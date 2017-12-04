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

package io.sundr.builder.internal.functions;

import io.sundr.Function;
import io.sundr.builder.Constants;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.ModifierSupport;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.Statement;
import io.sundr.codegen.model.StringStatement;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.utils.TypeUtils;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.builder.Constants.INDEX;
import static io.sundr.builder.Constants.N;
import static io.sundr.builder.Constants.OUTER_CLASS;
import static io.sundr.builder.Constants.OUTER_INTERFACE;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_ARRAY_OF;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_COLLECTION_OF;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_OPTIONAL_OF;
import static io.sundr.codegen.utils.StringUtils.captializeFirst;

public final class PropertyAs {

    private PropertyAs() {
    }

    public static final Function<Property, TypeDef> NESTED_CLASS = new Function<Property, TypeDef>() {

        public TypeDef apply(Property item) {
            boolean isArray = TypeUtils.isArray(item.getTypeRef());
            boolean isList = TypeUtils.isList(item.getTypeRef());

            TypeRef unwrapped = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(item.getTypeRef());

            if (unwrapped instanceof ClassRef) {
                TypeDef baseType = ((ClassRef) unwrapped).getDefinition();
                ClassRef builderType = TypeAs.SHALLOW_BUILDER.apply(baseType).toReference();

                TypeDef nestedType = NESTED_CLASS_TYPE.apply(item);
                TypeRef nestedRef = nestedType.toReference();

                Set<ClassRef> nestedInterfaces = new HashSet<ClassRef>();
                for (ClassRef n : nestedType.getImplementsList()) {
                    nestedInterfaces.add(n);
                }

                //nestedType = new TypeDefBuilder(nestedType).withInterfaces(nestedInterfaces.toArray(new TypeDef[nestedInterfaces.size()])).build();
                //TypeDef nestedUnwrapped = new TypeDefBuilder(nestedType).withGenericTypes(new TypeDef[0]).build();

                List<Method> nestedMethods = new ArrayList<Method>();
                nestedMethods.add(ToMethod.AND.apply(item));
                nestedMethods.add(ToMethod.END.apply(item));

                List<Property> properties = new ArrayList<Property>();
                List<Method> constructors = new ArrayList<Method>();
                List<Statement> statementsWithItem = new ArrayList<Statement>();
                List<Statement> statementsWithoutItem = new ArrayList<Statement>();

                properties.add(new PropertyBuilder()
                        .withName("builder")
                        .withTypeRef(builderType).build());

                List<Property> arguments = new ArrayList<Property>();
                if (isArray || isList) {
                    arguments.add(INDEX);
                    properties.add(INDEX);
                    statementsWithItem.add(new StringStatement("this.index = index;"));
                    statementsWithoutItem.add(new StringStatement("this.index = -1;"));
                }
                arguments.add(new PropertyBuilder().withName("item").withTypeRef(unwrapped).build());

                statementsWithItem.add(new StringStatement("this.builder = new " + builderType.getName() + "(this, item);"));
                constructors.add(new MethodBuilder()
                        .withName("")
                        .withReturnType(nestedRef)
                        .withArguments(arguments)
                        .withNewBlock()
                            .withStatements(statementsWithItem)
                        .endBlock()
                        .build());

                statementsWithoutItem.add(new StringStatement("this.builder = new " + builderType.getName() + "(this);"));
                constructors.add(new MethodBuilder()
                        .withName("")
                        .withReturnType(nestedRef)
                        .withNewBlock()
                            .withStatements(statementsWithoutItem)
                        .endBlock()
                        .build());

                return new TypeDefBuilder(nestedType)
                        .withAnnotations()
                        .withProperties(properties)
                        .withMethods(nestedMethods)
                        .withConstructors(constructors)
                        .build();
            }
            throw new IllegalStateException();
        }
    };

    public static final Function<Property, TypeDef> NESTED_INTERFACE = new Function<Property, TypeDef>() {
        public TypeDef apply(Property item) {


            TypeRef unwrapped = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(item.getTypeRef());
            //TypeRef unwrapped = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getTypeRef());

            if (unwrapped instanceof ClassRef) {
                TypeDef baseType = ((ClassRef) unwrapped).getDefinition();
                TypeDef builderType = TypeAs.SHALLOW_BUILDER.apply(baseType);

                TypeDef nestedType = NESTED_INTERFACE_TYPE.apply(item);
                TypeRef nestedRef = nestedType.toReference();

                Set<ClassRef> nestedInterfaces = new HashSet<ClassRef>();
                for (ClassRef n : nestedType.getImplementsList()) {
                    nestedInterfaces.add(n);
                }


                List<Method> nestedMethods = new ArrayList<Method>();
                nestedMethods.add(ToMethod.AND.apply(item));
                nestedMethods.add(ToMethod.END.apply(item));

                List<Property> properties = new ArrayList<Property>();
                List<Method> constructors = new ArrayList<Method>();

                properties.add(new PropertyBuilder()
                        .withName("builder")
                        .withTypeRef(builderType.toReference()).build());

                constructors.add(new MethodBuilder()
                        .withName("")
                        .withReturnType(nestedRef)
                        .addNewArgument()
                        .withName("item")
                        .withTypeRef(baseType.toReference())
                        .endArgument()
                        .withNewBlock()
                            .addNewStringStatementStatement("this.builder = new " + builderType.getName() + "(this, item);")
                        .endBlock()
                        .build());

                constructors.add(new MethodBuilder()
                        .withName("")
                        .withReturnType(nestedRef)
                        .withNewBlock()
                            .addNewStringStatementStatement("this.builder = new " + builderType.getName() + "(this);")
                        .endBlock()
                        .build());

                return new TypeDefBuilder(nestedType)
                        .withAnnotations()
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withProperties(properties)
                        .withMethods(nestedMethods)
                        .withConstructors(constructors)
                        .build();
            }
            throw new IllegalStateException();
        }
    };


        public static final Function<Property, TypeDef> NESTED_CLASS_TYPE = new Function<Property, TypeDef>() {
            public TypeDef apply(Property item) {
                TypeDef shallowNestedType = SHALLOW_NESTED_TYPE.apply(item);
                TypeDef nestedInterfaceType = NESTED_INTERFACE_TYPE.apply(item);
                TypeDef outerClass = item.getAttribute(OUTER_CLASS);

                TypeDef nested = new TypeDefBuilder(shallowNestedType)
                        .withPackageName(outerClass.getPackageName())
                        .withName(shallowNestedType.getName() + "Impl")
                        .withOuterType(outerClass)
                        .build();

                //Not a typical fluent
                TypeRef typeRef = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(item.getTypeRef());
                TypeDef typeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition(typeRef);

                if (typeDef == null) {
                    if (typeRef instanceof ClassRef) {
                        typeDef = ((ClassRef)typeRef).getDefinition();
                    } else {
                        throw new IllegalStateException("Could not find definition from property: ["+item+"] neither in the repo nor via the object tree.");
                    }
                }

                List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();
                List<TypeRef> superClassParameters = new ArrayList<TypeRef>();

                for (TypeParamDef parameter : typeDef.getParameters()) {
                    parameters.add(parameter);
                    superClassParameters.add(parameter.toReference());
                }
                parameters.add(N);
                List<TypeRef> pivotParameters = new ArrayList<TypeRef>(superClassParameters);
                pivotParameters.add(N.toReference());

                ClassRef nestedInterfaceRef = nestedInterfaceType.toReference(pivotParameters);
                superClassParameters.add(nestedInterfaceRef);

                ClassRef superClassFluent = new ClassRefBuilder()
                        .withNewDefinition()
                        .withName(typeDef.getName() + "FluentImpl")
                        .withPackageName(typeDef.getPackageName())
                        .endDefinition()
                        .withArguments(superClassParameters)
                        .build();

                return new TypeDefBuilder(nested)
                        .withKind(Kind.CLASS)
                        .withParameters(parameters)
                        .withExtendsList(superClassFluent)
                        .withImplementsList(nestedInterfaceRef, BuilderContextManager.getContext().getNestedInterface().toReference(N.toReference()))
                        .build();
            }

        };

        public static final Function<Property, TypeDef> NESTED_INTERFACE_TYPE = new Function<Property, TypeDef>() {
            public TypeDef apply(Property item) {
                TypeDef nested = new TypeDefBuilder(SHALLOW_NESTED_TYPE.apply(item)).withOuterType(item.getAttribute(OUTER_INTERFACE)).build();
                TypeDef outerInterface = item.getAttribute(OUTER_INTERFACE);
                //Not a typical fluent

                TypeRef typeRef = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(item.getTypeRef());
                //TypeRef typeRef = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getTypeRef());
                TypeDef typeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition(typeRef);

                if (typeDef == null) {
                    if (typeRef instanceof ClassRef) {
                        typeDef = ((ClassRef)typeRef).getDefinition();
                    } else {
                        throw new IllegalStateException("Could not find definition from property: ["+item+"] neither in the repo nor via the object tree.");
                    }
                }

                List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();
                List<TypeRef> superClassParameters = new ArrayList<TypeRef>();

                for (TypeParamDef parameter : typeDef.getParameters()) {
                    parameters.add(parameter);
                    superClassParameters.add(parameter.toReference());
                }
                parameters.add(N);
                List<TypeRef> pivotParameters = new ArrayList<TypeRef>(superClassParameters);
                pivotParameters.add(N.toReference());
                superClassParameters.add(nested.toReference(pivotParameters));

                //CircleFluent<T, CircleShapesNested<T, N>>
                ClassRef superClassFluent = new ClassRefBuilder()
                        .withNewDefinition()
                        .withName(typeDef.getName() + "Fluent")
                        .withPackageName(typeDef.getPackageName())
                        .endDefinition()
                        .withArguments(superClassParameters)
                        .build();

                return new TypeDefBuilder(nested)
                        .withKind(Kind.INTERFACE)
                        .withPackageName(outerInterface.getPackageName())
                        .withParameters(parameters)
                        .withOuterType(outerInterface)
                        .withImplementsList()
                        .withExtendsList(BuilderContextManager.getContext().getNestedInterface().toReference(N.toReference()), superClassFluent)
                        .build();
            }
        };

        public static final Function<Property, TypeDef> SHALLOW_NESTED_TYPE = new Function<Property, TypeDef>() {
            public TypeDef apply(Property property) {
                TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);


                TypeRef typeRef = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());
                TypeDef typeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition(typeRef);

                if (typeDef == null) {
                    if (typeRef instanceof ClassRef) {
                        typeDef = ((ClassRef)typeRef).getDefinition();
                    } else {
                        throw new IllegalStateException("Could not find definition from property: ["+property+"] neither in the repo nor via the object tree.");
                    }
                }

                TypeDef outerInterface = property.getAttribute(OUTER_INTERFACE);

                List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();
                for (TypeParamDef generic : typeDef.getParameters()) {
                    parameters.add(generic);
                }
                parameters.add(N);

                return new TypeDefBuilder(typeDef)
                        .withPackageName(outerInterface.getPackageName())
                        .withName(BuilderUtils.fullyQualifiedNameDiff(property.getTypeRef(), originTypeDef) + property.getNameCapitalized() + "Nested")
                        .withParameters(parameters)
                        .build();
            }
        };

    }
