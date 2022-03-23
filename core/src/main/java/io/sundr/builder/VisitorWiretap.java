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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class VisitorWiretap<T> implements Visitor<T> {

  private final Collection<VisitorListener> listeners;
  private final Visitor<T> delegate;

  private VisitorWiretap(Visitor<T> delegate, Collection<VisitorListener> listeners) {
    this.delegate = delegate;
    this.listeners = listeners;
  }

  public static <T> VisitorWiretap<T> create(Visitor<T> visitor, Collection<VisitorListener> listeners) {
    if (visitor instanceof VisitorWiretap) {
      return (VisitorWiretap<T>) visitor;
    }
    return new VisitorWiretap<T>(visitor, listeners);
  }

  @Override
  public <F> Boolean canVisit(F target) {
    boolean canVisit = delegate.canVisit(target);
    listeners.forEach(l -> l.onCheck(delegate, canVisit, target));
    return canVisit;
  }

  @Override
  public int order() {
    return delegate.order();
  }

  @Override
  public void visit(T target) {
    listeners.forEach(l -> l.beforeVisit(delegate, Collections.emptyList(), target));
    delegate.visit(target);
    listeners.forEach(l -> l.afterVisit(delegate, Collections.emptyList(), target));
  }

  @Override
  public void visit(List<Object> path, T target) {
    listeners.forEach(l -> l.beforeVisit(delegate, path, target));
    delegate.visit(path, target);
    listeners.forEach(l -> l.afterVisit(delegate, path, target));
  }

  @Override
  public Class<T> getType() {
    return delegate.getType();
  }

  @Override
  public String toString() {
    return delegate.toString();
  }

}
