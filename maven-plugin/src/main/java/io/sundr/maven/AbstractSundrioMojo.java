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

import com.google.common.base.Strings;
import io.sundr.codegen.utils.StringUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.handler.ArtifactHandler;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.model.Model;
import org.apache.maven.model.Profile;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.plugin.AbstractMojo;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractSundrioMojo extends AbstractMojo {

    static final String BOM_TEMPLATE = "templates/bom.xml.vm";
    static final String BOM_DIR = "bom";
    static final String BOM_NAME = "bom.xml";
    static final String POM_TYPE = "pom";
    static final String PLUGIN_TYPE = "maven-plugin";


    static final String INSTALL_FILE_GOAL = "install:install-file";
    static final String DEPLOY_FILE_GOAL = "deploy:deploy-file";

    static final String FILE = "file";
    static final String GROUP_ID = "groupId";
    static final String ARTIFACT_ID = "artifactId";
    static final String VERSION = "version";
    static final String CLASSIFIER = "classifier";
    static final String PACKAGING = "packaging";
    static final String REPO_ID = "repositoryId";
    static final String REPO_URL = "url";
    static final String GENERATE_POM = "generatePom";

    static final Pattern ALT_REPO_PATTERN = Pattern.compile("(?<repositoryId>[^ ]*)::[^ ]*::(?<url>[^ ]*)");

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;


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


    void install(MavenProject project) throws MojoExecutionException {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setBaseDirectory(project.getBasedir());
        request.setPomFile(project.getFile());
        request.setGoals(Collections.singletonList(INSTALL_FILE_GOAL));
        request.setRecursive(false);
        request.setInteractive(false);
        request.setProfiles(getActiveProfileIds(project));

        Properties props = new Properties();
        props.setProperty(FILE, project.getFile().getAbsolutePath());
        props.setProperty(GROUP_ID, project.getGroupId());
        props.setProperty(ARTIFACT_ID, project.getArtifactId());
        props.setProperty(VERSION, project.getVersion());
        props.setProperty(CLASSIFIER, "");
        props.setProperty(PACKAGING, project.getPackaging());
        request.setProperties(props);

        Invoker invoker = new DefaultInvoker();
        try {
            InvocationResult result = invoker.execute(request);
            if (result.getExitCode() != 0) {
                throw new IllegalStateException("Error invoking Maven goal install:install-file");
            }
        } catch (MavenInvocationException e) {
            throw new MojoExecutionException("Error invoking Maven goal install:install-file", e);
        }
    }

    void deploy(MavenProject project) throws MojoExecutionException {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setBaseDirectory(project.getBasedir());
        request.setPomFile(project.getFile());
        request.setGoals(Collections.singletonList(DEPLOY_FILE_GOAL));
        request.setRecursive(false);
        request.setInteractive(false);
        request.setProfiles(getActiveProfileIds(project));

        Properties props = new Properties();
        props.setProperty(FILE, project.getFile().getAbsolutePath());
        props.setProperty(GROUP_ID, project.getGroupId());
        props.setProperty(ARTIFACT_ID, project.getArtifactId());
        props.setProperty(VERSION, project.getVersion());
        props.setProperty(CLASSIFIER, "");
        props.setProperty(PACKAGING, project.getPackaging());

        props.setProperty(REPO_URL, getDeployUrl());
        props.setProperty(REPO_ID, getRepositoryId());
        props.setProperty(GENERATE_POM, "false");
        request.setProperties(props);

        Invoker invoker = new DefaultInvoker();
        try {
            InvocationResult result = invoker.execute(request);
            if (result.getExitCode() != 0) {
                throw new IllegalStateException("Error invoking Maven goal deploy:deploy-file");
            }
        } catch (MavenInvocationException e) {
            throw new MojoExecutionException("Error invoking Maven goal deploy:deploy-file", e);
        }
    }

    private List<String> getActiveProfileIds(MavenProject project) {
        List<String> result = new ArrayList<String>();
        for (Profile profile : project.getActiveProfiles()) {
            String id = profile.getId();
            if (!Strings.isNullOrEmpty(id)) {
                result.add(id);
            }
        }
        return result;
    }

    private String getRepositoryId() {
        String altRepoId = getAltDeploymentRepositoryId();
        if (!StringUtils.isNullOrEmpty(altRepoId)) {
            return altRepoId;
        } else if (deploymentRepository != null) {
            return deploymentRepository.getId();
        } else {
            throw new IllegalStateException("Neither distribution management, nor altDeploymentRepository have been configured.");

        }
    }

    private String getDeployUrl() {
        String altRepoUrl = getAltDeploymentRepositoryUrl();
        if (!StringUtils.isNullOrEmpty(altRepoUrl)) {
            return altRepoUrl;
        } else if (deploymentRepository != null) {
            return deploymentRepository.getUrl();
        } else {
            throw new IllegalStateException("Neither distribution management, nor altDeploymentRepository have been configured.");
        }
    }

    private String getAltDeploymentRepositoryUrl() {
        if (Strings.isNullOrEmpty(altDeploymentRepository)) {
            return null;
        } else {
            Matcher m = ALT_REPO_PATTERN.matcher(altDeploymentRepository);
            if (m.matches()) {
                return m.group(REPO_URL);
            } else {
                throw new IllegalArgumentException("Parameter: altDeploymentRepository doesn't match the required pattern. Expected (id::layout::url), found: " + altDeploymentRepository);
            }
        }
    }

    private String getAltDeploymentRepositoryId() {
        if (Strings.isNullOrEmpty(altDeploymentRepository)) {
            return null;
        } else {
            Matcher m = ALT_REPO_PATTERN.matcher(altDeploymentRepository);
            if (m.matches()) {
                return m.group(REPO_ID);
            } else {
                throw new IllegalArgumentException("Parameter: altDeploymentRepository doesn't match the required pattern. Expected (id::layout::url), found: " + altDeploymentRepository);
            }
        }
    }
}
