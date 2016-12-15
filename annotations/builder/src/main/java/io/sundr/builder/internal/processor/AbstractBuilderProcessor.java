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
import io.sundr.builder.TypedVisitor;
import io.sundr.builder.Visitor;
import io.sundr.builder.annotations.Inline;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.functions.TypeAs;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.model.AttributeSupportFluent;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.sundr.builder.Constants.EMPTY;
import static io.sundr.builder.Constants.EMPTY_FUNCTION_SNIPPET;
import static io.sundr.codegen.utils.StringUtils.loadResourceQuietly;

public abstract class AbstractBuilderProcessor extends JavaGeneratingProcessor {

    void generateLocalDependenciesIfNeeded() {
        BuilderContext context = BuilderContextManager.getContext();
            try {
                if (context.getGenerateBuilderPackage() && !Constants.DEFAULT_BUILDER_PACKAGE.equals(context.getBuilderPackage())) {

                    generateFromClazz(context.getFluentInterface(),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION
                    );

                    generateFromClazz(context.getPredicateClass(),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION
                    );

                    generateFromClazz(context.getVisitableInterface(),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION
                    );
                    generateFromClazz(context.getVisitorInterface(),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION
                    );
                    generateFromClazz(context.getTypedVisitorInterface(),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION
                    );

                    generateFromClazz(context.getPathAwareVisitorClass(),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION
                    );

                    generateFromClazz(context.getVisitableBuilderInterface(),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION
                    );
                    generateFromClazz(context.getBuilderInterface(),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION
                    );

                    generateFromClazz(context.getFluentInterface(),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION
                    );

                    generateFromClazz(context.getBaseFluentClass(),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION
                    );

                    generateFromClazz(context.getNestedInterface(),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION
                    );
                    generateFromClazz(context.getEditableInterface(),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION
                    );

                    generateFromClazz(context.getFunctionInterface(),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION
                    );
                }

                if (context.isValidationEnabled() &&  !classExists(context.getBuilderPackage() + ".ValidationUtils") ) {
                    generateFromClazz(context.getValidationUtils(),
                            Constants.DEFAULT_SOURCEFILE_TEMPLATE_LOCATION
                    );
                }
            } catch (Exception e) {
                //
            }
    }


    static boolean classExists(String c) {
       try {
           Class.forName(c);
           return true;
       } catch (Exception e) {
           return false;
       }
    }

    static TypeDef inlineableOf(BuilderContext ctx, TypeDef type, Inline inline) {
        final String inlineableName = !inline.name().isEmpty()
                ? inline.name()
                : inline.prefix() + type.getName() + inline.suffix();

        List<Method> constructors = new ArrayList<Method>();
        final TypeDef builderType = TypeAs.BUILDER.apply(type);
        TypeDef inlineType = BuilderUtils.getInlineType(ctx, inline);
        TypeDef returnType = BuilderUtils.getInlineReturnType(ctx, inline, type);
        final ClassRef inlineTypeRef = inlineType.toReference(returnType.toReference());

        //Use the builder as the base of the inlineable. Just add interface and change name.
        final TypeDef shallowInlineType = new TypeDefBuilder(builderType)
                .withName(inlineableName)
                .withImplementsList(inlineTypeRef)
                .withProperties()
                .withMethods()
                .withConstructors().build();

        TypeRef functionType = ctx.getFunctionInterface().toReference(type.toInternalReference(), returnType.toInternalReference());

        Property builderProperty = new PropertyBuilder()
                .withTypeRef(TypeAs.BUILDER.apply(type).toInternalReference())
                .withName(BUILDER)
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PRIVATE, Modifier.FINAL))
                .build();

        Property functionProperty = new PropertyBuilder()
                .withTypeRef(functionType)
                .withName(FUNCTION)
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PRIVATE, Modifier.FINAL))
                .build();



        Method inlineMethod = new MethodBuilder()
                .withReturnType(returnType.toInternalReference())
                .withName(inline.value())
                .withNewBlock()
                    .addNewStringStatementStatement(BUILD_AND_APPLY_FUNCTION)
                .endBlock()
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .build();


        constructors.add(new MethodBuilder()
                .withReturnType(inlineTypeRef)
                .withName(EMPTY)
                .addNewArgument()
                    .withName(FUNCTION)
                    .withTypeRef(functionType)
                .and()
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .withNewBlock()
                    .addNewStringStatementStatement(String.format(NEW_BULDER_AND_SET_FUNCTION_FORMAT, builderType.getName()))
                .endBlock()
                .build());

        constructors.add(new MethodBuilder()
                .withReturnType(inlineTypeRef)
                .withName(EMPTY)
                .addNewArgument()
                    .withName(ITEM)
                    .withTypeRef(type.toReference())
                .and()
                .addNewArgument()
                .withName(FUNCTION)
                    .withTypeRef(functionType)
                .and()
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .withNewBlock()
                    .addNewStringStatementStatement(String.format(NEW_BULDER_WITH_ITEM_AND_SET_FUNCTION_FORMAT, builderType.getName()))
                .endBlock()
                .build());

        if (type.equals(returnType)) {
            constructors.add(new MethodBuilder()
                    .withReturnType(inlineTypeRef)
                    .withName(EMPTY)
                    .addNewArgument()
                    .withName(ITEM)
                    .withTypeRef(type.toReference())
                    .and()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withNewBlock()
                    .addNewStringStatementStatement(String.format(NEW_BUILDER_AND_EMTPY_FUNCTION_FORMAT, builderType.getName(), String.format(EMPTY_FUNCTION_TEXT, type.toInternalReference(), returnType.toInternalReference(),  returnType.toInternalReference(), type.toInternalReference())))
                    .endBlock()
                    .build());
        }

        return new TypeDefBuilder(shallowInlineType)
                .withAnnotations()
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .withConstructors(constructors)
                .addToProperties(builderProperty, functionProperty)
                .addToMethods(inlineMethod)
                .accept(new TypedVisitor<ClassRefBuilder>() {
                    public void visit(ClassRefBuilder builder) {
                        List<TypeRef> updatedArguments = new ArrayList<TypeRef>();
                        for (TypeRef arg : builder.getArguments()) {
                            if (arg.equals(builderType.toInternalReference())) {
                                updatedArguments.add(shallowInlineType.toInternalReference());
                            } else {
                                updatedArguments.add(arg);
                            }
                        }
                        builder.withArguments(updatedArguments);
                    }
                }).build();
    }


    /**
     * Create a mapping from class name to {@link ClassRef}.
     * @return
     */
    public void addCustomMappings(BuilderContext builderContext) {
        DefinitionRepository definitionRepository = builderContext.getDefinitionRepository();

        List<ClassRef> refs = new ArrayList<ClassRef>();
        for (TypeDef typeDef : builderContext.getDefinitionRepository().getDefinitions()) {
            refs.add(typeDef.toInternalReference());
        }

        //It's best to have predictable order, so that we can generate uniform code.
        Collections.sort(refs, new Comparator<ClassRef>() {
            @Override
            public int compare(ClassRef o1, ClassRef o2) {
                return o1.getFullyQualifiedName().compareTo(o2.getFullyQualifiedName());
            }
        });

        for (ClassRef ref : refs) {
            String key = ref.getName();

            if (BuilderUtils.isBuildable(ref)) {

                //Add the builder
                String builderKey = key + "Builder";
                if (!definitionRepository.customMappingExists(builderKey)) {
                    definitionRepository.putCustomMapping(builderKey, ref.getDefinition().getFullyQualifiedName() + "Builder");
                }

                //Add the editable
                String editableKey = "Editable" + key;
                if (!definitionRepository.customMappingExists(editableKey)) {
                    definitionRepository.putCustomMapping(editableKey, "Editable" + ref.getDefinition().getFullyQualifiedName());
                }

                //Add the builder
                String fluentKey = key + "Fluent";
                if (!definitionRepository.customMappingExists(fluentKey)) {
                    definitionRepository.putCustomMapping(fluentKey, ref.getDefinition().getFullyQualifiedName() + "Fluent");
                }

                //Add the builder
                String fluentImplKey = key + "FluentImpl";
                if (!definitionRepository.customMappingExists(fluentImplKey)) {
                    definitionRepository.putCustomMapping(fluentImplKey, ref.getDefinition().getFullyQualifiedName() + "FluentImpl");
                }
            }
        }
    }

    private static final String EMPTY_FUNCTION_TEXT = loadResourceQuietly(EMPTY_FUNCTION_SNIPPET);

    private static final String BUILDER = "builder";
    private static final String FUNCTION = "function";
    private static final String ITEM = "item";

    private static final String NEW_BUILDER_AND_EMTPY_FUNCTION_FORMAT = "super(item);this.builder=new %s(this, item);this.function=new %s;";
    private static final String NEW_BULDER_AND_SET_FUNCTION_FORMAT = "super();this.builder=new %s(this);this.function=function;";
    private static final String NEW_BULDER_WITH_ITEM_AND_SET_FUNCTION_FORMAT = "super(item);this.builder=new %s(this, item);this.function=function;";
    private static final String BUILD_AND_APPLY_FUNCTION = " return function.apply(builder.build());";


}
