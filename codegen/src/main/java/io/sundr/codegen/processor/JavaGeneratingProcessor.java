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

import io.sundr.codegen.functions.Sources;
import io.sundr.codegen.generator.CodeGeneratorBuilder;
import io.sundr.codegen.generator.CodeGeneratorContext;
import io.sundr.codegen.model.TypeDef;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.FilerException;
import javax.tools.JavaFileObject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public abstract class JavaGeneratingProcessor extends AbstractProcessor {

    protected CodeGeneratorContext context = new CodeGeneratorContext();

    /**
     * Generates a source file from the specified {@link io.sundr.codegen.model.TypeDef}.
     * @param model                     The model of the class to generate.
     * @param resourceName              The template to use.
     * @throws IOException
     */
    public void generateFromResources(TypeDef model, String resourceName) throws IOException {
        try {
            generateFromResources(model, processingEnv
                    .getFiler()
                    .createSourceFile(model.getFullyQualifiedName()), resourceName);
        } catch (FilerException e) {
            //TODO: Need to avoid dublicate interfaces here.
        }
    }

    /**
     * Generates a source file from the specified {@link io.sundr.codegen.model.TypeDef}.
     *
     * @param model        The model of the class to generate.
     * @param fileObject   Where to save the generated class.
     * @param resourceName The template to use.
     * @throws IOException
     */
   public void generateFromResources(TypeDef model, JavaFileObject fileObject, String resourceName) throws IOException {
        System.err.println("Generating: "+model.getFullyQualifiedName());
        new CodeGeneratorBuilder<TypeDef>()
                .withContext(context)
                .withModel(model)
                .withWriter(fileObject.openWriter())
                .withTemplateResource(resourceName)
                .build()
                .generate();
    }



     /**
     * Generates a source file from the specified {@link io.sundr.codegen.model.TypeDef}.
     * @param model                     The model of the class to generate.
     * @param content              The template to use.
     * @throws IOException
     */
    public void generateFromStringTemplate(TypeDef model, String content) throws IOException {
        try {
            generateFromStringTemplate(model, processingEnv.getFiler().createSourceFile(model.getFullyQualifiedName()), content);
        } catch (FilerException e) {
            //TODO: Need to avoid dublicate interfaces here.
        }
    }

    /**
     * Generates a source file from the specified {@link io.sundr.codegen.model.TypeDef}.
     *
     * @param model        The model of the class to generate.
     * @param fileObject   Where to save the generated class.
     * @param content The template to use.
     * @throws IOException
     */
   public void generateFromStringTemplate(TypeDef model, JavaFileObject fileObject, String content) throws IOException {
        System.err.println("Generating: "+model.getFullyQualifiedName());
        new CodeGeneratorBuilder<TypeDef>()
                .withContext(context)
                .withModel(model)
                .withWriter(fileObject.openWriter())
                .withTemplateContent(content)
                .build()
                .generate();
    }

    /**
     * Generates a source file from the specified {@link io.sundr.codegen.model.TypeDef}.
     *
     * @param model        The model of the class to generate.
     * @param content The template to use.
     * @throws IOException
     */
   public void generateFromStringTemplateAndDetermineOutput(TypeDef model, String content) throws IOException {
        System.err.println("Generating: "+model.getFullyQualifiedName());
        try (StringWriter writer = new StringWriter()) {
            new CodeGeneratorBuilder<TypeDef>()
                    .withContext(context)
                    .withModel(model)
                    .withWriter(writer)
                    .withTemplateContent(content)
                    .build()
                    .generate();

            ByteArrayInputStream bis = new ByteArrayInputStream(writer.toString().getBytes());
            TypeDef newModel = Sources.FROM_INPUTSTEAM_TO_SINGLE_TYPEDEF.apply(bis);

            generateFromStringTemplate(model, processingEnv.getFiler().createSourceFile(newModel.getFullyQualifiedName()), content);

        }
    }
}
