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

package io.sundr.builder.internal.processor;

import io.sundr.builder.Constants;
import io.sundr.builder.Inlineable;
import io.sundr.builder.Visitor;
import io.sundr.builder.annotations.ExternalBuildables;
import io.sundr.builder.annotations.Inline;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.functions.ClazzAs;
import io.sundr.codegen.functions.ClassToJavaType;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaMethodBuilder;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.codegen.utils.ModelUtils;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;

@SupportedAnnotationTypes("io.sundr.builder.annotations.ExternalBuildables")
public class ExternalBuildableProcessor extends AbstractBuilderProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        Elements elements = processingEnv.getElementUtils();

        //First pass register all externals
        for (TypeElement annotation : annotations) {
            for (Element element : env.getElementsAnnotatedWith(annotation)) {
                ExternalBuildables generated = element.getAnnotation(ExternalBuildables.class);
                BuilderContextManager.create(elements, generated.generateBuilderPackage(), generated.builderPackage());
                for (String name : generated.value()) {
                    TypeElement typeElement = elements.getTypeElement(name);
                    BuilderContextManager.getContext().getRepository().register(typeElement);
                }
            }
        }

        for (TypeElement annotation : annotations) {
            for (Element element : env.getElementsAnnotatedWith(annotation)) {
                ExternalBuildables generated = element.getAnnotation(ExternalBuildables.class);
                for (String name : generated.value()) {
                    TypeElement typeElement = elements.getTypeElement(name);
                    if (typeElement == null) {
                        processingEnv
                                .getMessager()
                                .printMessage(Diagnostic.Kind.WARNING, "Type:" + name + " doesn't exists. Ignoring...");
                        continue;
                    }
                    BuilderContext ctx = BuilderContextManager.getContext();
                    JavaClazz clazz = ctx.getToClazz().apply(ModelUtils.getClassElement(typeElement));
                    generateLocalDependenciesIfNeeded();
                    try {
                        generateFromClazz(ClazzAs.FLUENT.apply(clazz),
                                Constants.DEFAULT_FLUENT_TEMPLATE_LOCATION);

                        if (generated.editableEnabled()) {
                            generateFromClazz(ClazzAs.EDITABLE_BUILDER.apply(clazz),
                                    selectBuilderTemplate(generated.validationEnabled()));

                            generateFromClazz(ClazzAs.EDITABLE.apply(clazz),
                                    Constants.DEFAULT_EDITABLE_TEMPLATE_LOCATION);
                        } else {
                            generateFromClazz(ClazzAs.BUILDER.apply(clazz),
                                    selectBuilderTemplate(generated.validationEnabled()));
                        }


                        for (final Inline inline : generated.inline()) {
                            generateFromClazz(inlineableOf(ctx, clazz, inline),
                                    Constants.DEFAULT_CLASS_TEMPLATE_LOCATION);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return true;
    }
}
