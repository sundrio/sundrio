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
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.processor.JavaGeneratingProcessor;
import io.sundr.codegen.utils.TypeUtils;

import javax.lang.model.element.Modifier;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.builder.Constants.BODY;
import static io.sundr.builder.Constants.BOXED_VOID;
import static io.sundr.builder.Constants.EMPTY;
import static io.sundr.builder.Constants.EMPTY_FUNCTION_SNIPPET;
import static io.sundr.codegen.utils.StringUtils.loadResourceQuietly;
import static io.sundr.codegen.utils.TypeUtils.classRefOf;

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


    TypeDef inlineableOf(BuilderContext ctx, TypeDef type, Inline inline) {
        Set<Method> constructors = new LinkedHashSet<Method>();

        TypeDef builderType = TypeAs.SHALLOW_BUILDER.apply(type);
        TypeDef inlineableType = TypeAs.INLINEABLE.apply(type);

        if (!inline.name().isEmpty()) {
            inlineableType = new TypeDefBuilder(inlineableType).withName(inline.name()).build();
        }

        TypeRef returnType = BuilderUtils.getInlineReturnType(ctx, inline);
        if (returnType.equals(BOXED_VOID)) {
            returnType = classRefOf(type);
        }

        TypeRef functionType = classRefOf(ctx.getFunctionInterface(), type, returnType);

        Property builderProperty = new PropertyBuilder()
                .withTypeRef(classRefOf(TypeAs.BUILDER.apply(type)))
                .withName(BUILDER)
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PRIVATE, Modifier.FINAL))
                .build();

        Property functionProperty = new PropertyBuilder()
                .withTypeRef(functionType)
                .withName(FUNCTION)
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PRIVATE, Modifier.FINAL))
                .build();

        if (returnType.equals(Constants.BOXED_VOID)) {
            returnType = classRefOf(type);
        }

        ClassRef baseInterface = classRefOf(BuilderUtils.getInlineType(ctx, inline), returnType);

        TypeDef shallowInlineType = new TypeDefBuilder(inlineableType)
                .withName(inline.prefix() + inlineableType.getName() + inline.suffix())
                .addToImplementsList(baseInterface)
                .build();


        TypeRef inlineType =classRefOf(shallowInlineType);

        Method inlineMethod = new MethodBuilder()
                .withReturnType(returnType)
                .withName(inline.value())
                .addToAttributes(BODY, BUILD_AND_APPLY_FUNCTION)
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .build();


        constructors.add(new MethodBuilder()
                .withReturnType(inlineType)
                .withName(EMPTY)
                .addNewArgument()
                    .withName(FUNCTION)
                    .withTypeRef(functionType)
                .and()
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .addToAttributes(BODY, String.format(NEW_BULDER_AND_SET_FUNCTION_FORMAT, builderType.getName()))
                .build());

        constructors.add(new MethodBuilder()
                .withReturnType(inlineType)
                .withName(EMPTY)
                .addNewArgument()
                    .withName(ITEM)
                    .withTypeRef(classRefOf(type))
                .and()
                .addNewArgument()
                .withName(FUNCTION)
                    .withTypeRef(functionType)
                .and()
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .addToAttributes(BODY, String.format(NEW_BULDER_AND_SET_FUNCTION_FORMAT, builderType.getName()))
                .build());

        if (type.equals(returnType)) {
            constructors.add(new MethodBuilder()
                    .withReturnType(inlineType)
                    .withName(EMPTY)
                    .addNewArgument()
                    .withName(FUNCTION)
                    .withTypeRef(functionType)
                    .and()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .addToAttributes(BODY, String.format(NEW_BUILDER_AND_EMTPY_FUNCTION_FORMAT, builderType.getName(), String.format(EMPTY_FUNCTION_TEXT, type.getName(), type.getName(), type.getName(), type.getName())))
                    .build());

            constructors.add(new MethodBuilder()
                    .withReturnType(inlineType)
                    .withName(EMPTY)
                    .addNewArgument()
                    .withName(ITEM)
                    .withTypeRef(classRefOf(type))
                    .and()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .addToAttributes(BODY, String.format(NEW_BUILDER_AND_EMTPY_FUNCTION_FORMAT, builderType.getName(), String.format(EMPTY_FUNCTION_TEXT, type.getName(), type.getName(), type.getName(), type.getName())))
                    .build());
        }

        return new TypeDefBuilder(inlineableType)
                .withConstructors(constructors)
                .addToProperties(builderProperty, functionProperty)
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
