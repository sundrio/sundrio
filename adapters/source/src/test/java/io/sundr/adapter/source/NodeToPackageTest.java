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

package io.sundr.adapter.source;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import io.sundr.adapter.source.utils.Sources;

public class NodeToPackageTest {

  private final NodeToPackage nodeToPackage = new NodeToPackage();

  @Test
  public void shouldResolveImportedListPackage() {
    TypeDeclaration<?> type = Sources.readTypeFromResource("ClassWithImportedTypes.java");
    FieldDeclaration field = type.getFields().get(0); // List<String> names

    ClassOrInterfaceType fieldType = (ClassOrInterfaceType) field.getVariable(0).getType();
    assertEquals("List", fieldType.getNameAsString());

    String resolvedPackage = nodeToPackage.apply(fieldType);
    assertEquals("java.util", resolvedPackage);
  }

  @Test
  public void shouldResolveImportedMapPackage() {
    TypeDeclaration<?> type = Sources.readTypeFromResource("ClassWithImportedTypes.java");
    FieldDeclaration field = type.getFields().get(1); // Map<String, Integer> counts

    ClassOrInterfaceType fieldType = (ClassOrInterfaceType) field.getVariable(0).getType();
    assertEquals("Map", fieldType.getNameAsString());

    String resolvedPackage = nodeToPackage.apply(fieldType);
    assertEquals("java.util", resolvedPackage);
  }
}
