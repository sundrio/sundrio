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

import io.sundr.codegen.utils.StringUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.handler.ArtifactHandler;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecution;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public abstract class AbstractSundrioMojo extends AbstractMojo {

    static final String BOM_TEMPLATE = "templates/bom.xml.vm";
    static final String BOM_DIR = "bom";
    static final String BOM_NAME = "bom.xml";
    static final String POM_TYPE = "pom";
    static final String PLUGIN_TYPE = "maven-plugin";

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Parameter( defaultValue = "${session}", readonly = true )
    private MavenSession session;

    @Parameter( defaultValue = "${mojoExecution}", readonly = true )
    private MojoExecution mojo;

    // this is required for the deploy phase, but end user may just use a install phase only, so let required = false
    @Parameter(defaultValue = "${project.distributionManagementArtifactRepository}", readonly = true, required = false)
    private ArtifactRepository deploymentRepository;

    @Parameter(defaultValue = "${altDeploymentRepository}", readonly = true, required = false)
    private String altDeploymentRepository;

    @Component
    private ArtifactHandler artifactHandler;

    public MavenProject getProject() {
        return project;
    }

    MavenProject readBomProject(File pomFile) throws IOException {
        MavenXpp3Reader mavenReader = new MavenXpp3Reader();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(pomFile);
            Model model = mavenReader.read(fileReader);
            model.setPomFile(pomFile);
            MavenProject project = new MavenProject(model);
            project.setFile(pomFile);
            project.setArtifact(createArtifact(pomFile, model.getGroupId(), model.getArtifactId(), model.getVersion(), "compile", model.getPackaging(), ""));
            return project;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
    }

    Artifact createArtifact(File file, String groupId, String artifactId, String version, String scope, String type, String classifier) {
        DefaultArtifact artifact = new DefaultArtifact(groupId, artifactId, scope, version, type, classifier, artifactHandler);
        artifact.setFile(file);
        artifact.setResolved(true);
        return artifact;
    }

    void alsoMake(MavenProject project) throws MojoExecutionException {
        MavenExecutionRequest executionRequest = session.getRequest();

        InvocationRequest request = new DefaultInvocationRequest();
        request.setBaseDirectory(project.getBasedir());
        request.setPomFile(project.getFile());
        request.setGoals(executionRequest.getGoals());
        request.setRecursive(false);
        request.setInteractive(false);
        request.setProfiles(executionRequest.getActiveProfiles());
        request.setProperties(executionRequest.getUserProperties());
        Invoker invoker = new DefaultInvoker();
        try {
            InvocationResult result = invoker.execute(request);
            if (result.getExitCode() != 0) {
                throw new IllegalStateException("Error invoking Maven goals:[" + StringUtils.join(executionRequest.getGoals(), ", ") + "]", result.getExecutionException());
            }
        } catch (MavenInvocationException e) {
            throw new IllegalStateException("Error invoking Maven goals:[" + StringUtils.join(executionRequest.getGoals(), ", ") + "]", e);
        }
    }
}
