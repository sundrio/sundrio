/*
 * Copyright 2015 The original authors.
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

package io.sundr.codegen.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public final class GeneratorUtils {

  private GeneratorUtils() {
    //Utility Class
  }

  static void generate(VelocityContext context, Writer writer, Template template) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(writer);
    try {
      template.merge(context, bufferedWriter);
      writer.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      bufferedWriter.close();
    }
  }

  static void generate(VelocityContext context, File dir, String fileName, Template template) throws IOException {
    FileWriter fw = null;
    try {
      fw = new FileWriter(new File(dir, fileName));
      generate(context, fw, template);
    } finally {
      if (fw != null) {
        fw.close();
      }
    }
  }
}
