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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.maven.model.Dependency;

public class BomConfig {

  private String artifactId;
  private String name;
  private String description = "Generated Bom";
  private ArtifactSet modules = new ArtifactSet();
  private ArtifactSet dependencies = new ArtifactSet();
  private List<Dependency> extraDependencies = new ArrayList<>();
  private ArtifactSet plugins = new ArtifactSet();
  private List<BomImport> imports = new LinkedList<BomImport>();
  private List<VersionOverride> overrides = new LinkedList<VersionOverride>();
  private GoalSet goals = new GoalSet();
  private boolean ignoreScope = true;
  private boolean excludeOptional = true;

  private boolean inheritDependencyManagement;
  private boolean inheritPluginManagement;

  private boolean checkMismatches = true;
  private boolean failOnMismatch = false;

  private Properties properties = new Properties();

  public BomConfig() {
  }

  public BomConfig(String artifactId, String name, String description) {
    this.artifactId = artifactId;
    this.name = name;
  }

  public String getArtifactId() {
    return artifactId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public List<Dependency> getExtraDependencies() {
    return extraDependencies;
  }

  public ArtifactSet getModules() {
    return modules;
  }

  public ArtifactSet getDependencies() {
    return dependencies;
  }

  public ArtifactSet getPlugins() {
    return plugins;
  }

  public List<BomImport> getImports() {
    return imports;
  }

  public List<VersionOverride> getOverrides() {
    return overrides;
  }

  public GoalSet getGoals() {
    return goals;
  }

  public Properties getProperties() {
    return properties;
  }

  public boolean isIgnoreScope() {
    return ignoreScope;
  }

  public boolean isExcludeOptional() {
    return excludeOptional;
  }

  public boolean isInheritDependencyManagement() {
    return inheritDependencyManagement;
  }

  public boolean isInheritPluginManagement() {
    return inheritPluginManagement;
  }

  public boolean isCheckMismatches() {
    return checkMismatches;
  }

  public boolean isFailOnMismatch() {
    return failOnMismatch;
  }
}
