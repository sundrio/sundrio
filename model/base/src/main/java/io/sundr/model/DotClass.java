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

package io.sundr.model;

public class DotClass implements Expression {

  private final ClassRef classRef;

  public DotClass(ClassRef classRef) {
    this.classRef = classRef;
  }

  public ClassRef getClassRef() {
    return classRef;
  }

  @Override
  public String renderExpression() {
    return classRef.getName() + ".class";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    DotClass dotClass = (DotClass) o;
    return classRef != null ? classRef.equals(dotClass.classRef) : dotClass.classRef == null;
  }

  @Override
  public int hashCode() {
    return classRef != null ? classRef.hashCode() : 0;
  }

  @Override
  public String toString() {
    return classRef.getName() + ".class";
  }
}
