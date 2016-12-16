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

public class MethodFluentImpl<A extends MethodFluent<A>> extends ModifierSupportFluentImpl<A> implements MethodFluent<A>{

    private List<String> comments = new ArrayList<String>();
    private List<VisitableBuilder<? extends AnnotationRef,?>> annotations =  new ArrayList<VisitableBuilder<? extends AnnotationRef,?>>();
    private List<VisitableBuilder<? extends TypeParamDef,?>> parameters =  new ArrayList<VisitableBuilder<? extends TypeParamDef,?>>();
    private String name;
    private VisitableBuilder<? extends TypeRef,?> returnType;
    private List<VisitableBuilder<? extends Property,?>> arguments =  new ArrayList<VisitableBuilder<? extends Property,?>>();
    private boolean varArgPreferred;
    private List<VisitableBuilder<? extends ClassRef,?>> exceptions =  new ArrayList<VisitableBuilder<? extends ClassRef,?>>();
    private VisitableBuilder<? extends Block,?> block;

    public MethodFluentImpl(){
    }
    public MethodFluentImpl(Method instance){
            this.withComments(instance.getComments()); 
            this.withAnnotations(instance.getAnnotations()); 
            this.withParameters(instance.getParameters()); 
            this.withName(instance.getName()); 
            this.withReturnType(instance.getReturnType()); 
            this.withArguments(instance.getArguments()); 
            this.withVarArgPreferred(instance.isVarArgPreferred()); 
            this.withExceptions(instance.getExceptions()); 
            this.withBlock(instance.getBlock()); 
            this.withModifiers(instance.getModifiers()); 
            this.withAttributes(instance.getAttributes()); 
    }

    public A addToComments(int index,String item){
            this.comments.add(index, item); return (A)this;
    }

    public A setToComments(int index,String item){
            this.comments.set(index, item); return (A)this;
    }

    public A addToComments(String... items){
            for (String item : items) {this.comments.add(item);} return (A)this;
    }

    public A addAllToComments(Collection<String> items){
            for (String item : items) {this.comments.add(item);} return (A)this;
    }

    public A removeFromComments(String... items){
            for (String item : items) {this.comments.remove(item);} return (A)this;
    }

    public A removeAllFromComments(Collection<String> items){
            for (String item : items) {this.comments.remove(item);} return (A)this;
    }

    public List<String> getComments(){
            return this.comments;
    }

    public String getComment(int index){
            return this.comments.get(index);
    }

    public String getFirstComment(){
            return this.comments.get(0);
    }

    public String getLastComment(){
            return this.comments.get(comments.size() - 1);
    }

    public String getMatchingComment(Predicate<String> predicate){
            for (String item: comments) { if(predicate.apply(item)){return item;} } return null;
    }

    public A withComments(List<String> comments){
            this.comments.clear();
            if (comments != null) {for (String item : comments){this.addToComments(item);}} return (A) this;
    }

    public A withComments(String... comments){
            this.comments.clear(); if (comments != null) {for (String item :comments){ this.addToComments(item);}} return (A) this;
    }

    public Boolean hasComments(){
            return comments!= null && !comments.isEmpty();
    }

    public A addToAnnotations(int index,AnnotationRef item){
            AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.add(builder);this.annotations.add(builder); return (A)this;
    }

    public A setToAnnotations(int index,AnnotationRef item){
            AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.add(builder);this.annotations.add(builder); return (A)this;
    }

    public A addToAnnotations(AnnotationRef... items){
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.add(builder);this.annotations.add(builder);} return (A)this;
    }

    public A addAllToAnnotations(Collection<AnnotationRef> items){
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.add(builder);this.annotations.add(builder);} return (A)this;
    }

    public A removeFromAnnotations(AnnotationRef... items){
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.remove(builder);this.annotations.remove(builder);} return (A)this;
    }

    public A removeAllFromAnnotations(Collection<AnnotationRef> items){
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.remove(builder);this.annotations.remove(builder);} return (A)this;
    }

    
/**
 * This method has been deprecated, please use method buildAnnotations instead.
 */
@Deprecated public List<AnnotationRef> getAnnotations(){
            return build(annotations);
    }

    public List<AnnotationRef> buildAnnotations(){
            return build(annotations);
    }

    public AnnotationRef buildAnnotation(int index){
            return this.annotations.get(index).build();
    }

    public AnnotationRef buildFirstAnnotation(){
            return this.annotations.get(0).build();
    }

    public AnnotationRef buildLastAnnotation(){
            return this.annotations.get(annotations.size() - 1).build();
    }

    public AnnotationRef buildMatchingAnnotation(Predicate<Builder<? extends AnnotationRef>> predicate){
            for (Builder<? extends AnnotationRef> item: annotations) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public A withAnnotations(List<AnnotationRef> annotations){
            _visitables.removeAll(this.annotations);
            this.annotations.clear();
            if (annotations != null) {for (AnnotationRef item : annotations){this.addToAnnotations(item);}} return (A) this;
    }

    public A withAnnotations(AnnotationRef... annotations){
            this.annotations.clear(); if (annotations != null) {for (AnnotationRef item :annotations){ this.addToAnnotations(item);}} return (A) this;
    }

    public Boolean hasAnnotations(){
            return annotations!= null && !annotations.isEmpty();
    }

    public AnnotationsNested<A> addNewAnnotation(){
            return new AnnotationsNestedImpl();
    }

    public AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item){
            return new AnnotationsNestedImpl(-1, item);
    }

    public A addToParameters(int index,TypeParamDef item){
            TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.add(builder);this.parameters.add(builder); return (A)this;
    }

    public A setToParameters(int index,TypeParamDef item){
            TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.add(builder);this.parameters.add(builder); return (A)this;
    }

    public A addToParameters(TypeParamDef... items){
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.add(builder);this.parameters.add(builder);} return (A)this;
    }

    public A addAllToParameters(Collection<TypeParamDef> items){
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.add(builder);this.parameters.add(builder);} return (A)this;
    }

    public A removeFromParameters(TypeParamDef... items){
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.remove(builder);this.parameters.remove(builder);} return (A)this;
    }

    public A removeAllFromParameters(Collection<TypeParamDef> items){
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.remove(builder);this.parameters.remove(builder);} return (A)this;
    }


/**
 * This method has been deprecated, please use method buildParameters instead.
 */
@Deprecated public List<TypeParamDef> getParameters(){
            return build(parameters);
    }

    public List<TypeParamDef> buildParameters(){
            return build(parameters);
    }

    public TypeParamDef buildParameter(int index){
            return this.parameters.get(index).build();
    }

    public TypeParamDef buildFirstParameter(){
            return this.parameters.get(0).build();
    }

    public TypeParamDef buildLastParameter(){
            return this.parameters.get(parameters.size() - 1).build();
    }

    public TypeParamDef buildMatchingParameter(Predicate<Builder<? extends TypeParamDef>> predicate){
            for (Builder<? extends TypeParamDef> item: parameters) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public A withParameters(List<TypeParamDef> parameters){
            _visitables.removeAll(this.parameters);
            this.parameters.clear();
            if (parameters != null) {for (TypeParamDef item : parameters){this.addToParameters(item);}} return (A) this;
    }

    public A withParameters(TypeParamDef... parameters){
            this.parameters.clear(); if (parameters != null) {for (TypeParamDef item :parameters){ this.addToParameters(item);}} return (A) this;
    }

    public Boolean hasParameters(){
            return parameters!= null && !parameters.isEmpty();
    }

    public ParametersNested<A> addNewParameter(){
            return new ParametersNestedImpl();
    }

    public ParametersNested<A> addNewParameterLike(TypeParamDef item){
            return new ParametersNestedImpl(-1, item);
    }

    public String getName(){
            return this.name;
    }

    public A withName(String name){
            this.name=name; return (A) this;
    }

    public Boolean hasName(){
            return this.name!=null;
    }


/**
 * This method has been deprecated, please use method buildReturnType instead.
 */
@Deprecated public TypeRef getReturnType(){
            return this.returnType!=null?this.returnType.build():null;
    }

    public TypeRef buildReturnType(){
            return this.returnType!=null?this.returnType.build():null;
    }

    public A withReturnType(TypeRef returnType){
            _visitables.remove(this.returnType);
            if (returnType instanceof VoidRef){ this.returnType= new VoidRefBuilder((VoidRef)returnType); _visitables.add(this.returnType);}
            if (returnType instanceof WildcardRef){ this.returnType= new WildcardRefBuilder((WildcardRef)returnType); _visitables.add(this.returnType);}
            if (returnType instanceof PrimitiveRef){ this.returnType= new PrimitiveRefBuilder((PrimitiveRef)returnType); _visitables.add(this.returnType);}
            if (returnType instanceof TypeParamRef){ this.returnType= new TypeParamRefBuilder((TypeParamRef)returnType); _visitables.add(this.returnType);}
            if (returnType instanceof ClassRef){ this.returnType= new ClassRefBuilder((ClassRef)returnType); _visitables.add(this.returnType);}
            return (A) this;
    }

    public Boolean hasReturnType(){
            return this.returnType!=null;
    }

    public A withVoidRefReturnType(VoidRef voidRefReturnType){
            _visitables.remove(this.returnType);
            if (voidRefReturnType!=null){ this.returnType= new VoidRefBuilder(voidRefReturnType); _visitables.add(this.returnType);} return (A) this;
    }

    public VoidRefReturnTypeNested<A> withNewVoidRefReturnType(){
            return new VoidRefReturnTypeNestedImpl();
    }

    public VoidRefReturnTypeNested<A> withNewVoidRefReturnTypeLike(VoidRef item){
            return new VoidRefReturnTypeNestedImpl(item);
    }

    public A withWildcardRefReturnType(WildcardRef wildcardRefReturnType){
            _visitables.remove(this.returnType);
            if (wildcardRefReturnType!=null){ this.returnType= new WildcardRefBuilder(wildcardRefReturnType); _visitables.add(this.returnType);} return (A) this;
    }

    public WildcardRefReturnTypeNested<A> withNewWildcardRefReturnType(){
            return new WildcardRefReturnTypeNestedImpl();
    }

    public WildcardRefReturnTypeNested<A> withNewWildcardRefReturnTypeLike(WildcardRef item){
            return new WildcardRefReturnTypeNestedImpl(item);
    }

    public A withPrimitiveRefReturnType(PrimitiveRef primitiveRefReturnType){
            _visitables.remove(this.returnType);
            if (primitiveRefReturnType!=null){ this.returnType= new PrimitiveRefBuilder(primitiveRefReturnType); _visitables.add(this.returnType);} return (A) this;
    }

    public PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnType(){
            return new PrimitiveRefReturnTypeNestedImpl();
    }

    public PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnTypeLike(PrimitiveRef item){
            return new PrimitiveRefReturnTypeNestedImpl(item);
    }

    public A withTypeParamRefReturnType(TypeParamRef typeParamRefReturnType){
            _visitables.remove(this.returnType);
            if (typeParamRefReturnType!=null){ this.returnType= new TypeParamRefBuilder(typeParamRefReturnType); _visitables.add(this.returnType);} return (A) this;
    }

    public TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnType(){
            return new TypeParamRefReturnTypeNestedImpl();
    }

    public TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnTypeLike(TypeParamRef item){
            return new TypeParamRefReturnTypeNestedImpl(item);
    }

    public A withClassRefReturnType(ClassRef classRefReturnType){
            _visitables.remove(this.returnType);
            if (classRefReturnType!=null){ this.returnType= new ClassRefBuilder(classRefReturnType); _visitables.add(this.returnType);} return (A) this;
    }

    public ClassRefReturnTypeNested<A> withNewClassRefReturnType(){
            return new ClassRefReturnTypeNestedImpl();
    }

    public ClassRefReturnTypeNested<A> withNewClassRefReturnTypeLike(ClassRef item){
            return new ClassRefReturnTypeNestedImpl(item);
    }

    public A addToArguments(int index, Property item) {
        PropertyBuilder builder = new PropertyBuilder(item);
        _visitables.add(index >= 0 ? index : _visitables.size(), builder);
        this.arguments.add(index >= 0 ? index : arguments.size(), builder);
        return (A) this;

    }

    public A setToArguments(int index,Property item){
        PropertyBuilder builder = new PropertyBuilder(item);
        if (index < 0 || index >= _visitables.size()) { _visitables.add(builder); } else { _visitables.set(index, builder);}
        if (index < 0 || index >= arguments.size()) { arguments.add(builder); } else { arguments.set(index, builder);}
        return (A) this;
    }

    public A addToArguments(Property... items){
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A addAllToArguments(Collection<Property> items){
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A removeFromArguments(Property... items){
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    public A removeAllFromArguments(Collection<Property> items){
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }


/**
 * This method has been deprecated, please use method buildArguments instead.
 */
@Deprecated public List<Property> getArguments(){
            return build(arguments);
    }

    public List<Property> buildArguments(){
            return build(arguments);
    }

    public Property buildArgument(int index){
            return this.arguments.get(index).build();
    }

    public Property buildFirstArgument(){
            return this.arguments.get(0).build();
    }

    public Property buildLastArgument(){
            return this.arguments.get(arguments.size() - 1).build();
    }

    public Property buildMatchingArgument(Predicate<Builder<? extends Property>> predicate){
            for (Builder<? extends Property> item: arguments) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public A withArguments(List<Property> arguments){
            _visitables.removeAll(this.arguments);
            this.arguments.clear();
            if (arguments != null) {for (Property item : arguments){this.addToArguments(item);}} return (A) this;
    }

    public A withArguments(Property... arguments){
            this.arguments.clear(); if (arguments != null) {for (Property item :arguments){ this.addToArguments(item);}} return (A) this;
    }

    public Boolean hasArguments(){
            return arguments!= null && !arguments.isEmpty();
    }

    public ArgumentsNested<A> addNewArgument(){
            return new ArgumentsNestedImpl();
    }

    public ArgumentsNested<A> addNewArgumentLike(Property item){
            return new ArgumentsNestedImpl(-1, item);
    }

    public boolean isVarArgPreferred(){
            return this.varArgPreferred;
    }

    public A withVarArgPreferred(boolean varArgPreferred){
            this.varArgPreferred=varArgPreferred; return (A) this;
    }

    public Boolean hasVarArgPreferred(){
            return true;
    }

    public A addToExceptions(int index,ClassRef item){
            ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.exceptions.add(builder); return (A)this;
    }

    public A setToExceptions(int index,ClassRef item){
            ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.exceptions.add(builder); return (A)this;
    }

    public A addToExceptions(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.exceptions.add(builder);} return (A)this;
    }

    public A addAllToExceptions(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.exceptions.add(builder);} return (A)this;
    }

    public A removeFromExceptions(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.exceptions.remove(builder);} return (A)this;
    }

    public A removeAllFromExceptions(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.exceptions.remove(builder);} return (A)this;
    }


/**
 * This method has been deprecated, please use method buildExceptions instead.
 */
@Deprecated public List<ClassRef> getExceptions(){
            return build(exceptions);
    }

    public List<ClassRef> buildExceptions(){
            return build(exceptions);
    }

    public ClassRef buildException(int index){
            return this.exceptions.get(index).build();
    }

    public ClassRef buildFirstException(){
            return this.exceptions.get(0).build();
    }

    public ClassRef buildLastException(){
            return this.exceptions.get(exceptions.size() - 1).build();
    }

    public ClassRef buildMatchingException(Predicate<Builder<? extends ClassRef>> predicate){
            for (Builder<? extends ClassRef> item: exceptions) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public A withExceptions(List<ClassRef> exceptions){
            _visitables.removeAll(this.exceptions);
            this.exceptions.clear();
            if (exceptions != null) {for (ClassRef item : exceptions){this.addToExceptions(item);}} return (A) this;
    }

    public A withExceptions(ClassRef... exceptions){
            this.exceptions.clear(); if (exceptions != null) {for (ClassRef item :exceptions){ this.addToExceptions(item);}} return (A) this;
    }

    public Boolean hasExceptions(){
            return exceptions!= null && !exceptions.isEmpty();
    }

    public ExceptionsNested<A> addNewException(){
            return new ExceptionsNestedImpl();
    }

    public ExceptionsNested<A> addNewExceptionLike(ClassRef item){
            return new ExceptionsNestedImpl(-1, item);
    }


/**
 * This method has been deprecated, please use method buildBlock instead.
 */
@Deprecated public Block getBlock(){
            return this.block!=null?this.block.build():null;
    }

    public Block buildBlock(){
            return this.block!=null?this.block.build():null;
    }

    public A withBlock(Block block){
            _visitables.remove(this.block);
            if (block!=null){ this.block= new BlockBuilder(block); _visitables.add(this.block);} return (A) this;
    }

    public Boolean hasBlock(){
            return this.block!=null;
    }

    public BlockNested<A> withNewBlock(){
            return new BlockNestedImpl();
    }

    public BlockNested<A> withNewBlockLike(Block item){
            return new BlockNestedImpl(item);
    }

    public BlockNested<A> editBlock(){
            return withNewBlockLike(getBlock());
    }

    public BlockNested<A> editOrNewBlock(){
            return withNewBlockLike(getBlock() != null ? getBlock(): new BlockBuilder().build());
    }

    public BlockNested<A> editOrNewBlockLike(Block item){
            return withNewBlockLike(getBlock() != null ? getBlock(): item);
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            MethodFluentImpl that = (MethodFluentImpl) o;
            if (comments != null ? !comments.equals(that.comments) :that.comments != null) return false;
            if (annotations != null ? !annotations.equals(that.annotations) :that.annotations != null) return false;
            if (parameters != null ? !parameters.equals(that.parameters) :that.parameters != null) return false;
            if (name != null ? !name.equals(that.name) :that.name != null) return false;
            if (returnType != null ? !returnType.equals(that.returnType) :that.returnType != null) return false;
            if (arguments != null ? !arguments.equals(that.arguments) :that.arguments != null) return false;
            if (varArgPreferred != that.varArgPreferred) return false;
            if (exceptions != null ? !exceptions.equals(that.exceptions) :that.exceptions != null) return false;
            if (block != null ? !block.equals(that.block) :that.block != null) return false;
            return true;
    }


    public class AnnotationsNestedImpl<N> extends AnnotationRefFluentImpl<AnnotationsNested<N>> implements AnnotationsNested<N>,Nested<N>{

            private final AnnotationRefBuilder builder;
        private final int index;

            AnnotationsNestedImpl(int index,AnnotationRef item){
                    this.index = index;
                    this.builder = new AnnotationRefBuilder(this, item);
            }
            AnnotationsNestedImpl(){
                    this.index = -1;
                    this.builder = new AnnotationRefBuilder(this);
            }

    public N and(){
            return (N) MethodFluentImpl.this.addToAnnotations(index, builder.build());
    }
    public N endAnnotation(){
            return and();
    }

}
    public class ParametersNestedImpl<N> extends TypeParamDefFluentImpl<ParametersNested<N>> implements ParametersNested<N>,Nested<N>{

            private final TypeParamDefBuilder builder;
        private final int index;

            ParametersNestedImpl(int index,TypeParamDef item){
                    this.index = index;
                    this.builder = new TypeParamDefBuilder(this, item);
            }
            ParametersNestedImpl(){
                    this.index = -1;
                    this.builder = new TypeParamDefBuilder(this);
            }

    public N and(){
            return (N) MethodFluentImpl.this.addToParameters(index, builder.build());
    }
    public N endParameter(){
            return and();
    }

}
    public class VoidRefReturnTypeNestedImpl<N> extends VoidRefFluentImpl<VoidRefReturnTypeNested<N>> implements VoidRefReturnTypeNested<N>,Nested<N>{

            private final VoidRefBuilder builder;

            VoidRefReturnTypeNestedImpl(VoidRef item){
                    this.builder = new VoidRefBuilder(this, item);
            }
            VoidRefReturnTypeNestedImpl(){
                    this.builder = new VoidRefBuilder(this);
            }

    public N and(){
            return (N) MethodFluentImpl.this.withVoidRefReturnType(builder.build());
    }
    public N endVoidRefReturnType(){
            return and();
    }

}
    public class WildcardRefReturnTypeNestedImpl<N> extends WildcardRefFluentImpl<WildcardRefReturnTypeNested<N>> implements WildcardRefReturnTypeNested<N>,Nested<N>{

            private final WildcardRefBuilder builder;

            WildcardRefReturnTypeNestedImpl(WildcardRef item){
                    this.builder = new WildcardRefBuilder(this, item);
            }
            WildcardRefReturnTypeNestedImpl(){
                    this.builder = new WildcardRefBuilder(this);
            }

    public N and(){
            return (N) MethodFluentImpl.this.withWildcardRefReturnType(builder.build());
    }
    public N endWildcardRefReturnType(){
            return and();
    }

}
    public class PrimitiveRefReturnTypeNestedImpl<N> extends PrimitiveRefFluentImpl<PrimitiveRefReturnTypeNested<N>> implements PrimitiveRefReturnTypeNested<N>,Nested<N>{

            private final PrimitiveRefBuilder builder;

            PrimitiveRefReturnTypeNestedImpl(PrimitiveRef item){
                    this.builder = new PrimitiveRefBuilder(this, item);
            }
            PrimitiveRefReturnTypeNestedImpl(){
                    this.builder = new PrimitiveRefBuilder(this);
            }

    public N and(){
            return (N) MethodFluentImpl.this.withPrimitiveRefReturnType(builder.build());
    }
    public N endPrimitiveRefReturnType(){
            return and();
    }

}
    public class TypeParamRefReturnTypeNestedImpl<N> extends TypeParamRefFluentImpl<TypeParamRefReturnTypeNested<N>> implements TypeParamRefReturnTypeNested<N>,Nested<N>{

            private final TypeParamRefBuilder builder;

            TypeParamRefReturnTypeNestedImpl(TypeParamRef item){
                    this.builder = new TypeParamRefBuilder(this, item);
            }
            TypeParamRefReturnTypeNestedImpl(){
                    this.builder = new TypeParamRefBuilder(this);
            }

    public N and(){
            return (N) MethodFluentImpl.this.withTypeParamRefReturnType(builder.build());
    }
    public N endTypeParamRefReturnType(){
            return and();
    }

}
    public class ClassRefReturnTypeNestedImpl<N> extends ClassRefFluentImpl<ClassRefReturnTypeNested<N>> implements ClassRefReturnTypeNested<N>,Nested<N>{

            private final ClassRefBuilder builder;

            ClassRefReturnTypeNestedImpl(ClassRef item){
                    this.builder = new ClassRefBuilder(this, item);
            }
            ClassRefReturnTypeNestedImpl(){
                    this.builder = new ClassRefBuilder(this);
            }

    public N and(){
            return (N) MethodFluentImpl.this.withClassRefReturnType(builder.build());
    }
    public N endClassRefReturnType(){
            return and();
    }

}
    public class ArgumentsNestedImpl<N> extends PropertyFluentImpl<ArgumentsNested<N>> implements ArgumentsNested<N>,Nested<N>{

            private final PropertyBuilder builder;
        private final int index;

            ArgumentsNestedImpl(int index,Property item){
                    this.index = index;
                    this.builder = new PropertyBuilder(this, item);
            }
            ArgumentsNestedImpl(){
                    this.index = -1;
                    this.builder = new PropertyBuilder(this);
            }

    public N and(){
            return (N) MethodFluentImpl.this.addToArguments(index, builder.build());
    }
    public N endArgument(){
            return and();
    }

}
    public class ExceptionsNestedImpl<N> extends ClassRefFluentImpl<ExceptionsNested<N>> implements ExceptionsNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
        private final int index;

            ExceptionsNestedImpl(int index,ClassRef item){
                    this.index = index;
                    this.builder = new ClassRefBuilder(this, item);
            }
            ExceptionsNestedImpl(){
                    this.index = -1;
                    this.builder = new ClassRefBuilder(this);
            }

    public N and(){
            return (N) MethodFluentImpl.this.addToExceptions(index, builder.build());
    }
    public N endException(){
            return and();
    }

}
    public class BlockNestedImpl<N> extends BlockFluentImpl<BlockNested<N>> implements BlockNested<N>,Nested<N>{

            private final BlockBuilder builder;
    
            BlockNestedImpl(Block item){
                    this.builder = new BlockBuilder(this, item);
            }
            BlockNestedImpl(){
                    this.builder = new BlockBuilder(this);
            }
    
    public N and(){
            return (N) MethodFluentImpl.this.withBlock(builder.build());
    }
    public N endBlock(){
            return and();
    }

}


}
