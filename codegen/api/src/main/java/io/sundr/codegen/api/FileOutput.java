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

package io.sundr.codegen.api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Function;

import io.sundr.SundrException;

public class FileOutput<T> implements Output<T> {

  private final File file;

  public FileOutput(File file) {
    this.file = file;
  }

  @Override
  public Function<T, Writer> getFunction() {
    return t -> {
      try {
        return new FileWriter(file);
      } catch (IOException e) {
        throw SundrException.launderThrowable(e);
      }
    };
  }
}
