/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.maven;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolutionRequest;
import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.lifecycle.LifecycleNotFoundException;
import org.apache.maven.lifecycle.LifecyclePhaseNotFoundException;
import org.apache.maven.lifecycle.internal.LifecycleModuleBuilder;
import org.apache.maven.lifecycle.internal.LifecycleTaskSegmentCalculator;
import org.apache.maven.lifecycle.internal.ProjectIndex;
import org.apache.maven.lifecycle.internal.ReactorBuildStatus;
import org.apache.maven.lifecycle.internal.ReactorContext;
import org.apache.maven.lifecycle.internal.TaskSegment;
import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginManagement;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.apache.maven.plugin.InvalidPluginDescriptorException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.MojoNotFoundException;
import org.apache.maven.plugin.PluginDescriptorParsingException;
import org.apache.maven.plugin.PluginNotFoundException;
import org.apache.maven.plugin.PluginResolutionException;
import org.apache.maven.plugin.prefix.NoPluginFoundForPrefixException;
import org.apache.maven.plugin.version.PluginVersionResolutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.SelectorUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mojo(name = "generate-bom", inheritByDefault = false, defaultPhase = LifecyclePhase.VERIFY)
public class GenerateBomMojo extends AbstractSundrioMojo {

    private final String ARTIFACT_FORMAT = "%s:%s:%s:%s:%s";
    private final Pattern ARTIFACT_PATTERN = Pattern.compile("(?<groupId>[^:]+):(?<artifactId>[^:]+)(:(?<version>[^:]+))?(:(?<type>[^:]+))?(:(?<classifier>[^:]+))?");

    private static final AtomicInteger READYPROJECTSCOUTNER = new AtomicInteger();

    private static final List<MavenProject> GENERATED_BOMS = Collections.synchronizedList(new ArrayList<MavenProject>());
    private static final Set<String> GENERATED_ARTIFACT_IDS = Collections.synchronizedSet(new HashSet<String>());

    @Component
    private ArtifactResolver artifactResolver;

    @Component
    private LifecycleModuleBuilder builder;

    @Component
    private LifecycleTaskSegmentCalculator segmentCalculator;

    /**
     * Location of the localRepository repository.
     */
    @Parameter(defaultValue = "${localRepository}", readonly = true, required = true)
    private ArtifactRepository localRepository;

    /**
     * List of Remote Repositories used by the resolver
     */
    @Parameter(defaultValue = "${project.remoteArtifactRepositories}", readonly = true, required = true)
    protected List<ArtifactRepository> remoteRepositories;


    @Parameter
    private BomConfig[] boms;

    @Parameter(defaultValue = "${reactorProjects}", required = true, readonly = false)
    private List<MavenProject> reactorProjects;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (getProject().isExecutionRoot() && !getProject().getModules().isEmpty()) {
            if (boms == null || boms.length == 0) {
                String artifactId = getProject().getArtifactId() + "-bom";
                if (GENERATED_ARTIFACT_IDS.add(artifactId)) {
                    GENERATED_BOMS.add(generateBom(artifactId, getProject().getName() + " Bom", new ArtifactSet(), new ArtifactSet()));
                }
            } else {
                for (BomConfig cfg : boms) {
                    if (GENERATED_ARTIFACT_IDS.add(cfg.getArtifactId())) {
                        GENERATED_BOMS.add(generateBom(cfg.getArtifactId(), cfg.getName(), cfg.getModules(), cfg.getDependencies()));
                    }
                }
            }
        }

        boolean projectsReady = READYPROJECTSCOUTNER.incrementAndGet() == reactorProjects.size() + 1;
        if (projectsReady) {
            synchronized (GENERATED_BOMS) {
                while (!GENERATED_BOMS.isEmpty()) {
                    try {
                        build(getSession().clone(), GENERATED_BOMS.remove(0));
                    } catch (Throwable t) {
                        throw new MojoExecutionException("Failed to build generated bom.", t);
                    }
                }
            }
        }
    }

    private MavenProject generateBom(String artifactId, String name, ArtifactSet moduleSet, ArtifactSet dependencySet) throws MojoFailureException, MojoExecutionException {
        File outputDir = new File(getProject().getBuild().getOutputDirectory());
        File bomDir = new File(outputDir, artifactId);
        File generatedBom = new File(bomDir, BOM_NAME);

        if (!bomDir.exists() && !bomDir.mkdirs()) {
            throw new MojoFailureException("Failed to create output dir for bom:" + bomDir.getAbsolutePath());
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter(generatedBom);
            Set<Artifact> archives = new LinkedHashSet<Artifact>();
            Set<Artifact> plugins = new LinkedHashSet<Artifact>();
            Set<Artifact> poms = new LinkedHashSet<Artifact>();

            Set<String> moduleIncludes = moduleSet != null ? moduleSet.getIncludes() : new HashSet<String>();
            Set<String> moduleExcludes = moduleSet != null ? moduleSet.getExcludes() : new HashSet<String>();

            Set<String> depIncludes = dependencySet != null ? dependencySet.getIncludes() : new HashSet<String>();
            Set<String> depExcludes = dependencySet != null ? dependencySet.getExcludes() : new HashSet<String>();

            if (moduleIncludes == null || moduleIncludes.isEmpty()) {
                moduleIncludes = new HashSet<String>();
                moduleIncludes.add("*:*");
            }

            for (MavenProject module : reactorProjects) {
                Artifact artifact = module.getArtifact();
                if (matches(artifact, moduleIncludes) && !matches(artifact, moduleExcludes)) {
                    if (PLUGIN_TYPE.equals(artifact.getType())) {
                        plugins.add(artifact);
                    } else if (POM_TYPE.equals(artifact.getType())) {
                        poms.add(artifact);
                    } else {
                        archives.add(artifact);
                    }

                    //Check artifacts
                    for (Artifact a : getDependencies(module.getArtifact())) {
                        if (matches(a, depIncludes) && !matches(a, depExcludes)) {
                            archives.add(a);
                        }
                    }
                }
            }

            MavenProject rootProject = getProject();
            MavenProject bomProject = new MavenProject(rootProject);

            bomProject.setFile(generatedBom);
            bomProject.getModel().setPomFile(generatedBom);
            bomProject.setModelVersion(rootProject.getModelVersion());

            bomProject.setArtifact(new DefaultArtifact(rootProject.getGroupId(),
                    artifactId, rootProject.getVersion(), rootProject.getArtifact().getScope(),
                    rootProject.getArtifact().getType(),rootProject.getArtifact().getClassifier(),
                    rootProject.getArtifact().getArtifactHandler()));


            bomProject.setParent(rootProject.getParent());
            bomProject.getModel().setParent(rootProject.getModel().getParent());

            bomProject.setGroupId(rootProject.getGroupId());
            bomProject.setArtifactId(artifactId);
            bomProject.setVersion(rootProject.getVersion());
            bomProject.setPackaging("pom");
            bomProject.setName(name);

            bomProject.setUrl(rootProject.getUrl());
            bomProject.setLicenses(rootProject.getLicenses());
            bomProject.setScm(rootProject.getScm());
            bomProject.setDevelopers(rootProject.getDevelopers());
            bomProject.setDistributionManagement(rootProject.getDistributionManagement());
            bomProject.getModel().setProfiles(rootProject.getModel().getProfiles());

            bomProject.getModel().setDependencyManagement(new DependencyManagement());
            for (Artifact artifact : archives) {
                bomProject.getDependencyManagement().addDependency(toDependency(artifact));
            }

            if (!plugins.isEmpty()) {
                bomProject.getModel().setBuild(new Build());
                bomProject.getModel().getBuild().setPluginManagement(new PluginManagement());
                for (Artifact artifact : plugins) {
                    bomProject.getPluginManagement().addPlugin(toPlugin(artifact));
                }
            }

            getLog().info("Generating BOM: " + artifactId);
            MavenXpp3Writer mavenWritter = new MavenXpp3Writer();
            mavenWritter.write(writer, cleanUp(bomProject).getModel());
            return bomProject;
        } catch (Exception e) {
            throw new MojoFailureException("Failed to generate bom.");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new MojoExecutionException("Failed to close the generated bom writer", e);
            }
        }
    }

    private void build(MavenSession session, MavenProject project) throws LifecycleNotFoundException, LifecyclePhaseNotFoundException, PluginNotFoundException, MojoNotFoundException, InvalidPluginDescriptorException, PluginDescriptorParsingException, NoPluginFoundForPrefixException, PluginVersionResolutionException, PluginResolutionException {
        session.getProjects().add(project);
        ProjectIndex projectIndex = new ProjectIndex(session.getProjects());
        ReactorBuildStatus reactorBuildStatus = new ReactorBuildStatus(session.getProjectDependencyGraph());
        ReactorContext reactorContext = new ReactorContext(session.getResult(), projectIndex, Thread.currentThread().getContextClassLoader(), reactorBuildStatus);
        List<TaskSegment> segments = segmentCalculator.calculateTaskSegments(session);
        for (TaskSegment segment : segments) {
            builder.buildProject(session, reactorContext, project, segment);
        }
    }


    private Set<Artifact> getDependencies(Artifact artifact) {
        ArtifactResolutionRequest request = new ArtifactResolutionRequest();
        request.setArtifact(artifact);
        request.setLocalRepository(localRepository);
        request.setRemoteRepositories(remoteRepositories);
        request.setResolveTransitively(true);
        ArtifactResolutionResult result = artifactResolver.resolve(request);
        return result.getArtifacts();
    }

    private boolean matches(Artifact artifact, Set<String> set) {
        Set<String> expanded = expand(set);
        String coords = String.format(ARTIFACT_FORMAT, artifact.getGroupId(),
                artifact.getArtifactId(),
                artifact.getVersion(),
                artifact.getType(),
                artifact.getClassifier());

        for (String e : expanded) {
            if (SelectorUtils.match(e, coords)) {
                return true;
            }
        }
        return false;
    }

    private Set<String> expand(Set<String> set) {
        Set<String> result = new HashSet<String>();
        if (set != null) {
            for (String exclusion : set) {
                Matcher m = ARTIFACT_PATTERN.matcher(exclusion);
                if (!m.matches()) {
                    throw new IllegalArgumentException("Pattern: " + exclusion + " doesn't the required format.");
                }
                String groupId = m.group("groupId");
                String artifactId = m.group("artifactId");
                String version = m.group("version");
                String type = m.group("type");
                String classifier = m.group("classifier");

                version = version != null ? version : "*";
                type = type != null ? type : "*";
                classifier = classifier != null ? classifier : "*";

                result.add(String.format(ARTIFACT_FORMAT, groupId, artifactId, version, type, classifier));
            }
        }
        return result;
    }

    private static MavenProject cleanUp(MavenProject p) {
        MavenProject result = new MavenProject();

        result.setModelVersion(p.getModelVersion());
        result.setGroupId(p.getGroupId());
        result.setArtifactId(p.getArtifactId());
        result.setVersion(p.getVersion());
        result.setPackaging(p.getPackaging());
        result.setName(p.getName());
        result.setDescription(p.getDescription());

        result.setDevelopers(p.getDevelopers());
        result.setLicenses(p.getLicenses());
        result.setScm(p.getScm());

        result.getModel().setDependencyManagement(p.getModel().getDependencyManagement());

        if (p.getModel().getBuild() != null &&
                p.getModel().getBuild().getPluginManagement() != null &&
                !p.getModel().getBuild().getPluginManagement().getPluginsAsMap().isEmpty()) {
            result.getModel().setBuild(new Build());
            result.getModel().getBuild().setPluginManagement(p.getModel().getBuild().getPluginManagement());
        }

        result.setArtifact(p.getArtifact());

        result.setParent(null);
        result.getModel().setParent(null);
        result.getModel().setBuild(null);
        return result;
    }

    private static Dependency toDependency(Artifact artifact) {
        Dependency dependency = new Dependency();
        dependency.setGroupId(artifact.getGroupId());
        dependency.setArtifactId(artifact.getArtifactId());
        dependency.setVersion(artifact.getVersion());
        dependency.setScope(artifact.getScope());
        dependency.setOptional(artifact.isOptional());
        return dependency;

    }

    private static Plugin toPlugin(Artifact artifact) {
        Plugin plugin = new Plugin();
        plugin.setGroupId(artifact.getGroupId());
        plugin.setArtifactId(artifact.getArtifactId());
        plugin.setVersion(artifact.getVersion());
        return plugin;

    }
}
