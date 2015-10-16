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

import org.apache.maven.artifact.Artifact;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;

import java.util.Collection;

public class SessionArtifactFilter implements ArtifactFilter {

    private final Collection<MavenProject> projects;
    private final boolean include;

    public SessionArtifactFilter(MavenSession session, boolean include) {
        this.projects = session.getProjectDependencyGraph().getSortedProjects();
        this.include = include;
    }

    public Artifact apply(Artifact artifact) {
        if (artifact == null) {
            return null;
        } else if (include) {
            return inSessionOrNull(artifact);
        } else {
            return nullIfInSession(artifact);
        }
    }


    private static Artifact inSessionOrNull(Artifact artifact) {
        return isBom(artifact) ? artifact : null;
    }

    private static Artifact nullIfInSession(Artifact artifact) {
        return !isBom(artifact) ? artifact : null;
    }

    private static boolean isBom(Artifact artifact) {
        return Artifact.SCOPE_IMPORT.equals(artifact.getScope()) && "pom".equals(artifact.getType());
    }
}
