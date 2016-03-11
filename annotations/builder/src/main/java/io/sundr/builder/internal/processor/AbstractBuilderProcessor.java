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
import io.sundr.builder.internal.functions.TypeAs;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaMethodBuilder;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaPropertyBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.codegen.processor.JavaGeneratingProcessor;

import javax.lang.model.element.Modifier;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.builder.Constants.BODY;
import static io.sundr.builder.Constants.BOXED_VOID;
import static io.sundr.builder.Constants.EMPTY_FUNCTION_SNIPPET;
import static io.sundr.codegen.utils.StringUtils.loadResourceQuietly;
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

                generateFromClazz(context.getFunctionInterface(),
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
        Set<JavaMethod> constructors = new LinkedHashSet<JavaMethod>();
        JavaType type = clazz.getType();
        JavaType builderType = TypeAs.SHALLOW_BUILDER.apply(type);
        JavaType inlineableType = TypeAs.INLINEABLE.apply(type);

        if (!inline.name().isEmpty()) {
            inlineableType = new JavaTypeBuilder(inlineableType).withClassName(inline.name()).build();
        }

        JavaType returnType = BuilderUtils.getInlineReturnType(ctx, inline);
        if (returnType.equals(BOXED_VOID)) {
            returnType =clazz.getType();
        }

        JavaType functionType = typeGenericOf(ctx.getFunctionInterface().getType(), clazz.getType(), returnType);

        JavaProperty builderProperty = new JavaPropertyBuilder()
                .withType(TypeAs.BUILDER.apply(type))
                .withName("builder")
                .addToModifiers(Modifier.PRIVATE)
                .addToModifiers(Modifier.FINAL)
                .build();

        JavaProperty functionProperty = new JavaPropertyBuilder()
                .withType(functionType)
                .withName("function")
                .addToModifiers(Modifier.PRIVATE)
                .addToModifiers(Modifier.FINAL)
                .build();

        if (returnType.equals(Constants.BOXED_VOID)) {
            returnType = clazz.getType();
        }

        JavaType baseInterface = typeGenericOf(BuilderUtils.getInlineType(ctx, inline), returnType);
        JavaType fluentImpl = TypeAs.FLUENT_IMPL.apply(clazz.getType());
        JavaType fluentInterface = TypeAs.FLUENT_INTERFACE.apply(clazz.getType());

        JavaType shallowInlineType = new JavaTypeBuilder(inlineableType)
                .withClassName(inline.prefix() + inlineableType.getClassName() + inline.suffix())
                .addToInterfaces(baseInterface)
                .build();

        JavaType inlineType = new JavaTypeBuilder(shallowInlineType)
                .withSuperClass(typeGenericOf(fluentImpl, shallowInlineType))
                .addToInterfaces(typeGenericOf(fluentInterface, shallowInlineType))
                .build();

        JavaMethod inlineMethod = new JavaMethodBuilder()
                .withReturnType(returnType)
                .withName(inline.value())
                .addToAttributes(BODY, " return function.apply(builder.build());")
                .addToModifiers(Modifier.PUBLIC)
                .build();


        constructors.add(new JavaMethodBuilder()
                .withReturnType(inlineType)
                .withName("")
                .addNewArgument()
                    .withName("function")
                    .withType(functionType)
                .and()
                .addToModifiers(Modifier.PUBLIC)
                .addToAttributes(BODY, "this.builder=new "+builderType.getSimpleName()+"(this);this.function=function;")
                .build());

        constructors.add(new JavaMethodBuilder()
                .withReturnType(inlineType)
                .withName("")
                .addNewArgument()
                .withName("item")
                .withType(type)
                .and()
                .addNewArgument()
                .withName("function")
                .withType(functionType)
                .and()
                .addToModifiers(Modifier.PUBLIC)
                .addToAttributes(BODY, "this.builder=new "+builderType.getSimpleName()+"(this, item);this.function=function;")
                .build());

        if (clazz.getType().equals(returnType)) {
            constructors.add(new JavaMethodBuilder()
                    .withReturnType(inlineType)
                    .withName("")
                    .addNewArgument()
                    .withName("function")
                    .withType(functionType)
                    .and()
                    .addToModifiers(Modifier.PUBLIC)
                    .addToAttributes(BODY, "this.builder=new " + builderType.getSimpleName() + "(this);this.function=new " + String.format(EMPTY_FUNCTION_TEXT, type.getSimpleName(), type.getSimpleName(), type.getSimpleName(), type.getSimpleName()) + ";")
                    .build());

            constructors.add(new JavaMethodBuilder()
                    .withReturnType(inlineType)
                    .withName("")
                    .addNewArgument()
                    .withName("item")
                    .withType(type)
                    .and()
                    .addToModifiers(Modifier.PUBLIC)
                    .addToAttributes(BODY, "this.builder=new " + builderType.getSimpleName() + "(this, item);this.function=new " + String.format(EMPTY_FUNCTION_TEXT, type.getSimpleName(), type.getSimpleName(), type.getSimpleName(), type.getSimpleName()) + ";")
                    .build());
        }

        return new JavaClazzBuilder()
                .withType(inlineType)
                .withConstructors(constructors)
                .addToFields(builderProperty, functionProperty)
                .addToMethods(inlineMethod)
                .build();
    }

    private static final String EMPTY_FUNCTION_TEXT = loadResourceQuietly(EMPTY_FUNCTION_SNIPPET);
}
