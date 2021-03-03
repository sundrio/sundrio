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

package io.sundr.codegen.functions;

import org.junit.Assert;
import org.junit.Test;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;

import io.sundr.codegen.model.TypeDef;

public class PareserTest {

  @Test
  public void testParser() throws Exception {

    CompilationUnit cu = Sources.FROM_CLASSPATH_TO_COMPILATIONUNIT.apply("io/sundr/builder/BaseFluent.java");
    String packageName = cu.getPackage().getPackageName();
    Assert.assertEquals("io.sundr.builder", packageName);

    for (TypeDeclaration typeDeclaration : cu.getTypes()) {
      TypeDef typeDef = Sources.TYPEDEF.apply(typeDeclaration);
      System.out.print(typeDef);
    }
  }
}
