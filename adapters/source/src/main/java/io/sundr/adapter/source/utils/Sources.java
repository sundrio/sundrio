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

package io.sundr.adapter.source.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.api.Adapters;
import io.sundr.model.TypeDef;

public class Sources {

  private static final String NO_TYPE_FOUND = "No type found in the stream.";

  /**
   * Read the first {@link TypeDef} instance from an {@link InputStream}.
   *
   * @param is the {@link InputStream}
   * @return the first {@link TypeDef} instance, or throw an exception if none is found.
   */
  public static TypeDef readTypeDefFromStream(InputStream is, AdapterContext ctx) {
    return readTypeDefsFromStream(is, ctx).stream().findFirst().orElseThrow(() -> new IllegalStateException(NO_TYPE_FOUND));
  }

  /**
   * Read a {@link List} of {@link TypeDef} instances from an {@link InputStream}.
   *
   * @param is the {@link InputStream}
   * @return a {@link List} of {@link TypeDef} instances.
   */
  public static List<TypeDef> readTypeDefsFromStream(InputStream is, AdapterContext ctx) {
    CompilationUnit cu = Sources.FROM_INPUTSTREAM_TO_COMPILATIONUNIT.apply(is);
    return cu.getTypes().stream().map(t -> Adapters.adaptType(t, ctx)).collect(Collectors.toList());
  }

  /**
   * Read a {@link List} of {@link TypeDeclaration} instances from an {@link InputStream}.
   *
   * @param is the {@link InputStream}
   * @return a {@link List} of {@link TypeDeclaration} instances.
   */
  public static List<TypeDeclaration> readTypesFromStream(InputStream is) {
    CompilationUnit cu = Sources.FROM_INPUTSTREAM_TO_COMPILATIONUNIT.apply(is);
    return cu.getTypes();
  }

  /**
   * Read a {@link TypeDeclaration} instance from an {@link InputStream}.
   *
   * @param is the {@link InputStream}
   * @return the {@link TypeDeclaration} instance.
   */
  public static TypeDeclaration readTypeFromStream(InputStream is) {
    return readTypesFromStream(is).stream().findFirst().orElseThrow(() -> new IllegalStateException(NO_TYPE_FOUND));
  }

  /**
   * Read a {@link List} of {@link TypeDeclaration} instances from a classpath resource.
   *
   * @param resourceName the {@link InputStream}
   * @return a {@link List} of {@link TypeDeclaration} instances.
   */
  public static List<TypeDeclaration> readTypesFromResource(String resourceName) {
    CompilationUnit cu = Sources.FROM_CLASSPATH_TO_COMPILATIONUNIT.apply(resourceName);
    return cu.getTypes();
  }

  /**
   * Read a {@link TypeDeclaration} instance from a classpath resource.
   *
   * @param resourceName the {@link InputStream}
   * @return the {@link TypeDeclaration} instance.
   */
  public static TypeDeclaration readTypeFromResource(String resourceName) {
    return readTypesFromResource(resourceName).stream().findFirst().orElseThrow(() -> new IllegalStateException(NO_TYPE_FOUND));
  }

  /**
   * Read the first {@link TypeDef} instance from a classpath resource.
   *
   * @param resourceName the {@link InputStream}
   * @param ctx the {@link AdapterContext}
   * @return the first {@link TypeDef} instance, or throw an exception if none is found.
   */
  public static TypeDef readTypeDefFromResource(String resourceName, AdapterContext ctx) {
    return readTypeDefsFromResource(resourceName, ctx).stream().findFirst()
        .orElseThrow(() -> new IllegalStateException(NO_TYPE_FOUND));
  }

  /**
   * Read a {@link List} of {@link TypeDef} instances from a classpath resource.
   *
   * @param resourceName the {@link InputStream}
   * @return a {@link List} of {@link TypeDef} instances.
   */
  public static List<TypeDef> readTypeDefsFromResource(String resourceName, AdapterContext ctx) {
    CompilationUnit cu = Sources.FROM_CLASSPATH_TO_COMPILATIONUNIT.apply(resourceName);
    return cu.getTypes().stream().map(t -> Adapters.adaptType(t, ctx)).collect(Collectors.toList());
  }

  private static final Function<File, CompilationUnit> FROM_FILE_TO_COMPILATIONUNIT = file -> {
    try (FileInputStream fis = new FileInputStream(file)) {
      return JavaParser.parse(fis);
    } catch (Exception ex) {
      throw new RuntimeException("Failed to load file: [" + file.getAbsolutePath() + "] from file system.");
    }
  };

  private static final Function<String, CompilationUnit> FROM_CLASSPATH_TO_COMPILATIONUNIT = resource -> {
    try (InputStream is = Sources.class.getClassLoader().getResourceAsStream(resource)) {
      return JavaParser.parse(is);
    } catch (Exception ex) {
      throw new RuntimeException("Failed to load resource: [" + resource + "] from classpath.");
    }
  };

  private static final Function<InputStream, CompilationUnit> FROM_INPUTSTREAM_TO_COMPILATIONUNIT = is -> {
    try {
      return JavaParser.parse(is);
    } catch (Exception ex) {
      throw new RuntimeException("Failed to parse stream.", ex);
    } finally {
      closeQuietly(is);
    }
  };

  /**
   * Closes multiple {@link Closeable} objects swallowing exceptions.
   *
   * @param cloasebales The {@link Closeable} objects.
   */
  private static void closeQuietly(Closeable... cloasebales) {
    if (cloasebales != null) {
      for (Closeable c : cloasebales) {
        try {
          if (c != null) {
            c.close();
          }
        } catch (IOException ex) {
          //ignore
        }
      }
    }
  }
}
