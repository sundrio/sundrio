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

package io.sundr.validation.internal;

/**
 * Holds metadata about a method or constructor parameter extracted during annotation processing.
 */
public class ParamInfo {

  private final String typeFqn;
  private final String name;
  private final boolean primitive;

  public ParamInfo(String typeFqn, String name, boolean primitive) {
    this.typeFqn = typeFqn;
    this.name = name;
    this.primitive = primitive;
  }

  public String getTypeFqn() {
    return typeFqn;
  }

  public String getName() {
    return name;
  }

  public boolean isPrimitive() {
    return primitive;
  }
}
