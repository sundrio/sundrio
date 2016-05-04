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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.builder.Constants.BODY;
import static io.sundr.builder.Constants.BOXED_VOID;
import static io.sundr.builder.Constants.EMPTY;
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
                generateFromClazz(context.getTypedVisitorInterface(),
                        Constants.DEFAULT_CLASS_TEMPLATE_LOCATION
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

        JavaType typeWithUnboundParameters =  TypeAs.REMOVE_GENERICS_BOUNDS.apply(clazz.getType());
        JavaType builderType = TypeAs.SHALLOW_BUILDER.apply(typeWithUnboundParameters);
        JavaType inlineableType = TypeAs.INLINEABLE.apply(type);

        if (!inline.name().isEmpty()) {
            inlineableType = new JavaTypeBuilder(inlineableType).withClassName(inline.name()).build();
        }

        JavaType returnType = BuilderUtils.getInlineReturnType(ctx, inline);
        if (returnType.equals(BOXED_VOID)) {
            returnType = typeWithUnboundParameters;
        }

        JavaType functionType = typeGenericOf(ctx.getFunctionInterface().getType(), typeWithUnboundParameters, returnType);

        JavaProperty builderProperty = new JavaPropertyBuilder()
                .withType(TypeAs.BUILDER.apply(typeWithUnboundParameters))
                .withName(BUILDER)
                .addToModifiers(Modifier.PRIVATE)
                .addToModifiers(Modifier.FINAL)
                .build();

        JavaProperty functionProperty = new JavaPropertyBuilder()
                .withType(functionType)
                .withName(FUNCTION)
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
                .withInterfaces(baseInterface)
                .build();

        List<JavaType> generics = new ArrayList<JavaType>();
        for (JavaType generic : clazz.getType().getGenericTypes()) {
            generics.add(generic);
        }
        generics.add(shallowInlineType);

        JavaType inlineType = new JavaTypeBuilder(shallowInlineType)
                .withSuperClass(TypeAs.REMOVE_GENERICS_BOUNDS.apply(typeGenericOf(fluentImpl, generics.toArray(new JavaType[generics.size()]))))
                .addToInterfaces(TypeAs.REMOVE_GENERICS_BOUNDS.apply(typeGenericOf(fluentInterface, generics.toArray(new JavaType[generics.size()]))))
                .build();

        JavaMethod inlineMethod = new JavaMethodBuilder()
                .withReturnType(returnType)
                .withName(inline.value())
                .addToAttributes(BODY, BUILD_AND_APPLY_FUNCTION)
                .addToModifiers(Modifier.PUBLIC)
                .build();


        constructors.add(new JavaMethodBuilder()
                .withNewReturnTypeLike(inlineType)
                    .withGenericTypes()
                .endReturnType()
                .withName(EMPTY)
                .addNewArgument()
                    .withName(FUNCTION)
                    .withType(functionType)
                .and()
                .addToModifiers(Modifier.PUBLIC)
                .addToAttributes(BODY, String.format(NEW_BULDER_AND_SET_FUNCTION_FORMAT, builderType.getSimpleName()))
                .build());

        constructors.add(new JavaMethodBuilder()
                .withNewReturnTypeLike(inlineType)
                    .withGenericTypes()
                .endReturnType()
                .withName(EMPTY)
                .addNewArgument()
                .withName(ITEM)
                .withType(typeWithUnboundParameters)
                .and()
                .addNewArgument()
                .withName(FUNCTION)
                .withType(functionType)
                .and()
                .addToModifiers(Modifier.PUBLIC)
                .addToAttributes(BODY, String.format(NEW_BULDER_AND_SET_FUNCTION_FORMAT, builderType.getSimpleName()))
                .build());

        if (clazz.getType().equals(returnType)) {
            constructors.add(new JavaMethodBuilder()
                    .withNewReturnTypeLike(inlineType)
                        .withGenericTypes()
                    .endReturnType()
                    .withName(EMPTY)
                    .addNewArgument()
                    .withName(FUNCTION)
                    .withType(functionType)
                    .and()
                    .addToModifiers(Modifier.PUBLIC)
                    .addToAttributes(BODY, String.format(NEW_BUILDER_AND_EMTPY_FUNCTION_FORMAT, builderType.getSimpleName(), String.format(EMPTY_FUNCTION_TEXT, typeWithUnboundParameters.getSimpleName(), typeWithUnboundParameters.getSimpleName(), typeWithUnboundParameters.getSimpleName(), typeWithUnboundParameters.getSimpleName())))
                    .build());

            constructors.add(new JavaMethodBuilder()
                    .withNewReturnTypeLike(inlineType)
                        .withGenericTypes()
                    .endReturnType()
                    .withName(EMPTY)
                    .addNewArgument()
                    .withName(ITEM)
                    .withType(typeWithUnboundParameters)
                    .and()
                    .addToModifiers(Modifier.PUBLIC)
                    .addToAttributes(BODY, String.format(NEW_BUILDER_AND_EMTPY_FUNCTION_FORMAT, builderType.getSimpleName(), String.format(EMPTY_FUNCTION_TEXT, typeWithUnboundParameters.getSimpleName(), typeWithUnboundParameters.getSimpleName(), typeWithUnboundParameters.getSimpleName(), typeWithUnboundParameters.getSimpleName())))
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

    private static final String BUILDER = "builder";
    private static final String FUNCTION = "function";
    private static final String ITEM = "item";

    private static final String NEW_BUILDER_AND_EMTPY_FUNCTION_FORMAT = "this.builder=new %s(this, item);this.function=new %s;";
    private static final String NEW_BULDER_AND_SET_FUNCTION_FORMAT = "this.builder=new %s(this);this.function=function;";
    private static final String BUILD_AND_APPLY_FUNCTION = " return function.apply(builder.build());";


}
