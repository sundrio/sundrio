package io.sundr.codegen.model;

import io.sundr.builder.Nested;
import java.util.List;
import java.lang.Object;
import java.util.Map;
import java.lang.String;

public interface MethodFluent<A extends MethodFluent<A>> extends ModifierSupportFluent<A>{


    public A addToAnnotations(AnnotationRef... items);
    public A removeFromAnnotations(AnnotationRef... items);
    public List<AnnotationRef> getAnnotations();
    public A withAnnotations(List<AnnotationRef> annotations);
    public A withAnnotations(AnnotationRef... annotations);
    public MethodFluent.AnnotationsNested<A> addNewAnnotation();
    public MethodFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item);
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
    public A withVoidRefReturnType(VoidRef voidRefReturnType);
    public MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnType();
    public MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnTypeLike(VoidRef item);
    public A withWildcardRefReturnType(WildcardRef wildcardRefReturnType);
    public MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnType();
    public MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnTypeLike(WildcardRef item);
    public A withPrimitiveRefReturnType(PrimitiveRef primitiveRefReturnType);
    public MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnType();
    public MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnTypeLike(PrimitiveRef item);
    public A withTypeParamRefReturnType(TypeParamRef typeParamRefReturnType);
    public MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnType();
    public MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnTypeLike(TypeParamRef item);
    public A withClassRefReturnType(ClassRef classRefReturnType);
    public MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnType();
    public MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnTypeLike(ClassRef item);
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
    public MethodFluent.BlockNested<A> editOrNewBlock();
    public MethodFluent.BlockNested<A> editOrNewBlockLike(Block item);

    public interface AnnotationsNested<N> extends Nested<N>,AnnotationRefFluent<MethodFluent.AnnotationsNested<N>>{


    public N and();    public N endAnnotation();
}
    public interface ParametersNested<N> extends Nested<N>,TypeParamDefFluent<MethodFluent.ParametersNested<N>>{


    public N and();    public N endParameter();
}
    public interface VoidRefReturnTypeNested<N> extends Nested<N>,VoidRefFluent<MethodFluent.VoidRefReturnTypeNested<N>>{


    public N and();    public N endVoidRefReturnType();
}
    public interface WildcardRefReturnTypeNested<N> extends Nested<N>,WildcardRefFluent<MethodFluent.WildcardRefReturnTypeNested<N>>{


    public N and();    public N endWildcardRefReturnType();
}
    public interface PrimitiveRefReturnTypeNested<N> extends Nested<N>,PrimitiveRefFluent<MethodFluent.PrimitiveRefReturnTypeNested<N>>{


    public N and();    public N endPrimitiveRefReturnType();
}
    public interface TypeParamRefReturnTypeNested<N> extends Nested<N>,TypeParamRefFluent<MethodFluent.TypeParamRefReturnTypeNested<N>>{


    public N and();    public N endTypeParamRefReturnType();
}
    public interface ClassRefReturnTypeNested<N> extends Nested<N>,ClassRefFluent<MethodFluent.ClassRefReturnTypeNested<N>>{


    public N and();    public N endClassRefReturnType();
}
    public interface ArgumentsNested<N> extends Nested<N>,PropertyFluent<MethodFluent.ArgumentsNested<N>>{


    public N and();    public N endArgument();
}
    public interface ExceptionsNested<N> extends Nested<N>,ClassRefFluent<MethodFluent.ExceptionsNested<N>>{


    public N and();    public N endException();
}
    public interface BlockNested<N> extends Nested<N>,BlockFluent<MethodFluent.BlockNested<N>>{

        
    public N and();    public N endBlock();
}


}
