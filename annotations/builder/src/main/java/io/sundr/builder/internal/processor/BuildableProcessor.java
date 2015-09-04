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
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.utils.ModelUtils;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.Set;

@SupportedAnnotationTypes("io.sundr.builder.annotations.Buildable")
public class BuildableProcessor extends AbstractBuilderProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        Elements elements = processingEnv.getElementUtils();

        //First pass register all buildables
        for (TypeElement typeElement : annotations) {
            for (Element element : env.getElementsAnnotatedWith(typeElement)) {
                Buildable buildable = element.getAnnotation(Buildable.class);
                BuilderContext ctx = BuilderContextManager.create(elements, buildable.generateBuilderPackage(), buildable.builderPackage());
                ctx.getRepository().register(ModelUtils.getClassElement(element));
                for (TypeElement ref : BuilderUtils.getBuildableReferences(ctx, buildable)) {
                    ctx.getRepository().register(ModelUtils.getClassElement(ref));
                }
            }
        }

        for (TypeElement typeElement : annotations) {
            for (Element element : env.getElementsAnnotatedWith(typeElement)) {
                Buildable buildable = element.getAnnotation(Buildable.class);
                BuilderContext ctx = BuilderContextManager.create(elements, buildable.generateBuilderPackage(), buildable.builderPackage());
                JavaClazz clazz = ctx.getTypeElementToJavaClazz().apply(ModelUtils.getClassElement(element));
                generateLocalDependenciesIfNeeded();
                try {
                    generateFromClazz(ClazzAs.FLUENT.apply(clazz),
                            Constants.DEFAULT_FLUENT_TEMPLATE_LOCATION);

                    if (buildable.editableEnabled()) {
                        generateFromClazz(ClazzAs.EDITABLE_BUILDER.apply(clazz),
                                selectBuilderTemplate(buildable.validationEnabled()));

                        generateFromClazz(ClazzAs.EDITABLE.apply(clazz),
                                Constants.DEFAULT_EDITABLE_TEMPLATE_LOCATION);
                    } else {
                        generateFromClazz(ClazzAs.BUILDER.apply(clazz),
                                selectBuilderTemplate(buildable.validationEnabled()));
                    }

                    for (final Inline inline : buildable.inline()) {
                        generateFromClazz(inlineableOf(ctx, clazz, inline),
                                Constants.DEFAULT_CLASS_TEMPLATE_LOCATION);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }
}
