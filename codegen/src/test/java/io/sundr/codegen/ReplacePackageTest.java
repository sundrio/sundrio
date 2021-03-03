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

package io.sundr.codegen;

import org.junit.Test;

import io.sundr.codegen.functions.Sources;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;

public class ReplacePackageTest {

  @Test
  public void testMoveToPackage() {
    TypeDef baseFluent = Sources.FROM_CLASSPATH_TO_SINGLE_TYPEDEF.apply("io/sundr/builder/BaseFluent.java");
    TypeDef refactored = new TypeDefBuilder(baseFluent).accept(new ReplacePackage("io.sundr.builder", "my.pkg")).build();
    System.out.println(refactored);
  }
}
