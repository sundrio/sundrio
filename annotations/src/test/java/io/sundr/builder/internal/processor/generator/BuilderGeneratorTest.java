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

import io.sundr.builder.internal.processor.BuildableProcessor;
import io.sundr.codegen.generator.CodeGeneratorBuilder;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BuilderGeneratorTest {

    @Test
    public void testFluentTemplate() throws IOException {


        JavaClazz javaClazz = new JavaClazzBuilder()
                .withNewType()
                    .withClassName("Circle")
                    .withPackageName("my.Test")
                    .endType()
                .addNewConstructor()
                    .addNewArgument()
                        .withName("w")
                        .withNewType().withPackageName("java.lang").withClassName("Integer").endType()
                        .endArgument()
                    .addNewArgument()
                        .withName("w")
                        .withNewType().withPackageName("java.lang").withClassName("Integer").endType()
                        .endArgument()
                    .endConstructor()
                .build();

        File tmp = new File("/tmp");
        generate(javaClazz, tmp, "CircleFluent.java", BuildableProcessor.DEFAULT_FLUENT_TEMPLATE_LOCATION);
        generate(javaClazz, tmp, "CircleBuilder.java", BuildableProcessor.DEFAULT_BUILDER_TEMPLATE_LOCATION);

    }
    
    private static void generate(JavaClazz model, File dir, String name, String templateResource) {
        try (FileWriter fluentWriter = new FileWriter(new File(dir, name))) {
            new CodeGeneratorBuilder<JavaClazz>()
                    .withModel(model)
                    .withWriter(fluentWriter)
                    .withTemplateResource(templateResource)
                    .build().generate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}