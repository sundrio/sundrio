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

import io.sundr.Function;
import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.annotations.ExternalBuildables;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.functions.ClazzAs;
import io.sundr.builder.internal.functions.overrides.ToBuildableJavaProperty;
import io.sundr.builder.internal.functions.overrides.ToBuildableJavaType;
import io.sundr.codegen.coverters.JavaClazzFunction;
import io.sundr.codegen.coverters.JavaMethodFunction;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.processor.JavaGeneratingProcessor;
import io.sundr.codegen.utils.ModelUtils;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.Set;

@SupportedAnnotationTypes("io.sundr.builder.annotations.Buildable")
public class BuildableProcessor extends AbstractBuilderProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        Elements elements = processingEnv.getElementUtils();
        Function<String, JavaType> toType = new ToBuildableJavaType(elements);
        Function<VariableElement, JavaProperty> toProperty = new ToBuildableJavaProperty(toType);
        JavaMethodFunction toMethod = new JavaMethodFunction(toType, toProperty);
        JavaClazzFunction toClazz = new JavaClazzFunction(elements, toType, toMethod, toProperty);

        for (TypeElement typeElement : annotations) {
            for (Element element : env.getElementsAnnotatedWith(typeElement)) {
                Buildable buildable = element.getAnnotation(Buildable.class);
                if (element instanceof ExecutableElement) {
                    JavaClazz clazz = toClazz.apply(ModelUtils.getClassElement(element));
                    if (buildable.nodeps()) {
                        BuilderContextManager.create(clazz.getType().getPackageName());
                        generateLocalDependencies();
                    } else {
                        BuilderContextManager.create();
                    }
                    try {
                        generateFromClazz(ClazzAs.BUILDER.apply(clazz),
                                DEFAULT_BUILDER_TEMPLATE_LOCATION);

                        generateFromClazz(ClazzAs.FLUENT.apply(clazz),
                                DEFAULT_FLUENT_TEMPLATE_LOCATION);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return true;
    }
}
