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

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;

@Mojo(name = "deploy-bom", inheritByDefault = false, defaultPhase = LifecyclePhase.DEPLOY)
public class DeployBomMojo extends AbstractSundrioMojo {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (getProject().isExecutionRoot()) {
            File outputDir = new File(getProject().getBuild().getOutputDirectory());
            File bomDir = new File(outputDir, BOM_DIR);
            File generatedBom = new File(bomDir, BOM_NAME);

            try {
                if (generatedBom.exists()) {
                    MavenProject bomProject = readBomProject(generatedBom);
                    deploy(bomProject);
                }
            } catch (IOException e) {
                throw new MojoFailureException("Failed to deploy bom.");
            }
        }
    }
}
