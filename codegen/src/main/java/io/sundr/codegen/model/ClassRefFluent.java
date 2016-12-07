package io.sundr.codegen.model;

import java.lang.String;
import java.util.List;
import java.lang.Object;
import java.util.Map;

import io.sundr.builder.Nested;

public interface ClassRefFluent<A extends ClassRefFluent<A>> extends TypeRefFluent<A> {


    public TypeDef getDefinition();

    public A withDefinition(TypeDef definition);

    public ClassRefFluent.DefinitionNested<A> withNewDefinition();

    public ClassRefFluent.DefinitionNested<A> withNewDefinitionLike(TypeDef item);

    public ClassRefFluent.DefinitionNested<A> editDefinition();

    public String getFullyQualifiedName();

    public A withFullyQualifiedName(String fullyQualifiedName);

    public int getDimensions();

    public A withDimensions(int dimensions);

    public A addToArguments(TypeRef... items);

    public A removeFromArguments(TypeRef... items);

    public List<TypeRef> getArguments();

    public A withArguments(List<TypeRef> arguments);

    public A withArguments(TypeRef... arguments);

    public A addToVoidRefArguments(VoidRef... items);

    public A removeFromVoidRefArguments(VoidRef... items);

    public ClassRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgument();

    public ClassRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgumentLike(VoidRef item);

    public A addToWildcardRefArguments(WildcardRef... items);

    public A removeFromWildcardRefArguments(WildcardRef... items);

    public ClassRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgument();

    public ClassRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgumentLike(WildcardRef item);

    public A addToPrimitiveRefArguments(PrimitiveRef... items);

    public A removeFromPrimitiveRefArguments(PrimitiveRef... items);

    public ClassRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgument();

    public ClassRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgumentLike(PrimitiveRef item);

    public A addToTypeParamRefArguments(TypeParamRef... items);

    public A removeFromTypeParamRefArguments(TypeParamRef... items);

    public ClassRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgument();

    public ClassRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgumentLike(TypeParamRef item);

    public A addToClassRefArguments(ClassRef... items);

    public A removeFromClassRefArguments(ClassRef... items);

    public ClassRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgument();

    public ClassRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgumentLike(ClassRef item);

    public interface DefinitionNested<N> extends Nested<N>, TypeDefFluent<ClassRefFluent.DefinitionNested<N>> {


        public N endDefinition();

        public N and();
    }

    public interface VoidRefArgumentsNested<N> extends Nested<N>, VoidRefFluent<ClassRefFluent.VoidRefArgumentsNested<N>> {


        public N endVoidRefArgument();

        public N and();
    }

    public interface WildcardRefArgumentsNested<N> extends Nested<N>, WildcardRefFluent<ClassRefFluent.WildcardRefArgumentsNested<N>> {


        public N endWildcardRefArgument();

        public N and();
    }

    public interface PrimitiveRefArgumentsNested<N> extends Nested<N>, PrimitiveRefFluent<ClassRefFluent.PrimitiveRefArgumentsNested<N>> {


        public N endPrimitiveRefArgument();

        public N and();
    }

    public interface TypeParamRefArgumentsNested<N> extends Nested<N>, TypeParamRefFluent<ClassRefFluent.TypeParamRefArgumentsNested<N>> {


        public N endTypeParamRefArgument();

        public N and();
    }

    public interface ClassRefArgumentsNested<N> extends Nested<N>, ClassRefFluent<ClassRefFluent.ClassRefArgumentsNested<N>> {


        public N endClassRefArgument();

        public N and();
    }


}
