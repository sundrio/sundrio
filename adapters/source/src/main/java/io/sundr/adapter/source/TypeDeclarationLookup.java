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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import com.github.javaparser.ast.body.TypeDeclaration;

import io.sundr.adapter.api.TypeLookup;
import io.sundr.adapter.source.utils.Project;
import io.sundr.adapter.source.utils.Sources;

public class TypeDeclarationLookup implements TypeLookup<TypeDeclaration> {

  @Override
  public Optional<TypeDeclaration> forName(String fullyQualifiedName) {
    //1. Lookup resources
    String resourceName = fullyQualifiedName.replace(".", File.separator) + ".java";
    try {
      Optional<TypeDeclaration> typeDeclaration = Sources.readTypesFromResource(resourceName).stream().findFirst();
      if (typeDeclaration.isPresent()) {
        return typeDeclaration;
      }
    } catch (Exception e) {
      //Will happen if the resource is not found.
    }

    //2. Lookup project path
    return Project.findJavaSourceFile(fullyQualifiedName).flatMap(f -> {
      try (FileInputStream fis = new FileInputStream(f.toFile())) {
        return Optional.ofNullable(Sources.readTypeFromStream(fis));
      } catch (IOException e) {
        return Optional.empty();
      }
    });
  }
}
