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

import io.sundr.codegen.generator.CodeGeneratorBuilder;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolutionRequest;
import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.lifecycle.internal.LifecycleModuleBuilder;
import org.apache.maven.lifecycle.internal.LifecycleTaskSegmentCalculator;
import org.apache.maven.lifecycle.internal.ProjectIndex;
import org.apache.maven.lifecycle.internal.ReactorBuildStatus;
import org.apache.maven.lifecycle.internal.ReactorContext;
import org.apache.maven.lifecycle.internal.TaskSegment;
import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginManagement;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.SelectorUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mojo(name = "generate-bom", inheritByDefault = false, defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class GenerateBomMojo extends AbstractSundrioMojo {

    private final String ARTIFACT_FORMAT = "%s:%s:%s:%s:%s";
    private final Pattern ARTIFACT_PATTERN = Pattern.compile("(?<groupId>[^:]+):(?<artifactId>[^:]+)(:(?<version>[^:]+))?(:(?<type>[^:]+))?(:(?<classifier>[^:]+))?");

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

    @Parameter(defaultValue = "${maven.version}")
    private String mavenVersion;

    @Parameter(defaultValue = "${bom.template.resource}")
    private String bomTemplateResouce = "templates/bom.xml.vm";

    @Parameter(defaultValue = "${bom.template.url}")
    private URL bomTemplateUrl;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (getProject().isExecutionRoot() && !getProject().getModules().isEmpty()) {
            if (boms == null || boms.length == 0) {
                String artifactId = getProject().getArtifactId() + "-bom";
                if (GENERATED_ARTIFACT_IDS.add(artifactId)) {
                    BomConfig cfg = new BomConfig(artifactId, getProject().getName() + " Bom", " Generated bom");
                    build(getSession().clone(), generateBom(cfg), cfg.getGoals());
                }
            } else {
                for (BomConfig cfg : boms) {
                    if (GENERATED_ARTIFACT_IDS.add(cfg.getArtifactId())) {
                        build(getSession().clone(), generateBom(cfg), cfg.getGoals());
                    }
                }
            }
        }
    }

    private MavenProject generateBom(BomConfig config) throws MojoFailureException, MojoExecutionException {
        File outputDir = new File(getProject().getBuild().getOutputDirectory());
        File bomDir = new File(outputDir, config.getArtifactId());
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


            if (config.getModules().getIncludes().isEmpty()) {
                config.getModules().getIncludes().add("*:*");
            }

            for (MavenProject module : reactorProjects) {
                Artifact artifact = module.getArtifact();
                if (matches(artifact, config.getModules().getIncludes()) && !matches(artifact, config.getModules().getExcludes())) {
                    if (PLUGIN_TYPE.equals(artifact.getType())) {
                        plugins.add(artifact);
                    } else if (POM_TYPE.equals(artifact.getType())) {
                        poms.add(artifact);
                    } else {
                        archives.add(artifact);
                    }

                    //Check artifacts
                    for (Artifact a : getDependencies(module.getArtifact())) {
                        if (matches(a, config.getDependencies().getIncludes()) && !matches(a, config.getDependencies().getExcludes())) {
                            archives.add(a);
                        }
                    }
                }
            }
            getLog().info("Generating BOM: " + config.getArtifactId());
            new CodeGeneratorBuilder<Model>()
                    .withWriter(writer)
                    .withModel(toGenerate(getProject(), config, archives, plugins).getModel())
                    .withTemplateResource(bomTemplateResouce)
                    .withTemplateUrl(bomTemplateUrl)
                    .build().generate();

            return toBuild(getProject(), config);
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

    /**
     * Returns the model of the {@link org.apache.maven.project.MavenProject} to generate.
     * This is a trimmed down version and contains just the stuff that need to go into the bom.
     * @param project       The source {@link org.apache.maven.project.MavenProject}.
     * @param config        The {@link io.sundr.maven.BomConfig}.
     * @return              The build {@link org.apache.maven.project.MavenProject}.
     */
    private static MavenProject toGenerate(MavenProject project, BomConfig config, Set<Artifact> dependencies, Set<Artifact> plugins) {
        MavenProject toGenerate = project.clone();
        toGenerate.setGroupId(project.getGroupId());
        toGenerate.setArtifactId(config.getArtifactId());
        toGenerate.setVersion(project.getVersion());
        toGenerate.setPackaging("pom");
        toGenerate.setName(config.getName());
        toGenerate.setDescription(config.getDescription());

        toGenerate.setUrl(project.getUrl());
        toGenerate.setLicenses(project.getLicenses());
        toGenerate.setScm(project.getScm());
        toGenerate.setDevelopers(project.getDevelopers());

        toGenerate.getModel().setDependencyManagement(new DependencyManagement());
        for (Artifact artifact : dependencies) {
            toGenerate.getDependencyManagement().addDependency(toDependency(artifact));
        }

        toGenerate.getModel().setBuild(new Build());
        if (!plugins.isEmpty()) {
            toGenerate.getModel().setBuild(new Build());
            toGenerate.getModel().getBuild().setPluginManagement(new PluginManagement());
            for (Artifact artifact : plugins) {
                toGenerate.getPluginManagement().addPlugin(toPlugin(artifact));
            }
        }

        return toGenerate;
    }

    /**
     * Returns the generated {@link org.apache.maven.project.MavenProject} to build.
     * This version of the project contains all the stuff needed for building (parents, profiles, properties etc).
     * @param project       The source {@link org.apache.maven.project.MavenProject}.
     * @param config        The {@link io.sundr.maven.BomConfig}.
     * @return              The build {@link org.apache.maven.project.MavenProject}.
     */
    private static MavenProject toBuild(MavenProject project, BomConfig config) {
        File outputDir = new File(project.getBuild().getOutputDirectory());
        File bomDir = new File(outputDir, config.getArtifactId());
        File generatedBom = new File(bomDir, BOM_NAME);

        MavenProject toBuild = project.clone();
        //we want to avoid recursive "generate-bom".
        toBuild.setExecutionRoot(false);
        toBuild.setFile(generatedBom);
        toBuild.getModel().setPomFile(generatedBom);
        toBuild.setModelVersion(project.getModelVersion());

        toBuild.setArtifact(new DefaultArtifact(project.getGroupId(),
                config.getArtifactId(), project.getVersion(), project.getArtifact().getScope(),
                project.getArtifact().getType(), project.getArtifact().getClassifier(),
                project.getArtifact().getArtifactHandler()));


        toBuild.setParent(project.getParent());
        toBuild.getModel().setParent(project.getModel().getParent());

        toBuild.setGroupId(project.getGroupId());
        toBuild.setArtifactId(config.getArtifactId());
        toBuild.setVersion(project.getVersion());
        toBuild.setPackaging("pom");
        toBuild.setName(config.getName());
        toBuild.setDescription(config.getDescription());

        toBuild.setUrl(project.getUrl());
        toBuild.setLicenses(project.getLicenses());
        toBuild.setScm(project.getScm());
        toBuild.setDevelopers(project.getDevelopers());
        toBuild.setDistributionManagement(project.getDistributionManagement());
        toBuild.getModel().setProfiles(project.getModel().getProfiles());

        //We want to avoid having the generated stuff wiped.
        toBuild.getProperties().put("clean.skip", "true");
        toBuild.getModel().getBuild().setDirectory(bomDir.getAbsolutePath());
        toBuild.getModel().getBuild().setOutputDirectory(new File(bomDir, "target").getAbsolutePath());
        for (String key : config.getProperties().stringPropertyNames()) {
            toBuild.getProperties().put(key, config.getProperties().getProperty(key));
        }
        return toBuild;
    }



    private void build(MavenSession session, MavenProject project, GoalSet goals) throws MojoExecutionException {
        final List<MavenProject> projects = Arrays.asList(project);
        session.setProjects(projects);
        ProjectIndex projectIndex = new ProjectIndex(projects);
        try {
            ReactorBuildStatus reactorBuildStatus = new ReactorBuildStatus(new BomDependencyGraph(project));
            ReactorContext reactorContext = new ReactorContextFactory(new MavenVersion(mavenVersion)).create(session.getResult(), projectIndex, Thread.currentThread().getContextClassLoader(), reactorBuildStatus, builder);
            List<TaskSegment> segments = segmentCalculator.calculateTaskSegments(session);
            for (TaskSegment segment : segments) {
                builder.buildProject(session, reactorContext, project, filterSegment(segment, goals));
            }
        } catch (Throwable t) {
            throw new MojoExecutionException("Error building generated bom:" + project.getArtifactId(), t);
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
        result.setUrl(p.getUrl());

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

    private static TaskSegment filterSegment(TaskSegment segment, GoalSet goals) {
        List<Object> filtered = new ArrayList<Object>();

        Set<String> includes = goals.getIncludes();
        Set<String> excludes = goals.getExcludes();

        for (Object obj : segment.getTasks()) {
            String name = Reflections.readAnyField(obj, "pluginGoal", "lifecyclePhase");

            if (!excludes.contains(name) && (includes.contains(name) || includes.isEmpty())) {
                filtered.add(obj);
            }
        }
        return new TaskSegment(segment.isAggregating(), filtered.toArray());
    }
}
