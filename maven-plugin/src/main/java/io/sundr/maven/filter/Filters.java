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

import io.sundr.maven.BomConfig;
import org.apache.maven.artifact.Artifact;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Filters {

    public static ArtifactFilter createArtifactFilter(BomConfig config) {
        final List<ArtifactFilter> filters = new LinkedList<ArtifactFilter>();
        filters.add(new SystemFilter());
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
}
