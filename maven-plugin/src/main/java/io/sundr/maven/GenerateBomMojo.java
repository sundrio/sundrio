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
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.velocity.runtime.directive.Directive;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Mojo(name = "generate-bom", inheritByDefault = false, defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class GenerateBomMojo extends AbstractSundrioMojo {

    private static final String BOM_TEMPLATE = "templates/bom.xml.vm";
    private static final String BOM_DIR = "bom";
    private static final String BOM_NAME = "bom.xml";
    private static final String POM_TYPE = "pom";
    private static final String PLUGIN_TYPE = "maven-plugin";

    @Parameter(defaultValue = "${reactorProjects}", required = true, readonly = false)
    private List<MavenProject> reactorProjects;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (getProject().isExecutionRoot()) {
            File outputDir = new File(getProject().getBuild().getOutputDirectory());
            File bomDir = new File(outputDir, BOM_DIR);
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
                CodeGenerator<BomModel> generator = new CodeGenerator<BomModel>(model, writer, BOM_TEMPLATE, Collections.<Class<? extends Directive>>emptySet());
                getLog().info("Generating BOM for model:" + model);
                generator.generate();
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
    }
}
