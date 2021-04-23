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

package io.sundr.model;

import java.util.function.Supplier;

public class StringStatement implements Statement {

  private final Supplier<String> provider;
  private final Object[] parameters;

  public StringStatement(Supplier<String> provider) {
    this.provider = provider;
    this.parameters = new Object[] {};
  }

  public StringStatement(Supplier<String> provider, Object[] parameters) {
    this.provider = provider;
    this.parameters = parameters;
  }

  public StringStatement(final String data) {
    this(() -> data, new Object[] {});
  }

  public StringStatement(final String data, final Object... parameters) {
    this(() -> String.format(data, parameters));
  }

  public Supplier<String> getProvider() {
    return provider;
  }

  @Override
  public String toString() {
    return provider.get();
  }
}
