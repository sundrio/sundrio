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
 * EnvVarSource represents a source for the value of an EnvVar.
 */
@Buildable(generateBuilderPackage = true, builderPackage = "io.sundr.examples.kubernetes.domain")
public class EnvVarSource {

  private final java.util.Optional<String> _fieldRef;

  public EnvVarSource(java.util.Optional<String> _fieldRef) {
    this._fieldRef = _fieldRef;
  }

  /*
   * Selects a field of the pod: supports metadata.name, metadata.namespace, metadata.labels, metadata.annotations,
   * spec.nodeName, spec.serviceAccountName, status.hostIP, status.podIP.
   * +optional
   */
  public java.util.Optional<String> getFieldRef() {
    return this._fieldRef;
  }

}
