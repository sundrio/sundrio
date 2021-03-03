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

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.maven.artifact.Artifact;
import org.codehaus.plexus.util.SelectorUtils;

public abstract class ArtifactRuleFilter implements ArtifactFilter {

  private static final String ARTIFACT_FORMAT = "%s:%s:%s:%s:%s";
  private static final Pattern ARTIFACT_PATTERN = Pattern
      .compile("(?<groupId>[^:]+):(?<artifactId>[^:]+)(:(?<version>[^:]+))?(:(?<type>[^:]+))?(:(?<classifier>[^:]+))?");

  static boolean matches(Artifact artifact, Set<String> set) {
    Set<String> expanded = expand(set);
    String coords = String.format(ARTIFACT_FORMAT, artifact.getGroupId(),
        artifact.getArtifactId(),
        artifact.getVersion(),
        artifact.getType(),
        artifact.getClassifier());

    for (String e : expanded) {
      if (SelectorUtils.match(e, coords)) {
        return true;
      }
    }
    return false;
  }

  static Set<String> expand(Set<String> set) {
    Set<String> result = new HashSet<String>();
    if (set != null) {
      for (String exclusion : set) {
        Matcher m = ARTIFACT_PATTERN.matcher(exclusion);
        if (!m.matches()) {
          throw new IllegalArgumentException("Pattern: " + exclusion + " doesn't the required format.");
        }
        String groupId = m.group("groupId");
        String artifactId = m.group("artifactId");
        String version = m.group("version");
        String type = m.group("type");
        String classifier = m.group("classifier");

        version = version != null ? version : "*";
        type = type != null ? type : "*";
        classifier = classifier != null ? classifier : "*";

        result.add(String.format(ARTIFACT_FORMAT, groupId, artifactId, version, type, classifier));
      }
    }
    return result;
  }
}
