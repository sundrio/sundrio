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

package io.sundr.maven.filter;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.execution.MavenSession;

import io.sundr.maven.BomConfig;
import io.sundr.maven.BomImport;

public class Filters {

  private Filters() {
  }

  public static final ArtifactFilter MAVEN_PLUGIN_FILTER = new MavenPluginFilter();
  public static final ArtifactFilter EXCLUDE_POM_FILTER = new IncludePomsFilter(false);

  public static ArtifactFilter createDependencyFilter(MavenSession session, BomConfig config) {
    final List<ArtifactFilter> filters = new LinkedList<ArtifactFilter>();
    filters.add(new SystemFilter());
    filters.add(new SessionArtifactFilter(session, false));
    filters.add(new IncludesFilter(config.getDependencies().getIncludes()));
    filters.add(new ExcludesFilter(config.getDependencies().getExcludes()));

    if (config.isExcludeOptional()) {
      filters.add(new OptionalFilter());
    }
    if (config.isIgnoreScope()) {
      filters.add(new ScopeFilter());
    }
    return new CompositeFilter(filters);
  }

  public static ArtifactFilter createModulesFilter(BomConfig config) {
    final List<ArtifactFilter> filters = new LinkedList<ArtifactFilter>();
    filters.add(new SystemFilter());
    filters.add(EXCLUDE_POM_FILTER);
    filters.add(new IncludesFilter(config.getModules().getIncludes()));
    filters.add(new ExcludesFilter(config.getModules().getExcludes()));

    if (config.isExcludeOptional()) {
      filters.add(new OptionalFilter());
    }
    if (config.isIgnoreScope()) {
      filters.add(new ScopeFilter());
    }
    return new CompositeFilter(filters);
  }

  public static ArtifactFilter createPluginFilter(MavenSession session, BomConfig config) {
    final List<ArtifactFilter> filters = new LinkedList<ArtifactFilter>();
    filters.add(MAVEN_PLUGIN_FILTER);
    filters.add(new IncludesFilter(config.getPlugins().getIncludes()));
    filters.add(new ExcludesFilter(config.getPlugins().getExcludes()));
    return new CompositeFilter(filters);
  }

  public static ArtifactFilter createDependencyManagementFilter(MavenSession session, BomImport bomImport) {
    final List<ArtifactFilter> filters = new LinkedList<ArtifactFilter>();
    filters.add(new SystemFilter());
    filters.add(new SessionArtifactFilter(session, false));
    filters.add(new IncludesFilter(bomImport.getDependencyManagement().getIncludes()));
    filters.add(new ExcludesFilter(bomImport.getDependencyManagement().getExcludes()));
    return new CompositeFilter(filters);
  }

  public static Set<Artifact> filter(Set<Artifact> artifacts, ArtifactFilter filter) {
    Set<Artifact> result = new LinkedHashSet<Artifact>();
    for (Artifact artifact : artifacts) {
      Artifact filtered = filter.apply(artifact);
      if (filtered != null) {
        result.add(filtered);
      }
    }
    return result;
  }

  public static <T> Map<Artifact, T> filter(Map<Artifact, T> dependencies, ArtifactFilter filter) {
    Map<Artifact, T> result = new LinkedHashMap<Artifact, T>();
    for (Map.Entry<Artifact, T> dependency : dependencies.entrySet()) {
      Artifact filtered = filter.apply(dependency.getKey());
      if (filtered != null) {
        result.put(filtered, dependency.getValue());
      }
    }
    return result;
  }
}
