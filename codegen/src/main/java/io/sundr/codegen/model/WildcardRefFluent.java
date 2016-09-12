package io.sundr.codegen.model;

import io.sundr.builder.Nested;
import java.util.List;
import java.lang.Object;
import java.util.Map;
import java.lang.String;

public interface WildcardRefFluent<A extends WildcardRefFluent<A>> extends TypeRefFluent<A>{


    public A addToBounds(TypeRef... items);
    public A removeFromBounds(TypeRef... items);
    public List<TypeRef> getBounds();
    public A withBounds(List<TypeRef> bounds);
    public A withBounds(TypeRef... bounds);
    public A addToVoidRefBounds(VoidRef... items);
    public A removeFromVoidRefBounds(VoidRef... items);
    public WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBound();
    public WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBoundLike(VoidRef item);
    public A addToWildcardRefBounds(WildcardRef... items);
    public A removeFromWildcardRefBounds(WildcardRef... items);
    public WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBound();
    public WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBoundLike(WildcardRef item);
    public A addToPrimitiveRefBounds(PrimitiveRef... items);
    public A removeFromPrimitiveRefBounds(PrimitiveRef... items);
    public WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBound();
    public WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBoundLike(PrimitiveRef item);
    public A addToTypeParamRefBounds(TypeParamRef... items);
    public A removeFromTypeParamRefBounds(TypeParamRef... items);
    public WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBound();
    public WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBoundLike(TypeParamRef item);
    public A addToClassRefBounds(ClassRef... items);
    public A removeFromClassRefBounds(ClassRef... items);
    public WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBound();
    public WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBoundLike(ClassRef item);

    public interface VoidRefBoundsNested<N> extends Nested<N>,VoidRefFluent<WildcardRefFluent.VoidRefBoundsNested<N>>{


    public N endVoidRefBound();    public N and();
}
    public interface WildcardRefBoundsNested<N> extends Nested<N>,WildcardRefFluent<WildcardRefFluent.WildcardRefBoundsNested<N>>{


    public N endWildcardRefBound();    public N and();
}
    public interface PrimitiveRefBoundsNested<N> extends Nested<N>,PrimitiveRefFluent<WildcardRefFluent.PrimitiveRefBoundsNested<N>>{


    public N endPrimitiveRefBound();    public N and();
}
    public interface TypeParamRefBoundsNested<N> extends Nested<N>,TypeParamRefFluent<WildcardRefFluent.TypeParamRefBoundsNested<N>>{


    public N endTypeParamRefBound();    public N and();
}
    public interface ClassRefBoundsNested<N> extends Nested<N>,ClassRefFluent<WildcardRefFluent.ClassRefBoundsNested<N>>{

        
    public N endClassRefBound();    public N and();
}


}
