package io.sundr.codegen.model;

import io.sundr.builder.Nested;

import java.util.List;
import java.lang.String;

public interface MethodFluent<A extends MethodFluent<A>> extends ModifierSupportFluent<A>{


    public A addToAnnotations(ClassRef... items);
    public A removeFromAnnotations(ClassRef... items);
    public List<ClassRef> getAnnotations();
    public A withAnnotations(List<ClassRef> annotations);
    public A withAnnotations(ClassRef... annotations);
    public MethodFluent.AnnotationsNested<A> addNewAnnotation();
    public MethodFluent.AnnotationsNested<A> addNewAnnotationLike(ClassRef item);
    public A addToParameters(TypeParamDef... items);
    public A removeFromParameters(TypeParamDef... items);
    public List<TypeParamDef> getParameters();
    public A withParameters(List<TypeParamDef> parameters);
    public A withParameters(TypeParamDef... parameters);
    public MethodFluent.ParametersNested<A> addNewParameter();
    public MethodFluent.ParametersNested<A> addNewParameterLike(TypeParamDef item);
    public String getName();
    public A withName(String name);
    public TypeRef getReturnType();
    public A withReturnType(TypeRef returnType);
    public A addToArguments(Property... items);
    public A removeFromArguments(Property... items);
    public List<Property> getArguments();
    public A withArguments(List<Property> arguments);
    public A withArguments(Property... arguments);
    public MethodFluent.ArgumentsNested<A> addNewArgument();
    public MethodFluent.ArgumentsNested<A> addNewArgumentLike(Property item);
    public boolean isVarArgPreferred();
    public A withVarArgPreferred(boolean varArgPreferred);
    public A addToExceptions(ClassRef... items);
    public A removeFromExceptions(ClassRef... items);
    public List<ClassRef> getExceptions();
    public A withExceptions(List<ClassRef> exceptions);
    public A withExceptions(ClassRef... exceptions);
    public MethodFluent.ExceptionsNested<A> addNewException();
    public MethodFluent.ExceptionsNested<A> addNewExceptionLike(ClassRef item);
    public Block getBlock();
    public A withBlock(Block block);
    public MethodFluent.BlockNested<A> withNewBlock();
    public MethodFluent.BlockNested<A> withNewBlockLike(Block item);
    public MethodFluent.BlockNested<A> editBlock();

    public interface AnnotationsNested<N> extends Nested<N>,ClassRefFluent<MethodFluent.AnnotationsNested<N>>{


    public N endAnnotation();    public N and();
}
    public interface ParametersNested<N> extends Nested<N>,TypeParamDefFluent<MethodFluent.ParametersNested<N>>{


    public N endParameter();    public N and();
}
    public interface ArgumentsNested<N> extends Nested<N>,PropertyFluent<MethodFluent.ArgumentsNested<N>>{


    public N endArgument();    public N and();
}
    public interface ExceptionsNested<N> extends Nested<N>,ClassRefFluent<MethodFluent.ExceptionsNested<N>>{


    public N endException();    public N and();
}
    public interface BlockNested<N> extends Nested<N>,BlockFluent<MethodFluent.BlockNested<N>>{

        
    public N endBlock();    public N and();
}


}
