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

import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;

public class BomModel {

  private final MavenProject project;
  private final Set<Artifact> archives;
  private final Set<Artifact> poms;
  private final Set<Artifact> plugins;

  public BomModel(MavenProject project, Set<Artifact> archives, Set<Artifact> poms, Set<Artifact> plugins) {
    this.project = project;
    this.archives = archives;
    this.poms = poms;
    this.plugins = plugins;
  }

  public MavenProject getProject() {
    return project;
  }

  public Set<Artifact> getArchives() {
    return archives;
  }

  public Set<Artifact> getPoms() {
    return poms;
  }

  public Set<Artifact> getPlugins() {
    return plugins;
  }

  @Override
  public String toString() {
    return project.getGroupId() + ":" + project.getArtifactId() + ":" + project.getVersion();
  }
}
