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
import java.util.function.Function;

import javax.annotation.processing.Filer;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import io.sundr.SundrException;
import io.sundr.codegen.api.Output;
import io.sundr.codegen.api.Renderer;
import io.sundr.codegen.api.TypeDefRenderer;
import io.sundr.model.TypeDef;
import io.sundr.utils.Patterns;

public class TypeDefAptOutput implements Output<TypeDef> {

  public static final String PACKAGE = ".*package\\s+(.*)\\s*\\;";
  public static final String CLASS_NAME = ".*(enum|class|interface)\\s+(\\w+).*\\{";

  private final Filer filer;
  private final Renderer<TypeDef> renderer;
  private final static StringWriter DEV_NULL = new StringWriter();

  public TypeDefAptOutput(Filer filer) {
    this(filer, new TypeDefRenderer());
  }

  public TypeDefAptOutput(Filer filer, Renderer<TypeDef> renderer) {
    this.filer = filer;
    this.renderer = renderer;
  }

  @Override
  public Function<TypeDef, Writer> getFunction() {
    return type -> {
      try {
        String rendered = renderer.render(type);
        String pkg = Patterns.match(rendered, PACKAGE).orElse(null);
        String name = Patterns.match(rendered, CLASS_NAME, 2)
            .orElseThrow(() -> new IllegalStateException("Cannot extract fully qualified name from generated code."));

        FileObject fileObject = filer.getResource(StandardLocation.SOURCE_OUTPUT, pkg, name);
        File file = Paths.get(fileObject.toUri()).toFile();
        //If file exists just send output to /dev/null
        return file.exists() ? DEV_NULL : filer.createSourceFile(type.getFullyQualifiedName()).openWriter();
      } catch (IOException e) {
        throw SundrException.launderThrowable(e);
      }
    };
  }
}
