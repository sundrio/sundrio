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
import io.sundr.codegen.model.JavaClazz;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.PackageElement;
import javax.tools.JavaFileObject;
import java.io.IOException;

public abstract class JavaGeneratingProcessor extends AbstractProcessor {

    /**
     * Generates a source file from the specified {@link io.sundr.codegen.model.JavaClazz}.*
     * @param model                     The model of the class to generate.
     * @param processingEnvironment     The processing environment
     * @param resourceName              The template to use.
     * @throws IOException
     */
    public void generateFromClazz(JavaClazz model, ProcessingEnvironment processingEnvironment, String resourceName) throws IOException {
        PackageElement packageElement = processingEnvironment.getElementUtils().getPackageElement(model.getType().getPackageName());
        try {
            generateFromClazz(model, processingEnv
                    .getFiler()
                    .createSourceFile(model.getType().getClassName(), packageElement), resourceName);
        } catch (Exception e) {
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
        new CodeGeneratorBuilder<JavaClazz>()
                .withModel(model)
                .withWriter(fileObject.openWriter())
                .withTemplateResource(resourceName)
                .build()
                .generate();
    }
}
