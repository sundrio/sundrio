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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.sundr.codegen.model.TypeDef;

public class NodeRepository {

  private final Map<String, Node<TypeDef>> map = new HashMap<String, Node<TypeDef>>();

  public Node<TypeDef> createNode(TypeDef item, Set<Node<TypeDef>> transitions) {
    return new Node<TypeDef>(item, transitions);
  }

  public Node<TypeDef> get(TypeDef item) {
    String key = getKey(item);
    return map.get(key);
  }

  public Node<TypeDef> getOrCreateNode(TypeDef item, Set<Node<TypeDef>> transitions) {
    Node<TypeDef> node;
    String key = getKey(item);
    if (map.containsKey(key)) {
      node = map.get(key);
      node.getTransitions().addAll(transitions);
      return node;
    } else {
      node = new Node<TypeDef>(item, transitions);
      map.put(key, node);
    }
    return node;
  }

  public void clear() {
    map.clear();
  }

  private static String getKey(TypeDef clazz) {
    return clazz.getFullyQualifiedName();
  }
}
