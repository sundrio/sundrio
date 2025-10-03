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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Base class that provides common functionality for fluent builders.
 * This class serves as a foundation for implementing fluent interfaces with visitable support.
 *
 * @param <F> the fluent type
 */
public class BaseFluent<F> {

  /**
   * Map of visitable objects associated with this fluent instance.
   */
  public final VisitableMap _visitables = new VisitableMap();

  /**
   * Creates a builder for the given item.
   * First attempts to use the item's edit() method if it's Editable,
   * otherwise tries to instantiate a corresponding Builder class.
   *
   * @param <T> the type of the item
   * @param item the item to create a builder for
   * @return a VisitableBuilder for the item
   * @throws IllegalStateException if no builder can be created
   */
  public static <T> VisitableBuilder<T, ?> builderOf(T item) {
    if (item instanceof Editable) {
      Object editor = ((Editable) item).edit();
      if (editor instanceof VisitableBuilder) {
        return (VisitableBuilder<T, ?>) editor;
      }
    }

    try {
      return (VisitableBuilder<T, ?>) Class
          .forName(item.getClass().getName() + "Builder", true, item.getClass().getClassLoader())
          .getConstructor(item.getClass())
          .newInstance(item);
    } catch (Exception e) {
      try {
        return (VisitableBuilder<T, ?>) Class.forName(item.getClass().getName() + "Builder").getConstructor(item.getClass())
            .newInstance(item);
      } catch (Exception e1) {
        throw new IllegalStateException("Failed to create builder for: " + item.getClass(), e1);
      }
    }
  }

  /**
   * Builds a list of items from a list of builders.
   *
   * @param <T> the type of items to build
   * @param list the list of builders to build from
   * @return a list of built items, or null if the input list is null
   */
  public static <T> List<T> build(List<? extends Builder<? extends T>> list) {
    return list == null ? null : list.stream().map(Builder::build).collect(Collectors.toList());
  }

  /**
   * Builds a set of items from a set of builders.
   *
   * @param <T> the type of items to build
   * @param set the set of builders to build from
   * @return a LinkedHashSet of built items, or null if the input set is null
   */
  public static <T> Set<T> build(Set<? extends Builder<? extends T>> set) {
    return set == null ? null : new LinkedHashSet<T>(set.stream().map(Builder::build).collect(Collectors.toSet()));
  }

  /**
   * Aggregates multiple lists into a single list, filtering out null lists.
   *
   * @param <T> the type of list elements
   * @param lists the lists to aggregate
   * @return a new ArrayList containing all non-null lists
   */
  public static <T> List<T> aggregate(List<? extends T>... lists) {
    return new ArrayList(Arrays.stream(lists).filter(Objects::nonNull).collect(Collectors.toList()));
  }

  /**
   * Aggregates multiple sets into a single set, filtering out null sets.
   *
   * @param <T> the type of set elements
   * @param sets the sets to aggregate
   * @return a new LinkedHashSet containing all non-null sets
   */
  public static <T> Set<T> aggregate(Set<? extends T>... sets) {
    return new LinkedHashSet(Arrays.stream(sets).filter(Objects::nonNull).collect(Collectors.toSet()));
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

  /**
   * Gets the visitable map associated with this fluent instance.
   *
   * @return an Optional containing the VisitableMap
   */
  public Optional<VisitableMap> getVisitableMap() {
    return Optional.of(_visitables);
  }
}
