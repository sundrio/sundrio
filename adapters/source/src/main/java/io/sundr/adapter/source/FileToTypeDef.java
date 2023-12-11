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
import java.util.function.Function;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;

import io.sundr.SundrException;
import io.sundr.adapter.api.AdapterContext;
import io.sundr.model.TypeDef;

public class FileToTypeDef implements Function<File, TypeDef> {

  private final Function<TypeDeclaration, TypeDef> typeDeclarationToTypeDef;

  public FileToTypeDef(AdapterContext context) {
    this(new TypeDeclarationToTypeDef(context));
  }

  public FileToTypeDef(Function<TypeDeclaration, TypeDef> typeDeclarationToTypeDef) {
    this.typeDeclarationToTypeDef = typeDeclarationToTypeDef;
  }

  @Override
  public TypeDef apply(File file) {
    try (FileInputStream is = new FileInputStream(file)) {
      CompilationUnit cu = JavaParser.parse(is);
      TypeDeclaration typeDeclaration = cu.getTypes().get(0);
      return typeDeclarationToTypeDef.apply(typeDeclaration);
    } catch (Exception e) {
      throw SundrException.launderThrowable(e);
    }
  }
}
