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

import java.util.Set;

import org.apache.maven.artifact.Artifact;

public class IncludesFilter extends ArtifactRuleFilter {

  private final Set<String> includes;

  public IncludesFilter(Set<String> includes) {
    this.includes = includes;
  }

  @Override
  public Artifact apply(Artifact artifact) {
    if (artifact == null) {
      return null;
    } else if (matches(artifact, includes)) {
      return artifact;
    } else {
      return null;
    }
  }
}
