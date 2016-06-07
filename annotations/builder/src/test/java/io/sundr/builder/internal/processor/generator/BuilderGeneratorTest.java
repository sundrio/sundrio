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

import io.sundr.builder.Constants;
import io.sundr.codegen.generator.CodeGeneratorBuilder;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static io.sundr.codegen.functions.ClassTo.TYPEDEF;

public class BuilderGeneratorTest {

    private static final TypeDef INTEGER = TYPEDEF.apply(Integer.class);

    @Test
    public void testFluentTemplate() throws IOException {


        TypeDef type = new TypeDefBuilder()
                .withName("Circle")
                .withPackageName("my.Test")
                .addNewConstructor()
                    .addNewArgument()
                        .withName("w")
                        .withTypeRef(INTEGER.toReference())
                    .endArgument()
                    .addNewArgument()
                        .withName("w")
                        .withTypeRef(INTEGER.toReference())
                    .endArgument()
                .endConstructor()
                .build();

        File tmp = new File("/tmp");
        generate(type, tmp, "CircleFluent.java", Constants.DEFAULT_FLUENT_IMPL_TEMPLATE_LOCATION);
        generate(type, tmp, "CircleBuilder.java", Constants.DEFAULT_BUILDER_TEMPLATE_LOCATION);

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