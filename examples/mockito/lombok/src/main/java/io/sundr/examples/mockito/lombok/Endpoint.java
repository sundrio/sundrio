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

import lombok.Value;

/**
 * {@code @Value} is the immutable variant: getters only (no setters), plus equals/hashCode/
 * toString. Note {@code @Value} makes the class final, which affects mockability.
 */
@Value
public class Endpoint {

  String url;
  int weight;
}
