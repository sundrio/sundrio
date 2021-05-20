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

package io.sundr.builder.internal.processor.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import io.sundr.builder.Constants;
import io.sundr.codegen.generator.CodeGeneratorBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.utils.Types;

public class BuilderGeneratorTest {

  @Test
  public void testFluentTemplate() throws IOException {

    TypeDef type = new TypeDefBuilder()
        .withName("Circle")
        .withPackageName("my.Test")
        .addNewConstructor()
        .addNewArgument()
        .withName("w")
        .withTypeRef(Types.INT_REF)
        .endArgument()
        .addNewArgument()
        .withName("w")
        .withTypeRef(Types.INT_REF)
        .endArgument()
        .endConstructor()
        .build();

    File tmp = new File(System.getProperty("java.io.tmpdir"));
    generate(type, tmp, "CircleFluent.java", Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION);
    generate(type, tmp, "CircleBuilder.java", Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION);

  }

  private static void generate(TypeDef model, File dir, String name, String templateResource) throws IOException {
    FileWriter fluentWriter = null;
    try {
      fluentWriter = new FileWriter(new File(dir, name));
      new CodeGeneratorBuilder<TypeDef>()
          .withModel(model)
          .withWriter(fluentWriter)
          .withTemplateResource(templateResource)
          .build().generate();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (fluentWriter != null) {
        fluentWriter.close();
      }
    }
  }
}
