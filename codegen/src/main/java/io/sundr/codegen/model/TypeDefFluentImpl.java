/*
 *      Copyright 2016 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.codegen.model;

import io.sundr.builder.VisitableBuilder;
import io.sundr.builder.Nested;

import java.util.ArrayList;
import java.util.List;
import java.lang.Object;
import java.lang.String;

public class TypeDefFluentImpl<A extends TypeDefFluent<A>> extends ModifierSupportFluentImpl<A> implements TypeDefFluent<A> {

    private Kind kind;
    private String packageName;
    private String name;
    private List<VisitableBuilder<? extends AnnotationRef, ?>> annotations = new ArrayList<VisitableBuilder<? extends AnnotationRef, ?>>();
    private List<VisitableBuilder<? extends ClassRef, ?>> extendsList = new ArrayList<VisitableBuilder<? extends ClassRef, ?>>();
    private List<VisitableBuilder<? extends ClassRef, ?>> implementsList = new ArrayList<VisitableBuilder<? extends ClassRef, ?>>();
    private List<VisitableBuilder<? extends TypeParamDef, ?>> parameters = new ArrayList<VisitableBuilder<? extends TypeParamDef, ?>>();
    private List<VisitableBuilder<? extends Property, ?>> properties = new ArrayList<VisitableBuilder<? extends Property, ?>>();
    private List<VisitableBuilder<? extends Method, ?>> constructors = new ArrayList<VisitableBuilder<? extends Method, ?>>();
    private List<VisitableBuilder<? extends Method, ?>> methods = new ArrayList<VisitableBuilder<? extends Method, ?>>();
    private VisitableBuilder<? extends TypeDef, ?> outerType;
    private List<VisitableBuilder<? extends TypeDef, ?>> innerTypes = new ArrayList<VisitableBuilder<? extends TypeDef, ?>>();

    public TypeDefFluentImpl() {
    }

    public TypeDefFluentImpl(TypeDef instance) {
        this.withKind(instance.getKind());
        this.withPackageName(instance.getPackageName());
        this.withName(instance.getName());
        this.withAnnotations(instance.getAnnotations());
        this.withExtendsList(instance.getExtendsList());
        this.withImplementsList(instance.getImplementsList());
        this.withParameters(instance.getParameters());
        this.withProperties(instance.getProperties());
        this.withConstructors(instance.getConstructors());
        this.withMethods(instance.getMethods());
        this.withOuterType(instance.getOuterType());
        this.withInnerTypes(instance.getInnerTypes());
        this.withModifiers(instance.getModifiers());
        this.withAttributes(instance.getAttributes());
    }

    public Kind getKind() {
        return this.kind;
    }

    public A withKind(Kind kind) {
        this.kind = kind;
        return (A) this;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public A withPackageName(String packageName) {
        this.packageName = packageName;
        return (A) this;
    }

    public String getName() {
        return this.name;
    }

    public A withName(String name) {
        this.name = name;
        return (A) this;
    }

    public A addToAnnotations(AnnotationRef... items) {
        for (AnnotationRef item : items) {
            AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
            _visitables.add(builder);
            this.annotations.add(builder);
        }
        return (A) this;
    }

    public A removeFromAnnotations(AnnotationRef... items) {
        for (AnnotationRef item : items) {
            AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
            _visitables.remove(builder);
            this.annotations.remove(builder);
        }
        return (A) this;
    }

    public List<AnnotationRef> getAnnotations() {
        return build(annotations);
    }

    public A withAnnotations(List<AnnotationRef> annotations) {
        this.annotations.clear();
        if (annotations != null) {
            for (AnnotationRef item : annotations) {
                this.addToAnnotations(item);
            }
        }
        return (A) this;
    }

    public A withAnnotations(AnnotationRef... annotations) {
        this.annotations.clear();
        if (annotations != null) {
            for (AnnotationRef item : annotations) {
                this.addToAnnotations(item);
            }
        }
        return (A) this;
    }

    public TypeDefFluent.AnnotationsNested<A> addNewAnnotation() {
        return new AnnotationsNestedImpl();
    }

    public TypeDefFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item) {
        return new AnnotationsNestedImpl(item);
    }

    public A addToExtendsList(ClassRef... items) {
        for (ClassRef item : items) {
            ClassRefBuilder builder = new ClassRefBuilder(item);
            _visitables.add(builder);
            this.extendsList.add(builder);
        }
        return (A) this;
    }

    public A removeFromExtendsList(ClassRef... items) {
        for (ClassRef item : items) {
            ClassRefBuilder builder = new ClassRefBuilder(item);
            _visitables.remove(builder);
            this.extendsList.remove(builder);
        }
        return (A) this;
    }

    public List<ClassRef> getExtendsList() {
        return build(extendsList);
    }

    public A withExtendsList(List<ClassRef> extendsList) {
        this.extendsList.clear();
        if (extendsList != null) {
            for (ClassRef item : extendsList) {
                this.addToExtendsList(item);
            }
        }
        return (A) this;
    }

    public A withExtendsList(ClassRef... extendsList) {
        this.extendsList.clear();
        if (extendsList != null) {
            for (ClassRef item : extendsList) {
                this.addToExtendsList(item);
            }
        }
        return (A) this;
    }

    public TypeDefFluent.ExtendsListNested<A> addNewExtendsList() {
        return new ExtendsListNestedImpl();
    }

    public TypeDefFluent.ExtendsListNested<A> addNewExtendsListLike(ClassRef item) {
        return new ExtendsListNestedImpl(item);
    }

    public A addToImplementsList(ClassRef... items) {
        for (ClassRef item : items) {
            ClassRefBuilder builder = new ClassRefBuilder(item);
            _visitables.add(builder);
            this.implementsList.add(builder);
        }
        return (A) this;
    }

    public A removeFromImplementsList(ClassRef... items) {
        for (ClassRef item : items) {
            ClassRefBuilder builder = new ClassRefBuilder(item);
            _visitables.remove(builder);
            this.implementsList.remove(builder);
        }
        return (A) this;
    }

    public List<ClassRef> getImplementsList() {
        return build(implementsList);
    }

    public A withImplementsList(List<ClassRef> implementsList) {
        this.implementsList.clear();
        if (implementsList != null) {
            for (ClassRef item : implementsList) {
                this.addToImplementsList(item);
            }
        }
        return (A) this;
    }

    public A withImplementsList(ClassRef... implementsList) {
        this.implementsList.clear();
        if (implementsList != null) {
            for (ClassRef item : implementsList) {
                this.addToImplementsList(item);
            }
        }
        return (A) this;
    }

    public TypeDefFluent.ImplementsListNested<A> addNewImplementsList() {
        return new ImplementsListNestedImpl();
    }

    public TypeDefFluent.ImplementsListNested<A> addNewImplementsListLike(ClassRef item) {
        return new ImplementsListNestedImpl(item);
    }

    public A addToParameters(TypeParamDef... items) {
        for (TypeParamDef item : items) {
            TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
            _visitables.add(builder);
            this.parameters.add(builder);
        }
        return (A) this;
    }

    public A removeFromParameters(TypeParamDef... items) {
        for (TypeParamDef item : items) {
            TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
            _visitables.remove(builder);
            this.parameters.remove(builder);
        }
        return (A) this;
    }

    public List<TypeParamDef> getParameters() {
        return build(parameters);
    }

    public A withParameters(List<TypeParamDef> parameters) {
        this.parameters.clear();
        if (parameters != null) {
            for (TypeParamDef item : parameters) {
                this.addToParameters(item);
            }
        }
        return (A) this;
    }

    public A withParameters(TypeParamDef... parameters) {
        this.parameters.clear();
        if (parameters != null) {
            for (TypeParamDef item : parameters) {
                this.addToParameters(item);
            }
        }
        return (A) this;
    }

    public TypeDefFluent.ParametersNested<A> addNewParameter() {
        return new ParametersNestedImpl();
    }

    public TypeDefFluent.ParametersNested<A> addNewParameterLike(TypeParamDef item) {
        return new ParametersNestedImpl(item);
    }

    public A addToProperties(Property... items) {
        for (Property item : items) {
            PropertyBuilder builder = new PropertyBuilder(item);
            _visitables.add(builder);
            this.properties.add(builder);
        }
        return (A) this;
    }

    public A removeFromProperties(Property... items) {
        for (Property item : items) {
            PropertyBuilder builder = new PropertyBuilder(item);
            _visitables.remove(builder);
            this.properties.remove(builder);
        }
        return (A) this;
    }

    public List<Property> getProperties() {
        return build(properties);
    }

    public A withProperties(List<Property> properties) {
        this.properties.clear();
        if (properties != null) {
            for (Property item : properties) {
                this.addToProperties(item);
            }
        }
        return (A) this;
    }

    public A withProperties(Property... properties) {
        this.properties.clear();
        if (properties != null) {
            for (Property item : properties) {
                this.addToProperties(item);
            }
        }
        return (A) this;
    }

    public TypeDefFluent.PropertiesNested<A> addNewProperty() {
        return new PropertiesNestedImpl();
    }

    public TypeDefFluent.PropertiesNested<A> addNewPropertyLike(Property item) {
        return new PropertiesNestedImpl(item);
    }

    public A addToConstructors(Method... items) {
        for (Method item : items) {
            MethodBuilder builder = new MethodBuilder(item);
            _visitables.add(builder);
            this.constructors.add(builder);
        }
        return (A) this;
    }

    public A removeFromConstructors(Method... items) {
        for (Method item : items) {
            MethodBuilder builder = new MethodBuilder(item);
            _visitables.remove(builder);
            this.constructors.remove(builder);
        }
        return (A) this;
    }

    public List<Method> getConstructors() {
        return build(constructors);
    }

    public A withConstructors(List<Method> constructors) {
        this.constructors.clear();
        if (constructors != null) {
            for (Method item : constructors) {
                this.addToConstructors(item);
            }
        }
        return (A) this;
    }

    public A withConstructors(Method... constructors) {
        this.constructors.clear();
        if (constructors != null) {
            for (Method item : constructors) {
                this.addToConstructors(item);
            }
        }
        return (A) this;
    }

    public TypeDefFluent.ConstructorsNested<A> addNewConstructor() {
        return new ConstructorsNestedImpl();
    }

    public TypeDefFluent.ConstructorsNested<A> addNewConstructorLike(Method item) {
        return new ConstructorsNestedImpl(item);
    }

    public A addToMethods(Method... items) {
        for (Method item : items) {
            MethodBuilder builder = new MethodBuilder(item);
            _visitables.add(builder);
            this.methods.add(builder);
        }
        return (A) this;
    }

    public A removeFromMethods(Method... items) {
        for (Method item : items) {
            MethodBuilder builder = new MethodBuilder(item);
            _visitables.remove(builder);
            this.methods.remove(builder);
        }
        return (A) this;
    }

    public List<Method> getMethods() {
        return build(methods);
    }

    public A withMethods(List<Method> methods) {
        this.methods.clear();
        if (methods != null) {
            for (Method item : methods) {
                this.addToMethods(item);
            }
        }
        return (A) this;
    }

    public A withMethods(Method... methods) {
        this.methods.clear();
        if (methods != null) {
            for (Method item : methods) {
                this.addToMethods(item);
            }
        }
        return (A) this;
    }

    public TypeDefFluent.MethodsNested<A> addNewMethod() {
        return new MethodsNestedImpl();
    }

    public TypeDefFluent.MethodsNested<A> addNewMethodLike(Method item) {
        return new MethodsNestedImpl(item);
    }

    public TypeDef getOuterType() {
        return this.outerType != null ? this.outerType.build() : null;
    }

    public A withOuterType(TypeDef outerType) {
        if (outerType != null) {
            this.outerType = new TypeDefBuilder(outerType);
            _visitables.add(this.outerType);
        }
        return (A) this;
    }

    public TypeDefFluent.OuterTypeNested<A> withNewOuterType() {
        return new OuterTypeNestedImpl();
    }

    public TypeDefFluent.OuterTypeNested<A> withNewOuterTypeLike(TypeDef item) {
        return new OuterTypeNestedImpl(item);
    }

    public TypeDefFluent.OuterTypeNested<A> editOuterType() {
        return withNewOuterTypeLike(getOuterType());
    }

    public TypeDefFluent.OuterTypeNested<A> editOrNewOuterType() {
        return withNewOuterTypeLike(getOuterType() != null ? getOuterType() : new TypeDefBuilder().build());
    }

    public TypeDefFluent.OuterTypeNested<A> editOrNewOuterTypeLike(TypeDef item) {
        return withNewOuterTypeLike(getOuterType() != null ? getOuterType() : item);
    }

    public A addToInnerTypes(TypeDef... items) {
        for (TypeDef item : items) {
            TypeDefBuilder builder = new TypeDefBuilder(item);
            _visitables.add(builder);
            this.innerTypes.add(builder);
        }
        return (A) this;
    }

    public A removeFromInnerTypes(TypeDef... items) {
        for (TypeDef item : items) {
            TypeDefBuilder builder = new TypeDefBuilder(item);
            _visitables.remove(builder);
            this.innerTypes.remove(builder);
        }
        return (A) this;
    }

    public List<TypeDef> getInnerTypes() {
        return build(innerTypes);
    }

    public A withInnerTypes(List<TypeDef> innerTypes) {
        this.innerTypes.clear();
        if (innerTypes != null) {
            for (TypeDef item : innerTypes) {
                this.addToInnerTypes(item);
            }
        }
        return (A) this;
    }

    public A withInnerTypes(TypeDef... innerTypes) {
        this.innerTypes.clear();
        if (innerTypes != null) {
            for (TypeDef item : innerTypes) {
                this.addToInnerTypes(item);
            }
        }
        return (A) this;
    }

    public TypeDefFluent.InnerTypesNested<A> addNewInnerType() {
        return new InnerTypesNestedImpl();
    }

    public TypeDefFluent.InnerTypesNested<A> addNewInnerTypeLike(TypeDef item) {
        return new InnerTypesNestedImpl(item);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TypeDefFluentImpl that = (TypeDefFluentImpl) o;
        if (kind != null ? !kind.equals(that.kind) : that.kind != null) return false;
        if (packageName != null ? !packageName.equals(that.packageName) : that.packageName != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (annotations != null ? !annotations.equals(that.annotations) : that.annotations != null)
            return false;
        if (extendsList != null ? !extendsList.equals(that.extendsList) : that.extendsList != null)
            return false;
        if (implementsList != null ? !implementsList.equals(that.implementsList) : that.implementsList != null)
            return false;
        if (parameters != null ? !parameters.equals(that.parameters) : that.parameters != null)
            return false;
        if (properties != null ? !properties.equals(that.properties) : that.properties != null)
            return false;
        if (constructors != null ? !constructors.equals(that.constructors) : that.constructors != null)
            return false;
        if (methods != null ? !methods.equals(that.methods) : that.methods != null) return false;
        if (outerType != null ? !outerType.equals(that.outerType) : that.outerType != null)
            return false;
        if (innerTypes != null ? !innerTypes.equals(that.innerTypes) : that.innerTypes != null)
            return false;
        return true;
    }


    public class AnnotationsNestedImpl<N> extends AnnotationRefFluentImpl<TypeDefFluent.AnnotationsNested<N>> implements TypeDefFluent.AnnotationsNested<N>, Nested<N> {

        private final AnnotationRefBuilder builder;

        AnnotationsNestedImpl(AnnotationRef item) {
            this.builder = new AnnotationRefBuilder(this, item);
        }

        AnnotationsNestedImpl() {
            this.builder = new AnnotationRefBuilder(this);
        }

        public N and() {
            return (N) TypeDefFluentImpl.this.addToAnnotations(builder.build());
        }

        public N endAnnotation() {
            return and();
        }

    }

    public class ExtendsListNestedImpl<N> extends ClassRefFluentImpl<TypeDefFluent.ExtendsListNested<N>> implements TypeDefFluent.ExtendsListNested<N>, Nested<N> {

        private final ClassRefBuilder builder;

        ExtendsListNestedImpl(ClassRef item) {
            this.builder = new ClassRefBuilder(this, item);
        }

        ExtendsListNestedImpl() {
            this.builder = new ClassRefBuilder(this);
        }

        public N and() {
            return (N) TypeDefFluentImpl.this.addToExtendsList(builder.build());
        }

        public N endExtendsList() {
            return and();
        }

    }

    public class ImplementsListNestedImpl<N> extends ClassRefFluentImpl<TypeDefFluent.ImplementsListNested<N>> implements TypeDefFluent.ImplementsListNested<N>, Nested<N> {

        private final ClassRefBuilder builder;

        ImplementsListNestedImpl(ClassRef item) {
            this.builder = new ClassRefBuilder(this, item);
        }

        ImplementsListNestedImpl() {
            this.builder = new ClassRefBuilder(this);
        }

        public N and() {
            return (N) TypeDefFluentImpl.this.addToImplementsList(builder.build());
        }

        public N endImplementsList() {
            return and();
        }

    }

    public class ParametersNestedImpl<N> extends TypeParamDefFluentImpl<TypeDefFluent.ParametersNested<N>> implements TypeDefFluent.ParametersNested<N>, Nested<N> {

        private final TypeParamDefBuilder builder;

        ParametersNestedImpl(TypeParamDef item) {
            this.builder = new TypeParamDefBuilder(this, item);
        }

        ParametersNestedImpl() {
            this.builder = new TypeParamDefBuilder(this);
        }

        public N and() {
            return (N) TypeDefFluentImpl.this.addToParameters(builder.build());
        }

        public N endParameter() {
            return and();
        }

    }

    public class PropertiesNestedImpl<N> extends PropertyFluentImpl<TypeDefFluent.PropertiesNested<N>> implements TypeDefFluent.PropertiesNested<N>, Nested<N> {

        private final PropertyBuilder builder;

        PropertiesNestedImpl(Property item) {
            this.builder = new PropertyBuilder(this, item);
        }

        PropertiesNestedImpl() {
            this.builder = new PropertyBuilder(this);
        }

        public N and() {
            return (N) TypeDefFluentImpl.this.addToProperties(builder.build());
        }

        public N endProperty() {
            return and();
        }

    }

    public class ConstructorsNestedImpl<N> extends MethodFluentImpl<TypeDefFluent.ConstructorsNested<N>> implements TypeDefFluent.ConstructorsNested<N>, Nested<N> {

        private final MethodBuilder builder;

        ConstructorsNestedImpl(Method item) {
            this.builder = new MethodBuilder(this, item);
        }

        ConstructorsNestedImpl() {
            this.builder = new MethodBuilder(this);
        }

        public N and() {
            return (N) TypeDefFluentImpl.this.addToConstructors(builder.build());
        }

        public N endConstructor() {
            return and();
        }

    }

    public class MethodsNestedImpl<N> extends MethodFluentImpl<TypeDefFluent.MethodsNested<N>> implements TypeDefFluent.MethodsNested<N>, Nested<N> {

        private final MethodBuilder builder;

        MethodsNestedImpl(Method item) {
            this.builder = new MethodBuilder(this, item);
        }

        MethodsNestedImpl() {
            this.builder = new MethodBuilder(this);
        }

        public N and() {
            return (N) TypeDefFluentImpl.this.addToMethods(builder.build());
        }

        public N endMethod() {
            return and();
        }

    }

    public class OuterTypeNestedImpl<N> extends TypeDefFluentImpl<TypeDefFluent.OuterTypeNested<N>> implements TypeDefFluent.OuterTypeNested<N>, Nested<N> {

        private final TypeDefBuilder builder;

        OuterTypeNestedImpl(TypeDef item) {
            this.builder = new TypeDefBuilder(this, item);
        }

        OuterTypeNestedImpl() {
            this.builder = new TypeDefBuilder(this);
        }

        public N and() {
            return (N) TypeDefFluentImpl.this.withOuterType(builder.build());
        }

        public N endOuterType() {
            return and();
        }

    }

    public class InnerTypesNestedImpl<N> extends TypeDefFluentImpl<TypeDefFluent.InnerTypesNested<N>> implements TypeDefFluent.InnerTypesNested<N>, Nested<N> {

        private final TypeDefBuilder builder;

        InnerTypesNestedImpl(TypeDef item) {
            this.builder = new TypeDefBuilder(this, item);
        }

        InnerTypesNestedImpl() {
            this.builder = new TypeDefBuilder(this);
        }

        public N and() {
            return (N) TypeDefFluentImpl.this.addToInnerTypes(builder.build());
        }

        public N endInnerType() {
            return and();
        }

    }


}
