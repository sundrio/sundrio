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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.sundr.codegen.model.TypeDef;
import io.sundr.dsl.internal.utils.GraphUtils;
import io.sundr.dsl.internal.utils.TypeDefUtils;

public class NodeContext {

  private final TypeDef item;
  private final List<TypeDef> path;
  private final Set<TypeDef> visited;
  private final Set<TypeDef> all;

  public static class Builder {
    private TypeDef item;
    private List<TypeDef> path = new ArrayList<TypeDef>();
    private Set<TypeDef> visited = new HashSet<TypeDef>();
    private Set<TypeDef> all = new LinkedHashSet<TypeDef>();

    public Builder withItem(TypeDef item) {
      this.item = item;
      return this;
    }

    public Builder withPath(List<TypeDef> path) {
      this.path = path;
      return this;
    }

    public Builder addToPath(TypeDef clazz) {
      List<TypeDef> newPath = new ArrayList<TypeDef>(path);
      newPath.add(clazz);
      this.path = newPath;
      return this;
    }

    public Builder withVisited(Set<TypeDef> visited) {
      this.visited = visited;
      return this;
    }

    public Builder addToVisited(TypeDef clazz) {
      Set<TypeDef> newVisited = new HashSet<TypeDef>(visited);
      newVisited.add(clazz);
      this.visited = newVisited;
      return this;
    }

    public Builder addToVisited(Collection<TypeDef> clazzes) {
      Set<TypeDef> newVisited = new HashSet<TypeDef>(visited);
      newVisited.addAll(clazzes);
      this.visited = newVisited;
      return this;
    }

    public Builder withAll(Set<TypeDef> all) {
      this.all = all;
      return this;
    }

    public NodeContext build() {
      return new NodeContext(item, path, visited, all);
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  public NodeContext(TypeDef item, List<TypeDef> path, Set<TypeDef> visited, Set<TypeDef> all) {
    this.item = item;
    this.path = path != null ? Collections.unmodifiableList(path) : Collections.<TypeDef> emptyList();
    this.visited = visited != null ? Collections.unmodifiableSet(visited) : Collections.<TypeDef> emptySet();
    this.all = all != null ? Collections.unmodifiableSet(all) : Collections.<TypeDef> emptySet();
  }

  public TypeDef getItem() {
    return item;
  }

  public List<TypeDef> getPath() {
    return path;
  }

  public List<TypeDef> getPathTypes() {
    List<TypeDef> pathTypes = new ArrayList<TypeDef>();
    for (TypeDef c : path) {
      pathTypes.add(c);
    }
    return pathTypes;
  }

  public Set<TypeDef> getVisited() {
    return visited;
  }

  public Set<TypeDef> getVisitedTypes() {
    Set<TypeDef> visitedTypes = new HashSet<TypeDef>();
    for (TypeDef c : visited) {
      visitedTypes.add(c);
    }
    return visitedTypes;
  }

  public Set<TypeDef> getAll() {
    return all;
  }

  public List<String> getActiveScopes() {
    return GraphUtils.getScopes(getPathTypes());
  }

  public Builder but() {
    return builder().withItem(item)
        .withPath(new ArrayList<TypeDef>(path))
        .withVisited(new HashSet<TypeDef>(visited))
        .withAll(new HashSet<TypeDef>(all));
  }

  public Builder contextOfChild(TypeDef child) {
    if (TypeDefUtils.isBeginScope(child)) {
      return builder().withItem(child).withAll(new HashSet<TypeDef>(all));
    }

    return but().withItem(child)
        .withPath(new ArrayList<TypeDef>(path))
        .addToPath(item)
        .withVisited(new HashSet<TypeDef>(visited))
        .addToVisited(item)
        .withAll(new LinkedHashSet<TypeDef>(all));
  }
}
