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

import org.apache.maven.artifact.Artifact;

import java.util.Set;

public class BomModel {

    private final Artifact artifact;
    private final Set<Artifact> archives;
    private final Set<Artifact> poms;
    private final Set<Artifact> plugins;

    public BomModel(Artifact artifact, Set<Artifact> archives, Set<Artifact> poms, Set<Artifact> plugins) {
        this.artifact = artifact;
        this.archives = archives;
        this.poms = poms;
        this.plugins = plugins;
    }

    public Artifact getArtifact() {
        return artifact;
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
        return artifact.getGroupId() + ":" + artifact.getArtifactId() + ":" + artifact.getVersion();
    }
}
