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
import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.annotations.Inline;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.functions.ClazzAs;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.codegen.functions.ElementTo;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.utils.ModelUtils;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.io.IOException;
import java.util.Set;

import static io.sundr.builder.Constants.VALIDATION_ENABLED;

@SupportedAnnotationTypes("io.sundr.builder.annotations.Buildable")
public class BuildableProcessor extends AbstractBuilderProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        Elements elements = processingEnv.getElementUtils();
        Types types = processingEnv.getTypeUtils();
        Filer filer = processingEnv.getFiler();

        //First pass register all buildables
        for (TypeElement typeElement : annotations) {
            for (Element element : env.getElementsAnnotatedWith(typeElement)) {
                Buildable buildable = element.getAnnotation(Buildable.class);
                BuilderContext ctx = BuilderContextManager.create(elements, types, buildable.generateBuilderPackage(), buildable.builderPackage());
                ctx.getBuildableRepository().register(ElementTo.TYPEDEF.apply(ModelUtils.getClassElement(element)));
                for (TypeElement ref : BuilderUtils.getBuildableReferences(ctx, buildable)) {
                    ctx.getBuildableRepository().register(ElementTo.TYPEDEF.apply(ModelUtils.getClassElement(ref)));
                }
            }
        }

        for (TypeElement typeElement : annotations) {
            for (Element element : env.getElementsAnnotatedWith(typeElement)) {
                boolean isAbstract = element.getModifiers().contains(Modifier.ABSTRACT);
                Buildable buildable = element.getAnnotation(Buildable.class);
                BuilderContext ctx = BuilderContextManager.create(elements, types, buildable.generateBuilderPackage(), buildable.builderPackage());
                TypeDef typeDef = new TypeDefBuilder(ElementTo.TYPEDEF.apply(ModelUtils.getClassElement(element))).addToAttributes(VALIDATION_ENABLED, buildable.validationEnabled()).build();
                generateLocalDependenciesIfNeeded();

                try {
                    generateFromClazz(ClazzAs.FLUENT_INTERFACE.apply(typeDef),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION);

                    generateFromClazz(ClazzAs.FLUENT_IMPL.apply(typeDef),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION);

                    if (isAbstract) {
                      //ignore and move along
                    } else if (buildable.editableEnabled()) {
                        generateFromClazz(ClazzAs.EDITABLE_BUILDER.apply(typeDef),
                                Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION);

                        generateFromClazz(ClazzAs.EDITABLE.apply(typeDef),
                                Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION);
                    } else {
                        generateFromClazz(ClazzAs.BUILDER.apply(typeDef),
                                Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION);
                    }

                    for (final Inline inline : buildable.inline()) {
                        generateFromClazz(inlineableOf(ctx, typeDef, inline),
                                Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }
}
