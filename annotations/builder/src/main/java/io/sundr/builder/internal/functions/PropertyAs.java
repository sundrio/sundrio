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
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamRefBuilder;
import io.sundr.codegen.model.TypeRef;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.builder.Constants.BODY;
import static io.sundr.builder.Constants.MEMBER_OF;
import static io.sundr.builder.Constants.N;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_ARRAY_OF;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_COLLECTION_OF;
import static io.sundr.codegen.utils.StringUtils.captializeFirst;
import static io.sundr.codegen.utils.TypeUtils.classRefOf;

public final class PropertyAs {

    private PropertyAs() {
    }

    public static final Function<Property, TypeDef> NESTED_CLASS = new Function<Property, TypeDef>() {

        public TypeDef apply(Property item) {
            TypeRef unwrapped = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getTypeRef());

            if (unwrapped instanceof ClassRef) {
                TypeDef baseType = ((ClassRef) unwrapped).getDefinition();
                TypeDef builderType = TypeAs.SHALLOW_BUILDER.apply(baseType);

                TypeDef nestedType = NESTED_TYPE.apply(item);
                TypeRef nestedRef = classRefOf(nestedType);

                Set<ClassRef> nestedInterfaces = new HashSet<ClassRef>();
                for (ClassRef n : nestedType.getImplementsList()) {
                    nestedInterfaces.add(n);
                }

                //nestedType = new TypeDefBuilder(nestedType).withInterfaces(nestedInterfaces.toArray(new TypeDef[nestedInterfaces.size()])).build();
                //TypeDef nestedUnwrapped = new TypeDefBuilder(nestedType).withGenericTypes(new TypeDef[0]).build();

                Set<Method> nestedMethods = new HashSet<Method>();
                nestedMethods.add(ToMethod.AND.apply(item));
                nestedMethods.add(ToMethod.END.apply(item));

                Set<Property> properties = new HashSet<Property>();
                Set<Method> constructors = new HashSet<Method>();

                TypeDef memberOf = (TypeDef) item.getAttributes().get(MEMBER_OF);

                properties.add(new PropertyBuilder()
                        .withName("builder")
                        .withTypeRef(classRefOf(builderType)).build());

                constructors.add(new MethodBuilder()
                        .withName("")
                        .withReturnType(nestedRef)
                        .addNewArgument()
                        .withName("item")
                        .withTypeRef(item.getTypeRef())
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
                        .withProperties(properties)
                        .withMethods(nestedMethods)
                        .withConstructors(constructors)
                        .addToAttributes(MEMBER_OF, memberOf)
                        .build();
            }
            throw new IllegalStateException();
        }
    };

    public static final Function<Property, TypeDef> NESTED_INTERFACE = new Function<Property, TypeDef>() {
        public TypeDef apply(Property item) {


            TypeRef unwrapped = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getTypeRef());

            if (unwrapped instanceof ClassRef) {
                TypeDef baseType = ((ClassRef) unwrapped).getDefinition();
                TypeDef builderType = TypeAs.SHALLOW_BUILDER.apply(baseType);

                TypeDef nestedType = NESTED_INTERFACE_TYPE.apply(item);
                TypeRef nestedRef = classRefOf(nestedType);

                Set<ClassRef> nestedInterfaces = new HashSet<ClassRef>();
                for (ClassRef n : nestedType.getImplementsList()) {
                    nestedInterfaces.add(n);
                }


                Set<Method> nestedMethods = new HashSet<Method>();
                nestedMethods.add(ToMethod.AND.apply(item));
                nestedMethods.add(ToMethod.END.apply(item));

                Set<Property> properties = new HashSet<Property>();
                Set<Method> constructors = new HashSet<Method>();

                TypeDef memberOf = (TypeDef) item.getAttributes().get(MEMBER_OF);
                properties.add(new PropertyBuilder()
                        .withName("builder")
                        .withTypeRef(classRefOf(builderType)).build());

                constructors.add(new MethodBuilder()
                        .withName("")
                        .withReturnType(nestedRef)
                        .addNewArgument()
                        .withName("item")
                        .withTypeRef(classRefOf(baseType))
                        .endArgument()
                        .addToAttributes(BODY, "this.builder = new " + builderType.getName() + "(this, item);")
                        .build());

                constructors.add(new MethodBuilder()
                        .withName("")
                        .withReturnType(nestedRef)
                        .addToAttributes(BODY, "this.builder = new " + builderType.getName() + "(this);")
                        .build());

                return new TypeDefBuilder(nestedType)
                        .withProperties(properties)
                        .withMethods(nestedMethods)
                        .withConstructors(constructors)
                        .addToAttributes(MEMBER_OF, memberOf)
                        .build();
            }
            throw new IllegalStateException();
        }
    };




        public static final Function<Property, TypeDef> NESTED_TYPE = new Function<Property, TypeDef>() {
            public TypeDef apply(Property item) {

                TypeDef nested = SHALLOW_NESTED_TYPE.apply(item);
                //Not a typical fluent

                TypeRef typeRef = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getTypeRef());
                TypeDef typeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition(typeRef);

                List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();
                List<TypeRef> superClassParameters = new ArrayList<TypeRef>();

                for (TypeParamDef parameter : typeDef.getParameters()) {
                    parameters.add(parameter);
                    superClassParameters.add(new TypeParamRefBuilder().withName(parameter.getName()).build());
                }
                parameters.add(N);


                ClassRef superClassFluent = new ClassRefBuilder()
                        .withNewDefinition()
                        .withName(typeDef.getName() + "FluentImpl")
                        .withPackageName(typeDef.getPackageName())
                        .endDefinition()
                        .withArguments(superClassParameters)
                        .build();

                return new TypeDefBuilder(nested)
                        .withKind(Kind.CLASS)
                        .withName(nested.getName() + "Impl")
                        .withParameters(parameters)
                        .withExtendsList(superClassFluent)
                        .withImplementsList(BuilderContextManager.getContext().getNestedInterface().toReference())
                        .build();
            }

        };

        public static final Function<Property, TypeDef> NESTED_INTERFACE_TYPE = new Function<Property, TypeDef>() {
            public TypeDef apply(Property item) {
                TypeDef nested = SHALLOW_NESTED_TYPE.apply(item);
                //Not a typical fluent

                TypeRef typeRef = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getTypeRef());
                TypeDef typeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition(typeRef);

                List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();
                List<TypeRef> superClassParameters = new ArrayList<TypeRef>();

                for (TypeParamDef parameter : typeDef.getParameters()) {
                    parameters.add(parameter);
                    superClassParameters.add(new TypeParamRefBuilder().withName(parameter.getName()).build());
                }
                parameters.add(N);


                //We need to rid of the bounds: For example: Circle<T extends Number> implements Shape<T extends Shape>
                //We only need To define T bounds once.
                superClassParameters.add(classRefOf(nested));


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
                        .withParameters(parameters)
                        .addToExtendsList(classRefOf(BuilderContextManager.getContext().getNestedInterface()), superClassFluent)
                        .build();
            }
        };

        public static final Function<Property, TypeDef> SHALLOW_NESTED_TYPE = new Function<Property, TypeDef>() {
            public TypeDef apply(Property property) {
                TypeRef typeRef = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getTypeRef());
                TypeDef typeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition(typeRef);
                List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();
                for (TypeParamDef generic : typeDef.getParameters()) {
                    parameters.add(generic);
                }
                parameters.add(N);

                return new TypeDefBuilder(typeDef)
                        .withName(captializeFirst(property.getName() + "Nested"))
                        .withParameters(parameters)
                        .build();
            }
        };

    }
