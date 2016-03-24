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
import io.sundr.maven.filter.Filters;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mojo(name = "generate-bom", inheritByDefault = false, defaultPhase = LifecyclePhase.VALIDATE)
public class GenerateBomMojo extends AbstractSundrioMojo {


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
    private String bomTemplateResource = "templates/bom.xml.vm";

    @Parameter(defaultValue = "${bom.template.url}")
    private URL bomTemplateUrl;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (getProject().isExecutionRoot() && !getProject().getModules().isEmpty()) {
            List<MavenProject> updated = new LinkedList<MavenProject>();

            Map<BomConfig, MavenProject> generated = new HashMap<BomConfig, MavenProject>();
            updated.add(getProject());
            if (boms == null || boms.length == 0) {
                String artifactId = getProject().getArtifactId() + "-bom";
                if (GENERATED_ARTIFACT_IDS.add(artifactId)) {
                    BomConfig cfg = new BomConfig(artifactId, getProject().getName() + " Bom", " Generated bom");
                    MavenProject bomProject = generateBom(cfg);
                    generated.put(cfg, bomProject);
                    updated.add(bomProject);
                }
            } else {
                for (BomConfig cfg : boms) {
                    if (GENERATED_ARTIFACT_IDS.add(cfg.getArtifactId())) {
                        MavenProject bomProject = generateBom(cfg);
                        generated.put(cfg, bomProject);
                        updated.add(bomProject);
                    }
                }
            }

            updated.addAll(getAllButCurrent());
            for (Map.Entry<BomConfig, MavenProject> entry : generated.entrySet()) {
                build(getSession().clone(), entry.getValue(), updated, entry.getKey().getGoals());
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
        preProccessConfig(config);        
        try(FileWriter writer = new FileWriter(generatedBom)) {
            
            Set<Artifact> dependencies = new LinkedHashSet<Artifact>();
            Set<Artifact> plugins = new LinkedHashSet<Artifact>();

            Set<Artifact> ownModules = Filters.filter(getReactorArtifacts(), Filters.createModulesFilter(config));

            //We add first project management and unwrapped boms. (we don't resolve those).
            Set<Artifact> dependencyManagementArtifacts =  config.isInheritDependencyManagement() ? getProjectDependencyManagement() : new LinkedHashSet<Artifact>();
            Set<Artifact> pluginManagementArtifacts = config.isInheritPluginManagement() ? getProjectPluginManagement() : new LinkedHashSet<Artifact>();

            Set<Artifact> allDependencies = new LinkedHashSet<Artifact>(dependencyManagementArtifacts);
            allDependencies.addAll(getDependencies(getProjectDependencies())); //We resolve transitives here....

            //Populate dependencies
            dependencies.addAll(ownModules);
            dependencies.addAll(Filters.filter(allDependencies, Filters.createDependencyFilter(getSession(), config)));

            //Populate plugins
            plugins.addAll(Filters.filter(ownModules, Filters.MAVEN_PLUGIN_FILTER));
            plugins.addAll(Filters.filter(pluginManagementArtifacts, Filters.createPluginFilter(getSession(), config)));

            getLog().info("Generating BOM: " + config.getArtifactId());
            new CodeGeneratorBuilder<Model>()
                    .withWriter(writer)
                    .withModel(toGenerate(getProject(), config, dependencies, plugins).getModel())
                    .withTemplateResource(bomTemplateResource)
                    .withTemplateUrl(bomTemplateUrl)
                    .build().generate();

            return toBuild(getProject(), config);

        } catch (Exception e) {
            throw new MojoFailureException("Failed to generate bom.", e);
        }
    }

    private Set<Artifact> getReactorArtifacts() {
        Set<Artifact> reactorArtifacts = new LinkedHashSet<Artifact>();
        for (MavenProject project : reactorProjects) {
            reactorArtifacts.add(project.getArtifact());
        }
        return reactorArtifacts;
    }

    /**
     * Returns the model of the {@link org.apache.maven.project.MavenProject} to generate.
     * This is a trimmed down version and contains just the stuff that need to go into the bom.
     *
     * @param project The source {@link org.apache.maven.project.MavenProject}.
     * @param config  The {@link io.sundr.maven.BomConfig}.
     * @return The build {@link org.apache.maven.project.MavenProject}.
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
     *
     * @param project The source {@link org.apache.maven.project.MavenProject}.
     * @param config  The {@link io.sundr.maven.BomConfig}.
     * @return The build {@link org.apache.maven.project.MavenProject}.
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

    private List<MavenProject> getAllButCurrent() {
        List<MavenProject> result = new LinkedList<MavenProject>(getSession().getProjects());
        result.remove(getSession().getCurrentProject());
        return result;
    }

    private void build(MavenSession session, MavenProject project, List<MavenProject> allProjects, GoalSet goals) throws MojoExecutionException {
        session.setProjects(allProjects);
        ProjectIndex projectIndex = new ProjectIndex(session.getProjects());
        try {
            ReactorBuildStatus reactorBuildStatus = new ReactorBuildStatus(new BomDependencyGraph(session.getProjects()));
            ReactorContext reactorContext = new ReactorContextFactory(new MavenVersion(mavenVersion)).create(session.getResult(), projectIndex, Thread.currentThread().getContextClassLoader(), reactorBuildStatus, builder);
            List<TaskSegment> segments = segmentCalculator.calculateTaskSegments(session);
            for (TaskSegment segment : segments) {
                builder.buildProject(session, reactorContext, project, filterSegment(segment, goals));
            }
        } catch (Throwable t) {
            throw new MojoExecutionException("Error building generated bom:" + project.getArtifactId(), t);
        }
    }

    /**
     * Returns all the session/reactor artifacts topologically sorted.
     *
     * @return
     */
    private Set<Artifact> getSessionArtifacts() {
        Set<Artifact> result = new LinkedHashSet<Artifact>();
        for (MavenProject p : getSession().getProjectDependencyGraph().getSortedProjects()) {
            result.add(p.getArtifact());
        }
        return result;
    }

    /**
     * Returns all dependency artifacts in all modules, excluding all reactor artifacts (including attached).
     *
     * @return
     */
    private Set<Artifact> getProjectDependencies() {
        Set<Artifact> result = new LinkedHashSet<Artifact>();
        for (MavenProject p : getSession().getProjectDependencyGraph().getSortedProjects()) {
            for (Dependency dependency : p.getDependencies()) {
                result.add(toArtifact(dependency));
            }
        }
        return result;
    }

    /**
     * Returns all dependencies defined in dependency management of the root pom.
     *
     * @return
     */
    private Set<Artifact> getProjectDependencyManagement() {
        Set<Artifact> result = new LinkedHashSet<Artifact>();
        DependencyManagement dependencyManagement =  getProject().getDependencyManagement();
        if (dependencyManagement != null) {
            for (Dependency dependency : dependencyManagement.getDependencies()) {
                result.add(toArtifact(dependency));
            }
        }
        return result;
    }

    /**
     * Returns all dependencies defined in dependency management of the root pom.
     *
     * @return
     */
    private Set<Artifact> getProjectPluginManagement() {
        Set<Artifact> result = new LinkedHashSet<Artifact>();
        PluginManagement pluginManagement =  getProject().getPluginManagement();
        if (pluginManagement != null) {
            for (Plugin plugin : pluginManagement.getPlugins()) {
                result.add(toArtifact(plugin));
            }
        }
        return result;
    }

    /**
     * Resolves dependencies.
     *
     * @param dependencies
     * @return
     */
    private Set<Artifact> getDependencies(final Set<Artifact> dependencies) {
        ArtifactResolutionRequest request = new ArtifactResolutionRequest();
        request.setArtifact(getProject().getArtifact());
        request.setArtifactDependencies(dependencies);
        request.setLocalRepository(localRepository);
        request.setRemoteRepositories(remoteRepositories);
        request.setManagedVersionMap(getProject().getManagedVersionMap());
        request.setResolveTransitively(true);
        ArtifactResolutionResult result = artifactResolver.resolve(request);
        return result.getArtifacts();
    }

    private Artifact toArtifact(Dependency dependency) {
        return new DefaultArtifact(dependency.getGroupId(), dependency.getArtifactId(), dependency.getVersion(), dependency.getScope(), dependency.getType(), dependency.getClassifier(), getArtifactHandler());
    }

    private Artifact toArtifact(Plugin plugin) {
        return new DefaultArtifact(plugin.getGroupId(), plugin.getArtifactId(), plugin.getVersion(), null, Constants.MAVEN_PLUGIN_TYPE, null, getArtifactHandler());
    }

    private static Dependency toDependency(Artifact artifact) {
        Dependency dependency = new Dependency();
        dependency.setGroupId(artifact.getGroupId());
        dependency.setArtifactId(artifact.getArtifactId());
        dependency.setVersion(artifact.getVersion());
        dependency.setType(artifact.getType());
        dependency.setScope(artifact.getScope());
        dependency.setClassifier(artifact.getClassifier());
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


    private static void preProccessConfig(BomConfig config) {
        if (config.getModules().getIncludes().isEmpty()) {
            config.getModules().getIncludes().add("*:*");
        }
    }
}
