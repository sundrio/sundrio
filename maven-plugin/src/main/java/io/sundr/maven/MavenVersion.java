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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MavenVersion implements Comparable<MavenVersion> {

  private static final Pattern VERSION_PATTERN = Pattern.compile("(?<major>\\d+).(?<minor>\\d+).(?<micro>\\d+)");

  private final int major;
  private final int minor;
  private final int micro;

  public MavenVersion(String version) {
    this(versionGroup(version, "major"), versionGroup(version, "minor"), versionGroup(version, "micro"));
  }

  public MavenVersion(int major, int minor, int micro) {
    this.major = major;
    this.minor = minor;
    this.micro = micro;
  }

  @Override
  public int compareTo(MavenVersion o) {
    if (o == null) {
      return 0;
    } else if (this.major > o.major) {
      return -1;
    } else if (this.major < o.major) {
      return 1;
    } else if (this.minor > o.minor) {
      return -1;
    } else if (this.minor < o.minor) {
      return 1;
    } else if (this.micro > o.micro) {
      return -1;
    } else if (this.micro < o.micro) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public String toString() {
    return major + "." + minor + "." + micro;
  }

  private static int versionGroup(String version, String group) {
    if (version == null || version.isEmpty()) {
      return 0;
    }

    String cleanVersion = version.replaceAll("[^\\d\\.]", "");
    Matcher matcher = VERSION_PATTERN.matcher(cleanVersion);
    if (matcher.matches()) {
      String str = matcher.group(group);
      return Integer.parseInt(str);
    } else {
      return 0;
    }
  }
}
