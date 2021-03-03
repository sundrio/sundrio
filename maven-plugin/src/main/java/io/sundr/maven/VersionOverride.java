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

/**
 * Information about version that should be overridden in the final BOM.
 */
public class VersionOverride {

  private ArtifactSet dependencies = new ArtifactSet();

  private String version;

  public VersionOverride() {
  }

  public ArtifactSet getDependencies() {
    return dependencies;
  }

  public void setDependencies(ArtifactSet dependencies) {
    this.dependencies = dependencies;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("VersionOverride{");
    sb.append("dependencies=").append(dependencies);
    sb.append(", version='").append(version).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
