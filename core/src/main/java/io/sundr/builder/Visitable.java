/*
 *
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

import java.util.List;

public interface Visitable<T> {

  default <V> T accept(Class<V> type, Visitor<V> visitor) {
    return accept(new Visitor<V>() {
      @Override
      public Class<V> getType() {
        return type;
      }

      @Override
      public void visit(V element) {
        visitor.visit(element);
      }
    });
  }

  default T accept(Visitor... visitors) {
    for (Visitor visitor : visitors) {
      if (visitor.canVisit(this)) {
        visitor.visit(this);
      }
    }
    return getTarget(this);
  }

  default T accept(List<Object> path, Visitor... visitors) {
    for (Visitor visitor : visitors) {
      if (visitor.canVisit(this)) {
        visitor.visit(path, this);
      }
    }
    return getTarget(this);
  }

  default T getTarget(Visitable<T> visitable) {
    return (T) visitable;
  }
}
