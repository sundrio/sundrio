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

package io.sundr.examples.mockito.lombok;

import lombok.Data;

/**
 * {@code @Data} generates getters, setters, equals, hashCode and toString. The DSL should
 * expose the getters ({@code getZone}, {@code getReplicas}, {@code isEnabled}) and the
 * setters, while equals/hashCode/toString are excluded.
 */
@Data
public class CloudConfig {

  private String zone;
  private int replicas;
  private boolean enabled;
}
