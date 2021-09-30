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

package io.sundr.codegen.apt;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

import javax.annotation.processing.Filer;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import io.sundr.SundrException;
import io.sundr.codegen.api.Output;
import io.sundr.codegen.api.Renderer;
import io.sundr.model.utils.Types;
import io.sundr.utils.Strings;

public class GenericAptOutput<T> implements Output<T> {

  private final Filer filer;
  private final Renderer<T> renderer;
  private final String moduleAndPackage;
  private final String relativePath;
  private final static StringWriter DEV_NULL = new StringWriter();

  private static final String EMPTY = "";
  private static final String DOT = ".";
  private static final String JAVA_EXTENSION = ".java";
  private static final String JAVA_EXTENSION_REGEX = "\\.java$";
  private static final String TRAILING_DOT_REGEX = "\\.$";
  private static final String JAVA_FILENAME_REGEX = "[^" + Pattern.quote(String.valueOf(File.separatorChar)) + "]*\\.java$";
  private static final String PARENT_DIRS_REGEX = "(\\w*" + Pattern.quote(String.valueOf(File.separatorChar)) + ")*";

  public GenericAptOutput(Filer filer, Renderer<T> renderer, String relativePath) {
    this(filer, renderer, "", relativePath);
  }

  public GenericAptOutput(Filer filer, Renderer<T> renderer, String moduleAndPackage, String relativePath) {
    this.filer = filer;
    this.renderer = renderer;
    this.moduleAndPackage = moduleAndPackage;
    this.relativePath = relativePath;
  }

  @Override
  public Function<T, Writer> getFunction() {
    return type -> {
      try {
        String rendered = renderer.render(type);
        Optional<String> name = Types.parseName(rendered);
        if (Strings.isNotNullOrEmpty(relativePath) && relativePath.endsWith(JAVA_EXTENSION)) {
          return createSourceFile(filer, rendered, relativePath);
        } else if (Strings.isNotNullOrEmpty(relativePath)) {
          return filer.createResource(StandardLocation.CLASS_OUTPUT, moduleAndPackage, relativePath).openWriter();
        } else if ((name.isPresent())) {
          return createSourceFile(filer, rendered, moduleAndPackage, name.get());
        } else {
          throw new SundrException(
              "Cannot generate resource. No output path specified and generated code does not correspond to a java class (so that output path can be inferred).");
        }
      } catch (IOException e) {
        throw SundrException.launderThrowable(e);
      }
    };
  }

  private static Writer createSourceFile(Filer filer, String content, String path) throws IOException {
    if (!path.endsWith(".java")) {
      throw new IllegalArgumentException("Called createSourceFile but path is not an actual java file.");
    }
    String name = classNameForPath(path);
    String pkg = packageForPath(path);
    return createSourceFile(filer, content, pkg, name);
  }

  private static Writer createSourceFile(Filer filer, String content, String moduleAndPackage, String name) throws IOException {
    String pkg = Types.parsePackage(content).orElse(moduleAndPackage);
    String fqcn = Strings.isNullOrEmpty(pkg) ? name : pkg + "." + name;
    FileObject fileObject = filer.getResource(StandardLocation.SOURCE_OUTPUT, pkg, name + JAVA_EXTENSION);
    File file = Paths.get(fileObject.toUri()).toFile();
    //If file exists just send output to /dev/null
    return file.exists() ? DEV_NULL : filer.createSourceFile(fqcn).openWriter();
  }

  static String classNameForPath(String path) {
    return path.replaceAll(JAVA_EXTENSION_REGEX, EMPTY).replaceAll(PARENT_DIRS_REGEX, EMPTY);
  }

  static String packageForPath(String path) {
    return path.replaceAll(JAVA_FILENAME_REGEX, EMPTY).replaceAll(Pattern.quote(String.valueOf(File.separator)), DOT)
        .replaceAll(TRAILING_DOT_REGEX, EMPTY);
  }
}
