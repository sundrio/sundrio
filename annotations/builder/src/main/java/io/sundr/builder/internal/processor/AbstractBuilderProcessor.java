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
import io.sundr.builder.annotations.Inline;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.functions.ClazzAs;
import io.sundr.builder.internal.functions.TypeAs;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaMethodBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.codegen.processor.JavaGeneratingProcessor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;

public abstract class AbstractBuilderProcessor extends JavaGeneratingProcessor {

    void generateLocalDependenciesIfNeeded() {
        BuilderContext context = BuilderContextManager.getContext();
        if (context.getGenerateBuilderPackage() && !Constants.DEFAULT_BUILDER_PACKAGE.equals(context.getBuilderPackage())) {
            try {
                generateFromClazz(context.getVisitableInterface(),
                        Constants.DEFAULT_INTERFACE_TEMPLATE_LOCATION
                );
                generateFromClazz(context.getVisitorInterface(),
                        Constants.DEFAULT_INTERFACE_TEMPLATE_LOCATION
                );
                generateFromClazz(context.getVisitableBuilderInterface(),
                        Constants.DEFAULT_INTERFACE_TEMPLATE_LOCATION
                );
                generateFromClazz(context.getBuilderInterface(),
                        Constants.DEFAULT_INTERFACE_TEMPLATE_LOCATION
                );

                generateFromClazz(context.getFluentInterface(),
                        Constants.DEFAULT_INTERFACE_TEMPLATE_LOCATION
                );

                generateFromClazz(context.getBaseFluentClass(),
                        Constants.DEFAULT_CLASS_TEMPLATE_LOCATION
                );

                generateFromClazz(context.getNestedInterface(),
                        Constants.DEFAULT_INTERFACE_TEMPLATE_LOCATION
                );
                generateFromClazz(context.getEditableInterface(),
                        Constants.DEFAULT_INTERFACE_TEMPLATE_LOCATION
                );
            } catch (Exception e) {
                //
            }
        }
    }

    /**
     * Selects a builder template based on the criteria.
     * @param validationEnabled Flag that indicates if validationEnabled is enabled.
     * @return
     */
    String selectBuilderTemplate(boolean validationEnabled) {
        if (validationEnabled) {
            return Constants.VALIDATING_BUILDER_TEMPLATE_LOCATION;
        } else {
            return Constants.DEFAULT_BUILDER_TEMPLATE_LOCATION;
        }
    }


    JavaClazz inlineableOf(BuilderContext ctx, JavaClazz clazz, Inline inline) {
        JavaClazz base = ClazzAs.INLINEABLE.apply(clazz);
        JavaType baseInterface = typeGenericOf(BuilderUtils.getInlineType(ctx, inline), clazz.getType());
        JavaMethod method = new JavaMethodBuilder(base.getMethods().iterator().next()).withName(inline.value()).build();

        JavaType fluent = TypeAs.FLUENT_IMPL.apply(clazz.getType());
        JavaType shallowInlineType = new JavaTypeBuilder(base.getType())
                .withClassName(inline.prefix() + base.getType().getClassName() + inline.suffix())
                .addToInterfaces(baseInterface)
                .build();

        JavaType inlineType = new JavaTypeBuilder(shallowInlineType)
                .withSuperClass(typeGenericOf(fluent, shallowInlineType))
                .build();

        Set<JavaMethod> constructors = new LinkedHashSet<JavaMethod>();
        for (JavaMethod constructor : base.getConstructors()) {
            constructors.add(new JavaMethodBuilder(constructor).withReturnType(inlineType).build());
        }

        return new JavaClazzBuilder(base)
                .withType(inlineType)
                .withConstructors(constructors)
                .withMethods(new HashSet<JavaMethod>(Arrays.asList(method)))
                .build();
    }
}
