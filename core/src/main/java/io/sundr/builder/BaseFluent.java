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

package io.sundr.builder;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class BaseFluent<F extends Fluent<F>> implements Fluent<F>, Visitable<F> {

  public final VisitableMap _visitables = new VisitableMap();

  public static <T> VisitableBuilder<T, ?> builderOf(T item) {
    if (item instanceof Editable) {
      Object editor = ((Editable) item).edit();
      if (editor instanceof VisitableBuilder) {
        return (VisitableBuilder<T, ?>) editor;
      }
    }

    try {
      return (VisitableBuilder<T, ?>) Class.forName(item.getClass().getName() + "Builder").getConstructor(item.getClass())
          .newInstance(item);
    } catch (Exception e) {
      throw new IllegalStateException("Failed to create builder for: " + item.getClass(), e);
    }
  }

  public static <T> List<T> build(List<? extends Builder<? extends T>> list) {
    return list == null ? null : new ArrayList<T>(list.stream().map(Builder::build).collect(Collectors.toList()));
  }

  public static <T> Set<T> build(Set<? extends Builder<? extends T>> set) {
    return set == null ? null : new LinkedHashSet<T>(set.stream().map(Builder::build).collect(Collectors.toSet()));
  }

  public static <T> List<T> aggregate(List<? extends T>... lists) {
    return new ArrayList(Arrays.stream(lists).filter(Objects::nonNull).collect(Collectors.toList()));
  }

  public static <T> Set<T> aggregate(Set<? extends T>... sets) {
    return new LinkedHashSet(Arrays.stream(sets).filter(Objects::nonNull).collect(Collectors.toSet()));
  }

  public F accept(Visitor... visitors) {
    return accept(Collections.emptyList(), visitors);
  }

  @Override
  public <V> F accept(Class<V> type, Visitor<V> visitor) {
    return accept(Collections.emptyList(), new Visitor<V>() {
      @Override
      public Class<V> getType() {
        return type;
      }

      @Override
      public void visit(List<Entry<String, Object>> path, V element) {
        visitor.visit(path, element);
      }

      @Override
      public void visit(V element) {
        visitor.visit(element);
      }
    });
  }

  public F accept(List<Entry<String, Object>> path, String currentKey, Visitor... visitors) {
    Arrays.stream(visitors)
        .map(v -> VisitorListener.wrap(v))
        .filter(v -> ((Visitor) v).canVisit(path, this))
        .sorted((l, r) -> ((Visitor) r).order() - ((Visitor) l).order())
        .forEach(v -> {
          ((Visitor) v).visit(path, this);
        });

    List<Entry<String, Object>> copyOfPath = path != null ? new ArrayList(path) : new ArrayList<>();
    copyOfPath.add(new AbstractMap.SimpleEntry<String, Object>(currentKey, this));

    _visitables.forEach((key, visitables) -> {
      List<Entry<String, Object>> newPath = Collections.unmodifiableList(copyOfPath);
      // Copy visitables to avoid ConcurrrentModificationException when Visitors add/remove Visitables
      new ArrayList<>(visitables).forEach(visitable -> {
        Arrays.stream(visitors)
            .filter(v -> v.getType() != null && v.getType().isAssignableFrom(visitable.getClass()))
            .forEach(v -> visitable.accept(newPath, key, v));

        Arrays.stream(visitors)
            .filter(v -> v.getType() == null || !v.getType().isAssignableFrom(visitable.getClass()))
            .forEach(v -> visitable.accept(newPath, key, v));
      });
    });
    return (F) this;
  }

  @Override
  public F accept(List<Entry<String, Object>> path, Visitor... visitors) {
    return accept(path, "", visitors);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + 0;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    return true;
  }
}
