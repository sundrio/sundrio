/*
 * Copyright 2016 The original authors.
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

package io.sundr.dsl.internal.graph;

import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaType;
import io.sundr.dsl.internal.utils.GraphUtils;
import io.sundr.dsl.internal.utils.JavaTypeUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class NodeContext {

    private final JavaClazz item;
    private final List<JavaClazz> path;
    private final Set<JavaClazz> visited;
    private final Set<JavaClazz> all;

    public static class Builder {
        private JavaClazz item;
        private List<JavaClazz> path = new ArrayList<JavaClazz>();
        private Set<JavaClazz> visited = new LinkedHashSet<JavaClazz>();
        private Set<JavaClazz> all = new LinkedHashSet<JavaClazz>();

        public Builder withItem(JavaClazz item) {
            this.item = item;
            return this;
        }

        public Builder withPath(List<JavaClazz> path) {
            this.path = path;
            return this;
        }

        public Builder addToPath(JavaClazz clazz) {
            List<JavaClazz> newPath = new ArrayList<JavaClazz>(path);
            newPath.add(clazz);
            this.path = newPath;
            return this;
        }

        public Builder withVisited(Set<JavaClazz> visited) {
            this.visited = visited;
            return this;
        }

        public Builder addToVisited(JavaClazz clazz) {
            Set<JavaClazz> newVisited = new LinkedHashSet<JavaClazz>(visited);
            newVisited.add(clazz);
            this.visited = newVisited;
            return this;
        }

        public Builder addToVisited(Collection<JavaClazz> clazzes) {
            Set<JavaClazz> newVisited = new LinkedHashSet<JavaClazz>(visited);
            newVisited.addAll(clazzes);
            this.visited = newVisited;
            return this;
        }

        public Builder withAll(Set<JavaClazz> all) {
            this.all = all;
            return this;
        }

        public NodeContext build() {
            NodeContext nodeContext = new NodeContext(item, path, visited, all);
            return nodeContext;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public NodeContext(JavaClazz item, List<JavaClazz> path, Set<JavaClazz> visited, Set<JavaClazz> all) {
        this.item = item;
        this.path = path != null ? Collections.unmodifiableList(path) : Collections.<JavaClazz>emptyList();
        this.visited = visited != null ? Collections.unmodifiableSet(visited) : Collections.<JavaClazz>emptySet();
        this.all = all != null ? Collections.unmodifiableSet(all) : Collections.<JavaClazz>emptySet();
    }

    public JavaClazz getItem() {
        return item;
    }

    public List<JavaClazz> getPath() {
        return path;
    }

    public List<JavaType> getPathTypes() {
        List<JavaType> pathTypes = new ArrayList<JavaType>();
        for (JavaClazz c : path) {
            pathTypes.add(c.getType());
        }
        return pathTypes;
    }

    public Set<JavaClazz> getVisited() {
        return visited;
    }

    public Set<JavaType> getVisitedTypes() {
        Set<JavaType> visitedTypes = new LinkedHashSet<JavaType>();
        for (JavaClazz c : visited) {
            visitedTypes.add(c.getType());
        }
        return visitedTypes;
    }

    public Set<JavaClazz> getAll() {
        return all;
    }

    public List<String> getActiveScopes() {
        return GraphUtils.getScopes(getPathTypes());
    }

    public Builder but() {
        return builder().withItem(item)
                .withPath(new ArrayList<JavaClazz>(path))
                .withVisited(new LinkedHashSet<JavaClazz>(visited))
                .withAll(new LinkedHashSet<JavaClazz>(all));
    }

    public Builder contextOfChild(JavaClazz child) {
        if (JavaTypeUtils.isBeginScope(child)) {
            return builder().withItem(child).withAll(new LinkedHashSet<JavaClazz>(all));
        }

        return but().withItem(child)
                .withPath(new ArrayList<JavaClazz>(path))
                .addToPath(item)
                .withVisited(new LinkedHashSet<JavaClazz>(visited))
                .addToVisited(item)
                .withAll(new LinkedHashSet<JavaClazz>(all));
    }
}
