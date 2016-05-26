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
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;

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

public final class PropertyAs {
    
    private PropertyAs() {}

    public static final Function<Property, TypeDef> NESTED_CLASS = new Function<Property, TypeDef>() {

        @Override
        public TypeDef apply(Property item) {
            JavaType baseType = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getTypeRef());
            JavaType builderType = TypeAs.SHALLOW_BUILDER.apply(baseType);

            JavaType nestedType = NESTED_TYPE.apply(item);
            Set<JavaType> nestedInterfaces = new HashSet<JavaType>();
            for (JavaType n : nestedType.getInterfaces()) {
                nestedInterfaces.add(TypeAs.REMOVE_GENERICS_BOUNDS.apply(n));
            }
            nestedType = new JavaTypeBuilder(nestedType).withInterfaces(nestedInterfaces.toArray(new JavaType[nestedInterfaces.size()])).build();
            JavaType nestedUnwrapped = new JavaTypeBuilder(nestedType).withGenericTypes(new JavaType[0]).build();

            Set<Method> nestedMethods = new HashSet<Method>();
            nestedMethods.add(ToMethod.AND.apply(item));
            nestedMethods.add(ToMethod.END.apply(item));

            Set<Property> properties = new HashSet<Property>();
            Set<Method> constructors = new HashSet<Method>();
            
            JavaType memberOf = (JavaType) item.getAttributes().get(MEMBER_OF);
            properties.add(new PropertyBuilder()
                    .withName("builder")
                    .withType(builderType).build());

            constructors.add(new MethodBuilder()
                    .withName("")
                    .withReturnType(nestedUnwrapped)
                    .addNewArgument()
                        .withName("item")
                        .withType(TypeAs.REMOVE_GENERICS_BOUNDS.apply(baseType))
                    .endArgument()
                    .addToAttributes(BODY, "this.builder = new " + builderType.getSimpleName() + "(this, item);")
                    .build());

            constructors.add(new MethodBuilder()
                    .withName("")
                    .withReturnType(nestedUnwrapped)
                    .addToAttributes(BODY, "this.builder = new " + builderType.getSimpleName() + "(this);")
                    .build());

            return new TypeDefBuilder()
                    .withType(nestedType)
                    .withFields(properties)
                    .withMethods(nestedMethods)
                    .withConstructors(constructors)
                    .addToAttributes(MEMBER_OF, memberOf)
                    .build();
        }
    };

    public static final Function<Property, TypeDef> NESTED_INTERFACE = new Function<Property, TypeDef>() {
        @Override
        public TypeDef apply(Property item) {
            JavaType baseType = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getTypeRef());
            JavaType builderType = TypeAs.SHALLOW_BUILDER.apply(baseType);

            JavaType nestedType = NESTED_INTERFACE_TYPE.apply(item);
            JavaType nestedUnwrapped = new JavaTypeBuilder(nestedType).withGenericTypes(new JavaType[0]).build();

            Set<Method> nestedMethods = new HashSet<Method>();
            nestedMethods.add(ToMethod.AND.apply(item));
            nestedMethods.add(ToMethod.END.apply(item));

            Set<Property> properties = new HashSet<Property>();
            Set<Method> constructors = new HashSet<Method>();

            JavaType memberOf = (JavaType) item.getAttributes().get(MEMBER_OF);
            properties.add(new PropertyBuilder()
                    .withName("builder")
                    .withType(builderType).build());

            constructors.add(new MethodBuilder()
                    .withName("")
                    .withReturnType(nestedUnwrapped)
                    .addNewArgument()
                    .withName("item")
                    .withType(baseType)
                    .endArgument()
                    .addToAttributes(BODY, "this.builder = new " + builderType.getSimpleName() + "(this, item);")
                    .build());

            constructors.add(new MethodBuilder()
                    .withName("")
                    .withReturnType(nestedUnwrapped)
                    .addToAttributes(BODY, "this.builder = new " + builderType.getSimpleName() + "(this);")
                    .build());

            return new TypeDefBuilder()
                    .withType(nestedType)
                    .withFields(properties)
                    .withMethods(nestedMethods)
                    .withConstructors(constructors)
                    .addToAttributes(MEMBER_OF, memberOf)
                    .build();
        }
    };


    public static final Function<Property, JavaType> NESTED_TYPE = new Function<Property, JavaType>() {
        @Override
        public JavaType apply(Property item) {
            JavaType nested = SHALLOW_NESTED_TYPE.apply(item);
            //Not a typical fluent
            JavaType fluent = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getTypeRef());
            JavaType nestedInterfaceType = NESTED_INTERFACE_TYPE.apply(item);

            List<JavaType> generics = new ArrayList<JavaType>();
            List<JavaType> superClassGenerics = new ArrayList<JavaType>();
            for (JavaType generic : fluent.getGenericTypes()) {
                generics.add(generic);
                superClassGenerics.add(TypeAs.REMOVE_INTERFACES.apply(generic));
            }
            superClassGenerics.add(nested);
            generics.add(N);

            JavaType superClassFluent = new JavaTypeBuilder(fluent)
                    .withClassName(TypeAs.UNWRAP_COLLECTION_OF.apply(item.getTypeRef()) + "FluentImpl")
                    .withInterfaces(nestedInterfaceType)
                    .withGenericTypes(superClassGenerics.toArray(new JavaType[superClassGenerics.size()]))
                    .build();

            return new JavaTypeBuilder(nested)
                    .withConcrete(true)
                    .withClassName(nested.getClassName()+"Impl")
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .withSuperClass(superClassFluent)
                    .withInterfaces(nestedInterfaceType)
                    .build();
        }

    };

    public static final Function<Property, JavaType> NESTED_INTERFACE_TYPE = new Function<Property, JavaType>() {
        @Override
        public JavaType apply(Property item) {
            JavaType nested = SHALLOW_NESTED_TYPE.apply(item);
            //Not a typical fluent
            JavaType fluent = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getTypeRef());

            List<JavaType> generics = new ArrayList<JavaType>();
            List<JavaType> superClassGenerics = new ArrayList<JavaType>();
            for (JavaType generic : fluent.getGenericTypes()) {
                generics.add(generic);
                superClassGenerics.add(TypeAs.REMOVE_INTERFACES.apply(generic));
            }
            generics.add(N);
            //We need to rid of the bounds: For example: Circle<T extends Number> implements Shape<T extends Shape>
            //We only need To define T bounds once.
            superClassGenerics.add(TypeAs.REMOVE_GENERICS_BOUNDS.apply(nested));

            JavaType superClassFluent = new JavaTypeBuilder(fluent)
                    .withClassName(TypeAs.UNWRAP_COLLECTION_OF.apply(item.getTypeRef()) + "Fluent")
                    .withGenericTypes(superClassGenerics.toArray(new JavaType[superClassGenerics.size()]))
                    .build();

            return new JavaTypeBuilder(nested)
                    .withKind(JavaKind.INTERFACE)
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .withSuperClass(superClassFluent)
                    .withInterfaces(BuilderContextManager.getContext().getNestedInterface().getType(), superClassFluent)
                    .build();
        }

    };

    public static final Function<Property, JavaType> SHALLOW_NESTED_TYPE = new Function<Property, JavaType>() {
        public JavaType apply(Property property) {
            JavaType baseType = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getTypeRef());
            List<JavaType> generics = new ArrayList<JavaType>();
            for (JavaType generic : baseType.getGenericTypes()) {
                generics.add(TypeAs.REMOVE_INTERFACES.apply(generic));
            }
            generics.add(N);
            return new JavaTypeBuilder()
                    .withClassName(captializeFirst(property.getName() + "Nested"))
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .build();
        }
    };

}
