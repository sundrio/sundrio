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
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolutionRequest;
import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginManagement;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
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

    @Component
    private ArtifactResolver artifactResolver;

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
                generateBom(getProject().getArtifactId() + "-bom", getProject().getName() + " Bom", new ArtifactSet(), new ArtifactSet());
            } else {
                for (BomConfig cfg : boms) {
                    generateBom(cfg.getArtifactId(), cfg.getName(), cfg.getModules(), cfg.getDependencies());
                }
            }
        }
    }

    private void generateBom(String artifactId, String name, ArtifactSet moduleSet, ArtifactSet dependencySet) throws MojoFailureException, MojoExecutionException {
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
            MavenProject bomProject = new MavenProject();

            bomProject.setModelVersion(rootProject.getModelVersion());


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

            getLog().info("Generating BOM...");
            MavenXpp3Writer mavenWritter = new MavenXpp3Writer();
            mavenWritter.write(writer, bomProject.getModel());

            alsoMake(readBomProject(generatedBom));
        } catch (IOException e) {
            throw new MojoFailureException("Failed to generate bom.");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new MojoExecutionException("Failed to close the generated bom writer", e);
            }
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
