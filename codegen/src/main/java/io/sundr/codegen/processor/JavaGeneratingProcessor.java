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

package io.sundr.codegen.processor;

import io.sundr.codegen.generator.CodeGeneratorBuilder;
import io.sundr.codegen.generator.CodeGeneratorContext;
import io.sundr.codegen.model.JavaClazz;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.PackageElement;
import javax.tools.JavaFileObject;
import java.io.IOException;

public abstract class JavaGeneratingProcessor extends AbstractProcessor {

    protected CodeGeneratorContext context = new CodeGeneratorContext();
    /**
     * Generates a source file from the specified {@link io.sundr.codegen.model.JavaClazz}.*
     * @param model                     The model of the class to generate.
     * @param resourceName              The template to use.
     * @throws IOException
     */
    public void generateFromClazz(JavaClazz model, String resourceName) throws IOException {
        try {
            generateFromClazz(model, processingEnv
                    .getFiler()
                    .createSourceFile(model.getType().getFullyQualifiedName()), resourceName);
        } catch (FilerException e) {
            //TODO: Need to avoid dublicate interfaces here.
        }
    }

    /**
     * Generates a source file from the specified {@link io.sundr.codegen.model.JavaClazz}.
     *
     * @param model        The model of the class to generate.
     * @param fileObject   Where to save the generated class.
     * @param resourceName The template to use.
     * @throws IOException
     */
   public void generateFromClazz(JavaClazz model, JavaFileObject fileObject, String resourceName) throws IOException {
        System.err.println("Generating:"+model.getType().getFullyQualifiedName());
        new CodeGeneratorBuilder<JavaClazz>()
                .withContext(context)
                .withModel(model)
                .withWriter(fileObject.openWriter())
                .withTemplateResource(resourceName)
                .build()
                .generate();
    }
}
