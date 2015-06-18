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

package io.sundr.dependency;

import io.sundr.Function;

import java.util.LinkedHashSet;
import java.util.Set;

public class TopologicalSort<T> {

    private final Function<T, Set<T>> collectDepenedencies;

    public TopologicalSort(Function<T, Set<T>> collectDepenedencies) {
        this.collectDepenedencies = collectDepenedencies;
    }

    public Set<T> sort(Iterable<T> items) {
        Set<T> sorted = new LinkedHashSet<T>();
        Set<T> visited = new LinkedHashSet<T>();
        for (T e : items) {
            visit(e, visited, sorted);
        }
        return sorted;
    }


    public void visit(T item, Set<T> visited, Set<T> sorted) {
        if (!visited.add(item)) {
            return;
        }
        for (T t : collectDepenedencies.apply(item)) {
            visit(t, visited, sorted);
        }
        sorted.add(item);
    }
    
    public Set<T> collectDependencies(T item) {
        return collectDepenedencies.apply(item);
    }
}
