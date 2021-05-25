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

import java.io.IOException;
import java.io.Writer;
import java.util.function.Function;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileManager.Location;
import javax.tools.StandardLocation;

import io.sundr.SundrException;
import io.sundr.codegen.api.Output;

public class GenericAptOutput<T> implements Output<T> {

  private final Filer filer;
  private final Location location;
  private final String moduleAndPackage;
  private final String relativePath;

  public GenericAptOutput(Filer filer, String relativePath) {
    this(filer, StandardLocation.CLASS_OUTPUT, "", relativePath);
  }

  public GenericAptOutput(Filer filer, Location location, String moduleAndPackage, String relativePath) {
    this.filer = filer;
    this.location = location;
    this.moduleAndPackage = moduleAndPackage;
    this.relativePath = relativePath;
  }

  @Override
  public Function<T, Writer> getFunction() {
    return type -> {
      try {
        return filer.createResource(location, moduleAndPackage, relativePath).openWriter();
      } catch (IOException e) {
        throw SundrException.launderThrowable(e);
      }
    };
  }

}
