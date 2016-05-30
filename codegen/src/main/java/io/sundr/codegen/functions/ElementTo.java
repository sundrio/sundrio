/*
 * Copyright 2016 The original authors.
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

package io.sundr.codegen.functions;

import io.sundr.CachingFunction;
import io.sundr.Function;
import io.sundr.codegen.CodegenContext;
import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.converters.TypeRefTypeVisitor;
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
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeParamRefBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.utils.TypeUtils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.NoType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.ElementFilter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.codegen.utils.ModelUtils.getClassName;
import static io.sundr.codegen.utils.ModelUtils.getPackageName;

public class ElementTo {

    private static final String OBJECT_BOUND = "java.lang.Object";

    private static final Function<TypeMirror, TypeRef> DEEP_MIRROR_TO_TYPEREF = new Function<TypeMirror, TypeRef>() {
        public TypeRef apply(TypeMirror item) {
            Element element = CodegenContext.getContext().getTypes().asElement(item);
            TypeDef known = null;
            if (element instanceof TypeElement) {
                known = TYPEDEF.apply((TypeElement) element);
            }
            TypeRef typeRef = item.accept(new TypeRefTypeVisitor(), 0);
            if (typeRef instanceof ClassRef && known != null) {
                return new ClassRefBuilder((ClassRef) typeRef).withDefinition(known).build();
            }
            return typeRef;
        }
    };

    private static final Function<TypeMirror, TypeRef> SHALLOW_MIRROR_TO_TYPEREF = new Function<TypeMirror, TypeRef>() {
        public TypeRef apply(TypeMirror item) {
            return item.accept(new TypeRefTypeVisitor(), 0);
        }
    };

    private static final Function<TypeMirror, TypeRef> INTERNAL_MIRROR_TO_TYPEREF = CachingFunction.wrap(DEEP_MIRROR_TO_TYPEREF, SHALLOW_MIRROR_TO_TYPEREF, 1);

    public static final  Function<TypeParameterElement, TypeParamDef> TYPEPARAMDEF = new  Function<TypeParameterElement, TypeParamDef> () {

        public TypeParamDef apply(TypeParameterElement item) {
            List<ClassRef> typeRefs = new ArrayList();

            for (TypeMirror typeMirror : item.getBounds()) {
                //TODO: Fix this
                //typeRefs.add(toTypeRef.apply(typeMirror));
            }

            return new TypeParamDefBuilder()
                    .withName(item.getSimpleName().toString())
                    .withBounds(typeRefs.toArray(new ClassRef[typeRefs.size()]))
                    .build();
        }

    };

    public static final Function<TypeVariable, TypeParamRef> TYPEVARIABLE_TO_TYPEPARAM_REF = new Function<TypeVariable, TypeParamRef>() {

        public TypeParamRef apply(TypeVariable item) {
            return new TypeParamRefBuilder().withName(item.asElement().getSimpleName().toString()).build();
        }
    };

    public static final Function<VariableElement, Property> PROPERTY = new Function<VariableElement, Property>() {
        public Property apply(final VariableElement variableElement) {
            String name = variableElement.getSimpleName().toString();

            String elementType = variableElement.asType().toString();

            TypeRef type = variableElement.asType().accept(new TypeRefTypeVisitor(), 0);

            Set<ClassRef> annotations = new LinkedHashSet<ClassRef>();
            for (AnnotationMirror annotationMirror : variableElement.getAnnotationMirrors()) {
                TypeRef annotationType = annotationMirror.getAnnotationType().accept(new TypeRefTypeVisitor(), 0);
                if (annotationType instanceof ClassRef) {
                    annotations.add((ClassRef) annotationType);
                } else {
                    throw new IllegalStateException();
                }
            }

            return new PropertyBuilder()
                    .withName(name)
                    .withTypeRef(type)
                    .withAnnotations(annotations)
                    .withModifiers(TypeUtils.modifiersToInt(variableElement.getModifiers()))
                    .build();
        }
    };

     public static final Function<ExecutableElement, Method> METHOD = new Function<ExecutableElement, Method>() {


         public Method apply(ExecutableElement executableElement) {
             MethodBuilder methodBuilder = new MethodBuilder()
                     .withModifiers(TypeUtils.modifiersToInt(executableElement.getModifiers()))
                     .withName(executableElement.getSimpleName().toString())
                     .withReturnType(INTERNAL_MIRROR_TO_TYPEREF.apply(executableElement.getReturnType()));


             //Populate constructor parameters
             for (VariableElement variableElement : executableElement.getParameters()) {
                 methodBuilder = methodBuilder
                         .withName(executableElement.getSimpleName().toString())
                         .withReturnType(INTERNAL_MIRROR_TO_TYPEREF.apply(executableElement.getReturnType()))
                         .addToArguments(PROPERTY.apply(variableElement));

                 Set<ClassRef> exceptionRefs = new LinkedHashSet<ClassRef>();
                 for (TypeMirror thrownType : executableElement.getThrownTypes()) {
                     if (thrownType instanceof ClassRef) {
                         exceptionRefs.add((ClassRef) thrownType);
                     }
                 }
                 methodBuilder = methodBuilder.withExceptions(exceptionRefs);
             }

             Set<ClassRef> annotationRefs = new LinkedHashSet<ClassRef>();
             for (AnnotationMirror annotationMirror : executableElement.getAnnotationMirrors()) {
                 TypeRef annotationType = INTERNAL_MIRROR_TO_TYPEREF.apply(annotationMirror.getAnnotationType());
                 if (annotationType instanceof ClassRef) {
                     annotationRefs.add((ClassRef) annotationType);
                 }
                 methodBuilder.withAnnotations(annotationRefs);
             }


             return methodBuilder.build();
         }
     };

    public static final Function<TypeElement, TypeDef> TYPEDEF = CachingFunction.wrap(new Function<TypeElement, TypeDef>() {
        public TypeDef apply(TypeElement classElement) {
            //Check SuperClass
            Kind kind = Kind.CLASS;

            TypeMirror superClass = classElement.getSuperclass();

            TypeRef superClassType = superClass instanceof NoType
                    ? TypeDef.OBJECT.toReference()
                    : INTERNAL_MIRROR_TO_TYPEREF.apply(superClass);

            List<TypeParamDef> genericTypes = new ArrayList<TypeParamDef>();
            List<ClassRef> interfaces = new ArrayList<ClassRef>();

            if (classElement.getKind() == ElementKind.INTERFACE) {
                kind = Kind.INTERFACE;
            } else if (classElement.getKind() == ElementKind.CLASS) {
                kind = Kind.CLASS;
            }

            for (TypeMirror interfaceTypeMirrror : classElement.getInterfaces()) {
                TypeRef interfaceType = INTERNAL_MIRROR_TO_TYPEREF.apply(interfaceTypeMirrror);
                if (interfaceType instanceof ClassRef) {
                    interfaces.add((ClassRef) interfaceType);
                } else {
                    throw new IllegalStateException("Interface: [" + interfaceType + "] not mapped to a class ref.");
                }
            }

            for (TypeParameterElement typeParameter : classElement.getTypeParameters()) {
                List<ClassRef> genericBounds = new ArrayList<ClassRef>();
                if (!typeParameter.getBounds().isEmpty()) {
                    TypeMirror bound = typeParameter.getBounds().get(0);
                    if (!OBJECT_BOUND.equals(bound.toString())) {
                        TypeRef boundRef = INTERNAL_MIRROR_TO_TYPEREF.apply(bound);
                        if (boundRef instanceof ClassRef) {
                            genericBounds.add((ClassRef) boundRef);
                        } else {
                            throw new IllegalStateException("Parameter bound: [" + boundRef + "] not mapped to a class ref.");
                        }
                    }
                }

                TypeParamDef genericType = new TypeParamDefBuilder().withName(typeParameter.getSimpleName().toString())
                        .withBounds(genericBounds.toArray(new ClassRef[genericBounds.size()]))
                        .build();

                genericTypes.add(genericType);
            }

            TypeDefBuilder builder = new TypeDefBuilder()
                    .withKind(kind)
                    .withModifiers(TypeUtils.modifiersToInt(classElement.getModifiers()))
                    .withPackageName(getPackageName(classElement))
                    .withName(getClassName(classElement))
                    .withParameters(genericTypes.toArray(new TypeParamDef[genericTypes.size()]))
                    .withExtendsList(superClassType instanceof ClassRef ? (ClassRef) superClassType : null)
                    .withImplementsList(interfaces.toArray(new ClassRef[interfaces.size()]));


            for (ExecutableElement constructor : ElementFilter.constructorsIn(classElement.getEnclosedElements())) {
                builder.addToConstructors(METHOD.apply(constructor));
            }

            //Populate Fields
            for (VariableElement variableElement : ElementFilter.fieldsIn(classElement.getEnclosedElements())) {
                builder.addToProperties(PROPERTY.apply(variableElement));
            }

            Set<ExecutableElement> allMethods = new LinkedHashSet<ExecutableElement>();
            allMethods.addAll(ElementFilter.methodsIn(classElement.getEnclosedElements()));
            allMethods.addAll(getInheritedMethods(classElement));

            for (ExecutableElement method : allMethods) {
                builder.addToMethods(METHOD.apply(method));
            }

            for (AnnotationMirror annotationMirror : classElement.getAnnotationMirrors()) {
                TypeRef annotationType = INTERNAL_MIRROR_TO_TYPEREF.apply(annotationMirror.getAnnotationType());
                if (annotationType instanceof ClassRef) {
                    builder.addToAnnotations((ClassRef) annotationType);
                } else {
                    throw new IllegalStateException("Annotation type: [" + annotationType + "] not mapped to a class ref.");
                }
            }
            return DefinitionRepository.getRepository().register(builder.build());
        }

        public Set<ExecutableElement> getInheritedMethods(TypeElement typeElement) {
            Set<ExecutableElement> result = new LinkedHashSet<ExecutableElement>();
            if (typeElement != null) {
                for (ExecutableElement method : ElementFilter.methodsIn(typeElement.getEnclosedElements())) {
                    if (!method.getModifiers().contains(Modifier.PRIVATE)) {
                        result.add(method);
                    }
                }
                result.addAll(getInheritedMethods(typeElement.getSuperclass() != null ?
                        CodegenContext.getContext().getElements().getTypeElement(typeElement.getSuperclass().toString()) : null));

            }

            return result;
        }
    });


}
