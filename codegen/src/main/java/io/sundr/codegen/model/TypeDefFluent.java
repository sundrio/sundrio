package io.sundr.codegen.model;

import io.sundr.builder.Nested;
import java.util.List;
import java.lang.Object;
import java.util.Map;
import java.lang.String;

public interface TypeDefFluent<A extends TypeDefFluent<A>> extends ModifierSupportFluent<A>{


    public Kind getKind();
    public A withKind(Kind kind);
    public String getPackageName();
    public A withPackageName(String packageName);
    public String getName();
    public A withName(String name);
    public A addToAnnotations(AnnotationRef... items);
    public A removeFromAnnotations(AnnotationRef... items);
    public List<AnnotationRef> getAnnotations();
    public A withAnnotations(List<AnnotationRef> annotations);
    public A withAnnotations(AnnotationRef... annotations);
    public TypeDefFluent.AnnotationsNested<A> addNewAnnotation();
    public TypeDefFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item);
    public A addToExtendsList(ClassRef... items);
    public A removeFromExtendsList(ClassRef... items);
    public List<ClassRef> getExtendsList();
    public A withExtendsList(List<ClassRef> extendsList);
    public A withExtendsList(ClassRef... extendsList);
    public TypeDefFluent.ExtendsListNested<A> addNewExtendsList();
    public TypeDefFluent.ExtendsListNested<A> addNewExtendsListLike(ClassRef item);
    public A addToImplementsList(ClassRef... items);
    public A removeFromImplementsList(ClassRef... items);
    public List<ClassRef> getImplementsList();
    public A withImplementsList(List<ClassRef> implementsList);
    public A withImplementsList(ClassRef... implementsList);
    public TypeDefFluent.ImplementsListNested<A> addNewImplementsList();
    public TypeDefFluent.ImplementsListNested<A> addNewImplementsListLike(ClassRef item);
    public A addToParameters(TypeParamDef... items);
    public A removeFromParameters(TypeParamDef... items);
    public List<TypeParamDef> getParameters();
    public A withParameters(List<TypeParamDef> parameters);
    public A withParameters(TypeParamDef... parameters);
    public TypeDefFluent.ParametersNested<A> addNewParameter();
    public TypeDefFluent.ParametersNested<A> addNewParameterLike(TypeParamDef item);
    public A addToProperties(Property... items);
    public A removeFromProperties(Property... items);
    public List<Property> getProperties();
    public A withProperties(List<Property> properties);
    public A withProperties(Property... properties);
    public TypeDefFluent.PropertiesNested<A> addNewProperty();
    public TypeDefFluent.PropertiesNested<A> addNewPropertyLike(Property item);
    public A addToConstructors(Method... items);
    public A removeFromConstructors(Method... items);
    public List<Method> getConstructors();
    public A withConstructors(List<Method> constructors);
    public A withConstructors(Method... constructors);
    public TypeDefFluent.ConstructorsNested<A> addNewConstructor();
    public TypeDefFluent.ConstructorsNested<A> addNewConstructorLike(Method item);
    public A addToMethods(Method... items);
    public A removeFromMethods(Method... items);
    public List<Method> getMethods();
    public A withMethods(List<Method> methods);
    public A withMethods(Method... methods);
    public TypeDefFluent.MethodsNested<A> addNewMethod();
    public TypeDefFluent.MethodsNested<A> addNewMethodLike(Method item);
    public TypeDef getOuterType();
    public A withOuterType(TypeDef outerType);
    public TypeDefFluent.OuterTypeNested<A> withNewOuterType();
    public TypeDefFluent.OuterTypeNested<A> withNewOuterTypeLike(TypeDef item);
    public TypeDefFluent.OuterTypeNested<A> editOuterType();
    public TypeDefFluent.OuterTypeNested<A> editOrNewOuterType();
    public TypeDefFluent.OuterTypeNested<A> editOrNewOuterTypeLike(TypeDef item);
    public A addToInnerTypes(TypeDef... items);
    public A removeFromInnerTypes(TypeDef... items);
    public List<TypeDef> getInnerTypes();
    public A withInnerTypes(List<TypeDef> innerTypes);
    public A withInnerTypes(TypeDef... innerTypes);
    public TypeDefFluent.InnerTypesNested<A> addNewInnerType();
    public TypeDefFluent.InnerTypesNested<A> addNewInnerTypeLike(TypeDef item);

    public interface AnnotationsNested<N> extends Nested<N>,AnnotationRefFluent<TypeDefFluent.AnnotationsNested<N>>{


    public N and();    public N endAnnotation();
}
    public interface ExtendsListNested<N> extends Nested<N>,ClassRefFluent<TypeDefFluent.ExtendsListNested<N>>{


    public N and();    public N endExtendsList();
}
    public interface ImplementsListNested<N> extends Nested<N>,ClassRefFluent<TypeDefFluent.ImplementsListNested<N>>{


    public N and();    public N endImplementsList();
}
    public interface ParametersNested<N> extends Nested<N>,TypeParamDefFluent<TypeDefFluent.ParametersNested<N>>{


    public N and();    public N endParameter();
}
    public interface PropertiesNested<N> extends Nested<N>,PropertyFluent<TypeDefFluent.PropertiesNested<N>>{


    public N and();    public N endProperty();
}
    public interface ConstructorsNested<N> extends Nested<N>,MethodFluent<TypeDefFluent.ConstructorsNested<N>>{


    public N and();    public N endConstructor();
}
    public interface MethodsNested<N> extends Nested<N>,MethodFluent<TypeDefFluent.MethodsNested<N>>{


    public N and();    public N endMethod();
}
    public interface OuterTypeNested<N> extends Nested<N>,TypeDefFluent<TypeDefFluent.OuterTypeNested<N>>{


    public N and();    public N endOuterType();
}
    public interface InnerTypesNested<N> extends Nested<N>,TypeDefFluent<TypeDefFluent.InnerTypesNested<N>>{

        
    public N and();    public N endInnerType();
}


}
