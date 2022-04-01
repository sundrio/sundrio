/**
 * Copyright 2015 The original authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.sundr.builder;

import java.util.List;
import java.util.function.Predicate;

public class DelegatingVisitor<T> implements Visitor<T> {

  private final Class<T> type;
  private final Visitor<T> delegate;

  DelegatingVisitor(Class<T> type, Visitor<T> delegate) {
    this.type = type;
    this.delegate = delegate;
  }

  @Override
  public Predicate<List<Object>> getRequirement() {
    return delegate.getRequirement();
  }

  @Override
  public int order() {
    return delegate.order();
  }

  @Override
  public void visit(T target) {
    delegate.visit(target);
  }

  @Override
  public void visit(List<Object> path, T target) {
    delegate.visit(path, target);
  }

  @Override
  public Class<T> getType() {
    return type;
  }

  @Override
  public String toString() {
    return delegate.toString();
  }
}
