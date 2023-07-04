/**
 * Copyright 2015 The original authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
**/

package io.sundr.tests;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class WrapperTest {

  @Test
  public void testInheritedGenericsFromImmutableTypes() {
    Person person = new ImmutablePersonWrapperBuilder()
        .withNewItem()
        .withFirstName("John")
        .withLastName("Doe")
        .endItem()
        .build()
        .getItem();
  }

  @Test
  public void testInheritedGenericsFromMutableTypes() {
    Person person = new MutablePersonWrapperBuilder()
        .withNewItem()
        .withFirstName("John")
        .withLastName("Doe")
        .endItem()
        .build()
        .getItem();
  }

  @Test
  public void testBuilderInitialization() {
    MutablePersonWrapper wrapper = new MutablePersonWrapperBuilder()
        .withNewItem()
        .withFirstName("John")
        .withLastName("Doe")
        .endItem()
        .build();

    MutablePersonWrapperBuilder builder = new MutablePersonWrapperBuilder(wrapper);
    assertNotNull(builder.buildItem());
  }

}
