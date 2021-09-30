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
        if (Strings.isNotNullOrEmpty(relativePath) && relativePath.endsWith(".java")) {
          return filer.createResource(StandardLocation.SOURCE_OUTPUT, moduleAndPackage, relativePath).openWriter();
        } else if (Strings.isNotNullOrEmpty(relativePath)) {
          return filer.createResource(StandardLocation.CLASS_OUTPUT, moduleAndPackage, relativePath).openWriter();
        } else if ((name.isPresent())) {
          String pkg = Types.parsePackage(rendered).orElse(moduleAndPackage);
          String fqcn = Strings.isNullOrEmpty(pkg) ? name.get() : pkg + "." + name.get();
          FileObject fileObject = filer.getResource(StandardLocation.SOURCE_OUTPUT, pkg, name.get() + ".java");
          File file = Paths.get(fileObject.toUri()).toFile();
          //If file exists just send output to /dev/null
          return file.exists() ? DEV_NULL : filer.createSourceFile(fqcn).openWriter();
        } else {
          throw new SundrException(
              "Cannot generate resource. No output path specified and generated code does not correspond to a java class (so that output path can be inferred).");
        }
      } catch (IOException e) {
        throw SundrException.launderThrowable(e);
      }
    };
  }
}
