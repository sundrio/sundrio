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

import io.sundr.codegen.generator.CodeGenerator;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;
import org.apache.velocity.runtime.directive.Directive;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Mojo(name = "generate-bom", inheritByDefault = false, defaultPhase = LifecyclePhase.VERIFY)
public class BomMojo extends AbstractSundrioMojo {

    private static final String BOM_TEMPLATE = "templates/bom.xml.vm";
    private static final String BOM_NAME = "bom.xml";
    private static final String POM_TYPE = "pom";
    private static final String PLUGIN_TYPE = "maven-plugin";

    @Component
    private MavenProjectHelper projectHelper;

    @Parameter(defaultValue = "${reactorProjects}", required = true, readonly = true)
    private List<MavenProject> reactorProjects;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (getProject().isExecutionRoot()) {
            File outputDir = new File(getProject().getBuild().getOutputDirectory());
            File generatedBom = new File(outputDir, BOM_NAME);
            if (!outputDir.exists() && !outputDir.mkdirs()) {
                throw new MojoFailureException("Failed to create output dir for bom:" + outputDir.getAbsolutePath());
            }
            try (FileWriter writer = new FileWriter(generatedBom)) {
                Set<Artifact> archives = new LinkedHashSet<>();
                Set<Artifact> plugins = new LinkedHashSet<>();
                Set<Artifact> poms = new LinkedHashSet<>();

                for (MavenProject module : reactorProjects) {
                    Artifact artifact = module.getArtifact();
                    if (PLUGIN_TYPE.equals(artifact.getType())) {
                        plugins.add(artifact);
                    } else if (POM_TYPE.equals(artifact.getType())) {
                        poms.add(artifact);
                    } else {
                        archives.add(artifact);
                    }
                }
                MavenProject rootProject = getProject();
                Artifact rootArtifact = rootProject.getArtifact();
                BomModel model = new BomModel(rootArtifact, archives, poms, plugins);
                CodeGenerator<BomModel> generator = new CodeGenerator<>(model, writer, BOM_TEMPLATE, Collections.<Class<? extends Directive>>emptySet());
                getLog().info("Generating BOM for model:" + model);
                generator.generate();
                getLog().info("Attaching BOM");
                projectHelper.attachArtifact(rootProject, generatedBom, "bom");
            } catch (IOException e) {
                throw new MojoFailureException("Failed to generate bom.");
            }
        }
    }

    public MavenProjectHelper getProjectHelper() {
        return projectHelper;
    }

    public void setProjectHelper(MavenProjectHelper projectHelper) {
        this.projectHelper = projectHelper;
    }

    public List<MavenProject> getReactorProjects() {
        return reactorProjects;
    }

    public void setReactorProjects(List<MavenProject> reactorProjects) {
        this.reactorProjects = reactorProjects;
    }
}
