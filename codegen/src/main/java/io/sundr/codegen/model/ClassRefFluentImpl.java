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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.sundr.builder.Builder;
import io.sundr.builder.Nested;
import io.sundr.builder.Predicate;
import io.sundr.builder.VisitableBuilder;

public class ClassRefFluentImpl<A extends ClassRefFluent<A>> extends TypeRefFluentImpl<A> implements ClassRefFluent<A>{

    private TypeDef definition;
    private String fullyQualifiedName;
    private int dimensions;
    private List<VisitableBuilder<? extends TypeRef,?>> arguments =  new ArrayList<VisitableBuilder<? extends TypeRef,?>>();

    public ClassRefFluentImpl(){
    }
    public ClassRefFluentImpl(ClassRef instance){
            this.withDefinition(instance.getDefinition()); 
            this.withFullyQualifiedName(instance.getFullyQualifiedName()); 
            this.withDimensions(instance.getDimensions()); 
            this.withArguments(instance.getArguments()); 
            this.withAttributes(instance.getAttributes()); 
    }


    /**
     * This method has been deprecated, please use method buildDefinition instead.
     */
    @Deprecated
    public TypeDef getDefinition() {
        return this.definition;
    }

    public A withDefinition(TypeDef definition) {
        this.definition = definition;
        this.fullyQualifiedName = definition.getFullyQualifiedName();
        return (A) this;
    }

    public Boolean hasDefinition(){
            return this.definition!=null;
    }

    public ClassRefFluent.DefinitionNested<A> withNewDefinition() {
        return new DefinitionNestedImpl();
    }

    public ClassRefFluent.DefinitionNested<A> withNewDefinitionLike(TypeDef item) {
        return new DefinitionNestedImpl(item);
    }

    public ClassRefFluent.DefinitionNested<A> editDefinition() {
        return withNewDefinitionLike(getDefinition());
    }

    public String getFullyQualifiedName(){
            return this.fullyQualifiedName;
    }

    public A withFullyQualifiedName(String fullyQualifiedName){
            this.fullyQualifiedName=fullyQualifiedName; return (A) this;
    }

    public Boolean hasFullyQualifiedName(){
            return this.fullyQualifiedName!=null;
    }

    public int getDimensions(){
            return this.dimensions;
    }

    public A withDimensions(int dimensions){
            this.dimensions=dimensions; return (A) this;
    }

    public Boolean hasDimensions(){
            return true;
    }

    public A addToArguments(int index,TypeRef item){
            if (item instanceof VoidRef){addToVoidRefArguments(index, (VoidRef)item);}
 else if (item instanceof WildcardRef){addToWildcardRefArguments(index, (WildcardRef)item);}
 else if (item instanceof PrimitiveRef){addToPrimitiveRefArguments(index, (PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){addToTypeParamRefArguments(index, (TypeParamRef)item);}
 else if (item instanceof ClassRef){addToClassRefArguments(index, (ClassRef)item);}

            return (A)this;
    }

    public A setToArguments(int index,TypeRef item){
            if (item instanceof VoidRef){setToVoidRefArguments(index, (VoidRef)item);}
 else if (item instanceof WildcardRef){setToWildcardRefArguments(index, (WildcardRef)item);}
 else if (item instanceof PrimitiveRef){setToPrimitiveRefArguments(index, (PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){setToTypeParamRefArguments(index, (TypeParamRef)item);}
 else if (item instanceof ClassRef){setToClassRefArguments(index, (ClassRef)item);}

            return (A)this;
    }

    public A addToArguments(TypeRef... items){
            for (TypeRef item : items) {
            if (item instanceof VoidRef){addToVoidRefArguments((VoidRef)item);}
 else if (item instanceof WildcardRef){addToWildcardRefArguments((WildcardRef)item);}
 else if (item instanceof PrimitiveRef){addToPrimitiveRefArguments((PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){addToTypeParamRefArguments((TypeParamRef)item);}
 else if (item instanceof ClassRef){addToClassRefArguments((ClassRef)item);}

            } return (A)this;
    }

    public A addAllToArguments(Collection<TypeRef> items){
            for (TypeRef item : items) {
            if (item instanceof VoidRef){addToVoidRefArguments((VoidRef)item);}
 else if (item instanceof WildcardRef){addToWildcardRefArguments((WildcardRef)item);}
 else if (item instanceof PrimitiveRef){addToPrimitiveRefArguments((PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){addToTypeParamRefArguments((TypeParamRef)item);}
 else if (item instanceof ClassRef){addToClassRefArguments((ClassRef)item);}

            } return (A)this;
    }

    public A removeFromArguments(TypeRef... items){
            for (TypeRef item : items) {if (item instanceof VoidRef){removeFromVoidRefArguments((VoidRef)item);}
 else if (item instanceof WildcardRef){removeFromWildcardRefArguments((WildcardRef)item);}
 else if (item instanceof PrimitiveRef){removeFromPrimitiveRefArguments((PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){removeFromTypeParamRefArguments((TypeParamRef)item);}
 else if (item instanceof ClassRef){removeFromClassRefArguments((ClassRef)item);}
} return (A)this;
    }

    public A removeAllFromArguments(Collection<TypeRef> items){
            for (TypeRef item : items) {if (item instanceof VoidRef){removeFromVoidRefArguments((VoidRef)item);}
 else if (item instanceof WildcardRef){removeFromWildcardRefArguments((WildcardRef)item);}
 else if (item instanceof PrimitiveRef){removeFromPrimitiveRefArguments((PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){removeFromTypeParamRefArguments((TypeParamRef)item);}
 else if (item instanceof ClassRef){removeFromClassRefArguments((ClassRef)item);}
} return (A)this;
    }


/**
 * This method has been deprecated, please use method buildArguments instead.
 */
@Deprecated public List<TypeRef> getArguments(){
            return build(arguments);
    }

    public List<TypeRef> buildArguments(){
            return build(arguments);
    }

    public TypeRef buildArgument(int index){
            return this.arguments.get(index).build();
    }

    public TypeRef buildFirstArgument(){
            return this.arguments.get(0).build();
    }

    public TypeRef buildLastArgument(){
            return this.arguments.get(arguments.size() - 1).build();
    }

    public TypeRef buildMatchingArgument(Predicate<Builder<? extends TypeRef>> predicate){
            for (Builder<? extends TypeRef> item: arguments) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public A withArguments(List<TypeRef> arguments){
            _visitables.removeAll(this.arguments);
            this.arguments.clear();
            if (arguments != null) {for (TypeRef item : arguments){this.addToArguments(item);}} return (A) this;
    }

    public A withArguments(TypeRef... arguments){
            this.arguments.clear(); if (arguments != null) {for (TypeRef item :arguments){ this.addToArguments(item);}} return (A) this;
    }

    public Boolean hasArguments(){
            return arguments!= null && !arguments.isEmpty();
    }

    public A addToVoidRefArguments(int index,VoidRef item){
            VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.add(builder);this.arguments.add(builder); return (A)this;
    }

    public A setToVoidRefArguments(int index,VoidRef item){
            VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.add(builder);this.arguments.add(builder); return (A)this;
    }

    public A addToVoidRefArguments(VoidRef... items){
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A addAllToVoidRefArguments(Collection<VoidRef> items){
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A removeFromVoidRefArguments(VoidRef... items){
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public A removeAllFromVoidRefArguments(Collection<VoidRef> items){
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public VoidRefArgumentsNested<A> addNewVoidRefArgument(){
            return new VoidRefArgumentsNestedImpl();
    }

    public VoidRefArgumentsNested<A> addNewVoidRefArgumentLike(VoidRef item){
            return new VoidRefArgumentsNestedImpl(-1, item);
    }

    public A addToWildcardRefArguments(int index,WildcardRef item){
            WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.add(builder);this.arguments.add(builder); return (A)this;
    }

    public A setToWildcardRefArguments(int index,WildcardRef item){
            WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.add(builder);this.arguments.add(builder); return (A)this;
    }

    public A addToWildcardRefArguments(WildcardRef... items){
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A addAllToWildcardRefArguments(Collection<WildcardRef> items){
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A removeFromWildcardRefArguments(WildcardRef... items){
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public A removeAllFromWildcardRefArguments(Collection<WildcardRef> items){
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public WildcardRefArgumentsNested<A> addNewWildcardRefArgument(){
            return new WildcardRefArgumentsNestedImpl();
    }

    public WildcardRefArgumentsNested<A> addNewWildcardRefArgumentLike(WildcardRef item){
            return new WildcardRefArgumentsNestedImpl(-1, item);
    }

    public A addToPrimitiveRefArguments(int index,PrimitiveRef item){
            PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.add(builder);this.arguments.add(builder); return (A)this;
    }

    public A setToPrimitiveRefArguments(int index,PrimitiveRef item){
            PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.add(builder);this.arguments.add(builder); return (A)this;
    }

    public A addToPrimitiveRefArguments(PrimitiveRef... items){
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A addAllToPrimitiveRefArguments(Collection<PrimitiveRef> items){
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A removeFromPrimitiveRefArguments(PrimitiveRef... items){
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public A removeAllFromPrimitiveRefArguments(Collection<PrimitiveRef> items){
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgument(){
            return new PrimitiveRefArgumentsNestedImpl();
    }

    public PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgumentLike(PrimitiveRef item){
            return new PrimitiveRefArgumentsNestedImpl(-1, item);
    }

    public A addToTypeParamRefArguments(int index,TypeParamRef item){
            TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.add(builder);this.arguments.add(builder); return (A)this;
    }

    public A setToTypeParamRefArguments(int index,TypeParamRef item){
            TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.add(builder);this.arguments.add(builder); return (A)this;
    }

    public A addToTypeParamRefArguments(TypeParamRef... items){
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A addAllToTypeParamRefArguments(Collection<TypeParamRef> items){
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A removeFromTypeParamRefArguments(TypeParamRef... items){
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public A removeAllFromTypeParamRefArguments(Collection<TypeParamRef> items){
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public TypeParamRefArgumentsNested<A> addNewTypeParamRefArgument(){
            return new TypeParamRefArgumentsNestedImpl();
    }

    public TypeParamRefArgumentsNested<A> addNewTypeParamRefArgumentLike(TypeParamRef item){
            return new TypeParamRefArgumentsNestedImpl(-1, item);
    }

    public A addToClassRefArguments(int index,ClassRef item){
            ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.arguments.add(builder); return (A)this;
    }

    public A setToClassRefArguments(int index,ClassRef item){
            ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.arguments.add(builder); return (A)this;
    }

    public A addToClassRefArguments(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A addAllToClassRefArguments(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A removeFromClassRefArguments(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public A removeAllFromClassRefArguments(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public ClassRefArgumentsNested<A> addNewClassRefArgument(){
            return new ClassRefArgumentsNestedImpl();
    }

    public ClassRefArgumentsNested<A> addNewClassRefArgumentLike(ClassRef item){
            return new ClassRefArgumentsNestedImpl(-1, item);
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            ClassRefFluentImpl that = (ClassRefFluentImpl) o;
            if (definition != null ? !definition.equals(that.definition) :that.definition != null) return false;
            if (fullyQualifiedName != null ? !fullyQualifiedName.equals(that.fullyQualifiedName) :that.fullyQualifiedName != null) return false;
            if (dimensions != that.dimensions) return false;
            if (arguments != null ? !arguments.equals(that.arguments) :that.arguments != null) return false;
            return true;
    }


    public class DefinitionNestedImpl<N> extends TypeDefFluentImpl<DefinitionNested<N>> implements DefinitionNested<N>,Nested<N>{

            private final TypeDefBuilder builder;

            DefinitionNestedImpl(TypeDef item){
                    this.builder = new TypeDefBuilder(this, item);
            }
            DefinitionNestedImpl(){
                    this.builder = new TypeDefBuilder(this);
            }

    public N and(){
            return (N) ClassRefFluentImpl.this.withDefinition(builder.build());
    }
    public N endDefinition(){
            return and();
    }

}
    public class VoidRefArgumentsNestedImpl<N> extends VoidRefFluentImpl<VoidRefArgumentsNested<N>> implements VoidRefArgumentsNested<N>,Nested<N>{

            private final VoidRefBuilder builder;
        private final int index;

            VoidRefArgumentsNestedImpl(int index,VoidRef item){
                    this.index = index;
                    this.builder = new VoidRefBuilder(this, item);
            }
            VoidRefArgumentsNestedImpl(){
                    this.index = -1;
                    this.builder = new VoidRefBuilder(this);
            }

    public N and(){
            return (N) ClassRefFluentImpl.this.addToVoidRefArguments(index, builder.build());
    }
    public N endVoidRefArgument(){
            return and();
    }

}
    public class WildcardRefArgumentsNestedImpl<N> extends WildcardRefFluentImpl<WildcardRefArgumentsNested<N>> implements WildcardRefArgumentsNested<N>,Nested<N>{

            private final WildcardRefBuilder builder;
        private final int index;

            WildcardRefArgumentsNestedImpl(int index,WildcardRef item){
                    this.index = index;
                    this.builder = new WildcardRefBuilder(this, item);
            }
            WildcardRefArgumentsNestedImpl(){
                    this.index = -1;
                    this.builder = new WildcardRefBuilder(this);
            }

    public N and(){
            return (N) ClassRefFluentImpl.this.addToWildcardRefArguments(index, builder.build());
    }
    public N endWildcardRefArgument(){
            return and();
    }

}
    public class PrimitiveRefArgumentsNestedImpl<N> extends PrimitiveRefFluentImpl<PrimitiveRefArgumentsNested<N>> implements PrimitiveRefArgumentsNested<N>,Nested<N>{

            private final PrimitiveRefBuilder builder;
        private final int index;

            PrimitiveRefArgumentsNestedImpl(int index,PrimitiveRef item){
                    this.index = index;
                    this.builder = new PrimitiveRefBuilder(this, item);
            }
            PrimitiveRefArgumentsNestedImpl(){
                    this.index = -1;
                    this.builder = new PrimitiveRefBuilder(this);
            }

    public N and(){
            return (N) ClassRefFluentImpl.this.addToPrimitiveRefArguments(index, builder.build());
    }
    public N endPrimitiveRefArgument(){
            return and();
    }

}
    public class TypeParamRefArgumentsNestedImpl<N> extends TypeParamRefFluentImpl<TypeParamRefArgumentsNested<N>> implements TypeParamRefArgumentsNested<N>,Nested<N>{

            private final TypeParamRefBuilder builder;
        private final int index;

            TypeParamRefArgumentsNestedImpl(int index,TypeParamRef item){
                    this.index = index;
                    this.builder = new TypeParamRefBuilder(this, item);
            }
            TypeParamRefArgumentsNestedImpl(){
                    this.index = -1;
                    this.builder = new TypeParamRefBuilder(this);
            }

    public N and(){
            return (N) ClassRefFluentImpl.this.addToTypeParamRefArguments(index, builder.build());
    }
    public N endTypeParamRefArgument(){
            return and();
    }

}
    public class ClassRefArgumentsNestedImpl<N> extends ClassRefFluentImpl<ClassRefArgumentsNested<N>> implements ClassRefArgumentsNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
        private final int index;
    
            ClassRefArgumentsNestedImpl(int index,ClassRef item){
                    this.index = index;
                    this.builder = new ClassRefBuilder(this, item);
            }
            ClassRefArgumentsNestedImpl(){
                    this.index = -1;
                    this.builder = new ClassRefBuilder(this);
            }
    
    public N and(){
            return (N) ClassRefFluentImpl.this.addToClassRefArguments(index, builder.build());
    }
    public N endClassRefArgument(){
            return and();
    }

}


}
