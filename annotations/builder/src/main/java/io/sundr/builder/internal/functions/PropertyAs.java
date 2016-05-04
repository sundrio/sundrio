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
import io.sundr.builder.Visitor;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaMethodBuilder;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaPropertyBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.builder.Constants.BODY;
import static io.sundr.builder.Constants.MEMBER_OF;
import static io.sundr.builder.Constants.N;
import static io.sundr.builder.internal.functions.TypeAs.*;
import static io.sundr.builder.internal.utils.BuilderUtils.getNextGeneric;
import static io.sundr.codegen.utils.StringUtils.captializeFirst;
import static io.sundr.codegen.utils.TypeUtils.typeExtends;

public final class PropertyAs {
    
    private PropertyAs() {}

    public static final Function<JavaProperty, JavaClazz> CLASS = new Function<JavaProperty, JavaClazz>() {
        @Override
        public JavaClazz apply(JavaProperty item) {
            BuilderContext context = BuilderContextManager.getContext();
            Elements elements = context.getElements();
            JavaType type = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getType());
            TypeElement typeElement = elements.getTypeElement(type.getFullyQualifiedName());
            return BuilderContextManager.getContext().getTypeElementToJavaClazz().apply(typeElement);
        }
    };

    public static final Function<JavaProperty, JavaClazz> NESTED_CLASS = new Function<JavaProperty, JavaClazz>() {
        @Override
        public JavaClazz apply(JavaProperty item) {
            JavaType baseType = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getType());
            JavaType builderType = TypeAs.SHALLOW_BUILDER.apply(baseType);

            JavaType nestedType = NESTED_TYPE.apply(item);
            Set<JavaType> nestedInterfaces = new HashSet<JavaType>();
            for (JavaType n : nestedType.getInterfaces()) {
                nestedInterfaces.add(TypeAs.REMOVE_GENERICS_BOUNDS.apply(n));
            }
            nestedType = new JavaTypeBuilder(nestedType).withInterfaces(nestedInterfaces.toArray(new JavaType[nestedInterfaces.size()])).build();
            JavaType nestedUnwrapped = new JavaTypeBuilder(nestedType).withGenericTypes(new JavaType[0]).build();

            Set<JavaMethod> nestedMethods = new HashSet<JavaMethod>();
            nestedMethods.add(ToMethod.AND.apply(item));
            nestedMethods.add(ToMethod.END.apply(item));

            Set<JavaProperty> properties = new HashSet<JavaProperty>();
            Set<JavaMethod> constructors = new HashSet<JavaMethod>();
            
            JavaType memberOf = (JavaType) item.getAttributes().get(MEMBER_OF);
            properties.add(new JavaPropertyBuilder()
                    .withName("builder")
                    .withType(builderType).build());

            constructors.add(new JavaMethodBuilder()
                    .withName("")
                    .withReturnType(nestedUnwrapped)
                    .addNewArgument()
                        .withName("item")
                        .withType(TypeAs.REMOVE_GENERICS_BOUNDS.apply(baseType))
                    .endArgument()
                    .addToAttributes(BODY, "this.builder = new " + builderType.getSimpleName() + "(this, item);")
                    .build());

            constructors.add(new JavaMethodBuilder()
                    .withName("")
                    .withReturnType(nestedUnwrapped)
                    .addToAttributes(BODY, "this.builder = new " + builderType.getSimpleName() + "(this);")
                    .build());

            return new JavaClazzBuilder()
                    .withType(nestedType)
                    .withFields(properties)
                    .withMethods(nestedMethods)
                    .withConstructors(constructors)
                    .addToAttributes(MEMBER_OF, memberOf)
                    .build();
        }
    };

    public static final Function<JavaProperty, JavaClazz> NESTED_INTERFACE = new Function<JavaProperty, JavaClazz>() {
        @Override
        public JavaClazz apply(JavaProperty item) {
            JavaType baseType = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getType());
            JavaType builderType = TypeAs.SHALLOW_BUILDER.apply(baseType);

            JavaType nestedType = NESTED_INTERFACE_TYPE.apply(item);
            JavaType nestedUnwrapped = new JavaTypeBuilder(nestedType).withGenericTypes(new JavaType[0]).build();

            Set<JavaMethod> nestedMethods = new HashSet<JavaMethod>();
            nestedMethods.add(ToMethod.AND.apply(item));
            nestedMethods.add(ToMethod.END.apply(item));

            Set<JavaProperty> properties = new HashSet<JavaProperty>();
            Set<JavaMethod> constructors = new HashSet<JavaMethod>();

            JavaType memberOf = (JavaType) item.getAttributes().get(MEMBER_OF);
            properties.add(new JavaPropertyBuilder()
                    .withName("builder")
                    .withType(builderType).build());

            constructors.add(new JavaMethodBuilder()
                    .withName("")
                    .withReturnType(nestedUnwrapped)
                    .addNewArgument()
                    .withName("item")
                    .withType(baseType)
                    .endArgument()
                    .addToAttributes(BODY, "this.builder = new " + builderType.getSimpleName() + "(this, item);")
                    .build());

            constructors.add(new JavaMethodBuilder()
                    .withName("")
                    .withReturnType(nestedUnwrapped)
                    .addToAttributes(BODY, "this.builder = new " + builderType.getSimpleName() + "(this);")
                    .build());

            return new JavaClazzBuilder()
                    .withType(nestedType)
                    .withFields(properties)
                    .withMethods(nestedMethods)
                    .withConstructors(constructors)
                    .addToAttributes(MEMBER_OF, memberOf)
                    .build();
        }
    };


    public static final Function<JavaProperty, JavaType> NESTED_TYPE = new Function<JavaProperty, JavaType>() {
        @Override
        public JavaType apply(JavaProperty item) {
            JavaType nested = SHALLOW_NESTED_TYPE.apply(item);
            //Not a typical fluent
            JavaType fluent = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getType());
            JavaType nestedInterfaceType = NESTED_INTERFACE_TYPE.apply(item);

            List<JavaType> generics = new ArrayList<JavaType>();
            List<JavaType> superClassGenerics = new ArrayList<JavaType>();
            for (JavaType generic : fluent.getGenericTypes()) {
                generics.add(generic);
                superClassGenerics.add(TypeAs.REMOVE_SUPERCLASS.apply(generic));
            }
            superClassGenerics.add(nested);
            generics.add(N);

            JavaType superClassFluent = new JavaTypeBuilder(fluent)
                    .withClassName(TypeAs.UNWRAP_COLLECTION_OF.apply(item.getType()) + "FluentImpl")
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

    public static final Function<JavaProperty, JavaType> NESTED_INTERFACE_TYPE = new Function<JavaProperty, JavaType>() {
        @Override
        public JavaType apply(JavaProperty item) {
            JavaType nested = SHALLOW_NESTED_TYPE.apply(item);
            //Not a typical fluent
            JavaType fluent = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getType());

            List<JavaType> generics = new ArrayList<JavaType>();
            List<JavaType> superClassGenerics = new ArrayList<JavaType>();
            for (JavaType generic : fluent.getGenericTypes()) {
                generics.add(generic);
                superClassGenerics.add(TypeAs.REMOVE_SUPERCLASS.apply(generic));
            }
            generics.add(N);
            //We need to rid of the bounds: For example: Circle<T extends Number> implements Shape<T extends Shape>
            //We only need To define T bounds once.
            superClassGenerics.add(TypeAs.REMOVE_GENERICS_BOUNDS.apply(nested));

            JavaType superClassFluent = new JavaTypeBuilder(fluent)
                    .withClassName(TypeAs.UNWRAP_COLLECTION_OF.apply(item.getType()) + "Fluent")
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

    public static final Function<JavaProperty, JavaType> SHALLOW_NESTED_TYPE = new Function<JavaProperty, JavaType>() {
        public JavaType apply(JavaProperty property) {
            JavaType baseType = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getType());
            List<JavaType> generics = new ArrayList<JavaType>();
            for (JavaType generic : baseType.getGenericTypes()) {
                generics.add(TypeAs.REMOVE_SUPERCLASS.apply(generic));
            }
            generics.add(N);
            return new JavaTypeBuilder()
                    .withClassName(captializeFirst(property.getName() + "Nested"))
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .build();
        }
    };

}
