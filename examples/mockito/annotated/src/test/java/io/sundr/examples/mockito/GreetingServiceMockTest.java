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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GreetingServiceMockTest {

  private final GreetingService service = GreetingServiceMock.mock();

  @Test
  public void stubsAndVerifiesThroughTheGeneratedDsl() {
    GreetingServiceMock.stub(service)
        .when().greet().withName("Ada")
        .thenReturn("Hello Ada!");
    GreetingServiceMock.stub(service)
        .when().countGreetings().withIncludeFormal(true)
        .thenReturn(3);

    assertEquals("Hello Ada!", service.greet("Ada"));
    assertEquals(3, service.countGreetings("anyone", true));
    assertEquals(0, service.countGreetings("anyone", false));

    GreetingServiceMock.stub(service)
        .verify().greet().withName("Ada")
        .called();
  }
}
