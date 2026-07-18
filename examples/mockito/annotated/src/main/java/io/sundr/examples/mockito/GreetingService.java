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

package io.sundr.examples.mockito;

import io.sundr.mockito.annotations.Mockable;

/**
 * Annotating the type directly generates {@code GreetingServiceMock} into main sources,
 * so downstream modules can use the DSL in their own tests. Prefer the test-scoped
 * {@code Mockables} marker (see the marker example) when the DSL is only needed locally.
 */
@Mockable
public interface GreetingService {

  String greet(String name);

  int countGreetings(String name, boolean includeFormal);
}
