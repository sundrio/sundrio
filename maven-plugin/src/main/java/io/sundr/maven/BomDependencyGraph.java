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

import org.apache.maven.execution.ProjectDependencyGraph;
import org.apache.maven.project.MavenProject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BomDependencyGraph implements ProjectDependencyGraph {

    private final MavenProject project;

    public BomDependencyGraph(MavenProject project) {
        this.project = project;
    }

    @Override
    public List<MavenProject> getSortedProjects() {
        return Arrays.asList(project);
    }

    @Override
    public List<MavenProject> getDownstreamProjects(MavenProject project, boolean transitive) {
        return Collections.emptyList();
    }

    @Override
    public List<MavenProject> getUpstreamProjects(MavenProject project, boolean transitive) {
        return Collections.emptyList();
    }
}
