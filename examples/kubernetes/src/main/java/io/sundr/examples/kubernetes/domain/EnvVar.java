/*
 * Copyright (C) 2017 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.sundr.examples.kubernetes.domain;

import io.sundr.builder.annotations.Buildable;
/*
 * EnvVar represents an environment variable present in a Container.
 */
@Buildable(generateBuilderPackage = true, builderPackage = "io.sundr.examples.kubernetes.domain")
public class EnvVar {

  private final String _name;

  private final java.util.Optional<String> _value;

  private final java.util.Optional<EnvVarSource> _valueFrom;

  public EnvVar(String _name, java.util.Optional<String> _value, java.util.Optional<EnvVarSource> _valueFrom) {
    this._name = _name;
    this._value = _value;
    this._valueFrom = _valueFrom;
  }

  /*
   * Name of the environment variable. Must be a C_IDENTIFIER.
   */
  public String getName() {
    return this._name;
  }

  /*
   * Variable references $(VAR_NAME) are expanded
   * using the previous defined environment variables in the container and
   * any service environment variables. If a variable cannot be resolved,
   * the reference in the input string will be unchanged. The $(VAR_NAME)
   * syntax can be escaped with a double $$, ie: $$(VAR_NAME). Escaped
   * references will never be expanded, regardless of whether the variable
   * exists or not.
   * Defaults to "".
   * +optional
   */
  public java.util.Optional<String> getValue() {
    return this._value;
  }

  /*
   * Source for the environment variable's value. Cannot be used if value is not empty.
   * +optional
   */
  public java.util.Optional<EnvVarSource> getValueFrom() {
    return this._valueFrom;
  }
}
