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
import java.util.List;

import io.sundr.builder.Nested;
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

    public A addToComments(String... items){
            for (String item : items) {this.comments.add(item);} return (A)this;
    }

    public A removeFromComments(String... items){
            for (String item : items) {this.comments.remove(item);} return (A)this;
    }

    public List<String> getComments(){
            return this.comments;
    }

    public A withComments(List<String> comments){
            this.comments.clear();
            if (comments != null) {for (String item : comments){this.addToComments(item);}} return (A) this;
    }

    public A withComments(String... comments){
            this.comments.clear(); if (comments != null) {for (String item :comments){ this.addToComments(item);}} return (A) this;
    }

    public A addToAnnotations(AnnotationRef... items){
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.add(builder);this.annotations.add(builder);} return (A)this;
    }

    public A removeFromAnnotations(AnnotationRef... items){
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.remove(builder);this.annotations.remove(builder);} return (A)this;
    }

    @Deprecated public List<AnnotationRef> getAnnotations(){
            return build(annotations);
    }

    public List<AnnotationRef> buildAnnotations(){
            return build(annotations);
    }

    public A withAnnotations(List<AnnotationRef> annotations){
            _visitables.removeAll(this.annotations);
            this.annotations.clear();
            if (annotations != null) {for (AnnotationRef item : annotations){this.addToAnnotations(item);}} return (A) this;
    }

    public A withAnnotations(AnnotationRef... annotations){
            this.annotations.clear(); if (annotations != null) {for (AnnotationRef item :annotations){ this.addToAnnotations(item);}} return (A) this;
    }

    public MethodFluent.AnnotationsNested<A> addNewAnnotation(){
            return new AnnotationsNestedImpl();
    }

    public MethodFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item){
            return new AnnotationsNestedImpl(item);
    }

    public A addToParameters(TypeParamDef... items){
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.add(builder);this.parameters.add(builder);} return (A)this;
    }

    public A removeFromParameters(TypeParamDef... items){
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.remove(builder);this.parameters.remove(builder);} return (A)this;
    }

    @Deprecated public List<TypeParamDef> getParameters(){
            return build(parameters);
    }

    public List<TypeParamDef> buildParameters(){
            return build(parameters);
    }

    public A withParameters(List<TypeParamDef> parameters){
            _visitables.removeAll(this.parameters);
            this.parameters.clear();
            if (parameters != null) {for (TypeParamDef item : parameters){this.addToParameters(item);}} return (A) this;
    }

    public A withParameters(TypeParamDef... parameters){
            this.parameters.clear(); if (parameters != null) {for (TypeParamDef item :parameters){ this.addToParameters(item);}} return (A) this;
    }

    public MethodFluent.ParametersNested<A> addNewParameter(){
            return new ParametersNestedImpl();
    }

    public MethodFluent.ParametersNested<A> addNewParameterLike(TypeParamDef item){
            return new ParametersNestedImpl(item);
    }

    public String getName(){
            return this.name;
    }

    public A withName(String name){
            this.name=name; return (A) this;
    }

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

    public A withVoidRefReturnType(VoidRef voidRefReturnType){
            _visitables.remove(this.returnType);
            if (voidRefReturnType!=null){ this.returnType= new VoidRefBuilder(voidRefReturnType); _visitables.add(this.returnType);} return (A) this;
    }

    public MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnType(){
            return new VoidRefReturnTypeNestedImpl();
    }

    public MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnTypeLike(VoidRef item){
            return new VoidRefReturnTypeNestedImpl(item);
    }

    public A withWildcardRefReturnType(WildcardRef wildcardRefReturnType){
            _visitables.remove(this.returnType);
            if (wildcardRefReturnType!=null){ this.returnType= new WildcardRefBuilder(wildcardRefReturnType); _visitables.add(this.returnType);} return (A) this;
    }

    public MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnType(){
            return new WildcardRefReturnTypeNestedImpl();
    }

    public MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnTypeLike(WildcardRef item){
            return new WildcardRefReturnTypeNestedImpl(item);
    }

    public A withPrimitiveRefReturnType(PrimitiveRef primitiveRefReturnType){
            _visitables.remove(this.returnType);
            if (primitiveRefReturnType!=null){ this.returnType= new PrimitiveRefBuilder(primitiveRefReturnType); _visitables.add(this.returnType);} return (A) this;
    }

    public MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnType(){
            return new PrimitiveRefReturnTypeNestedImpl();
    }

    public MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnTypeLike(PrimitiveRef item){
            return new PrimitiveRefReturnTypeNestedImpl(item);
    }

    public A withTypeParamRefReturnType(TypeParamRef typeParamRefReturnType){
            _visitables.remove(this.returnType);
            if (typeParamRefReturnType!=null){ this.returnType= new TypeParamRefBuilder(typeParamRefReturnType); _visitables.add(this.returnType);} return (A) this;
    }

    public MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnType(){
            return new TypeParamRefReturnTypeNestedImpl();
    }

    public MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnTypeLike(TypeParamRef item){
            return new TypeParamRefReturnTypeNestedImpl(item);
    }

    public A withClassRefReturnType(ClassRef classRefReturnType){
            _visitables.remove(this.returnType);
            if (classRefReturnType!=null){ this.returnType= new ClassRefBuilder(classRefReturnType); _visitables.add(this.returnType);} return (A) this;
    }

    public MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnType(){
            return new ClassRefReturnTypeNestedImpl();
    }

    public MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnTypeLike(ClassRef item){
            return new ClassRefReturnTypeNestedImpl(item);
    }

    public A addToArguments(Property... items){
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }

    public A removeFromArguments(Property... items){
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }

    @Deprecated public List<Property> getArguments(){
            return build(arguments);
    }

    public List<Property> buildArguments(){
            return build(arguments);
    }

    public A withArguments(List<Property> arguments){
            _visitables.removeAll(this.arguments);
            this.arguments.clear();
            if (arguments != null) {for (Property item : arguments){this.addToArguments(item);}} return (A) this;
    }

    public A withArguments(Property... arguments){
            this.arguments.clear(); if (arguments != null) {for (Property item :arguments){ this.addToArguments(item);}} return (A) this;
    }

    public MethodFluent.ArgumentsNested<A> addNewArgument(){
            return new ArgumentsNestedImpl();
    }

    public MethodFluent.ArgumentsNested<A> addNewArgumentLike(Property item){
            return new ArgumentsNestedImpl(item);
    }

    public boolean isVarArgPreferred(){
            return this.varArgPreferred;
    }

    public A withVarArgPreferred(boolean varArgPreferred){
            this.varArgPreferred=varArgPreferred; return (A) this;
    }

    public A addToExceptions(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.exceptions.add(builder);} return (A)this;
    }

    public A removeFromExceptions(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.exceptions.remove(builder);} return (A)this;
    }

    @Deprecated public List<ClassRef> getExceptions(){
            return build(exceptions);
    }

    public List<ClassRef> buildExceptions(){
            return build(exceptions);
    }

    public A withExceptions(List<ClassRef> exceptions){
            _visitables.removeAll(this.exceptions);
            this.exceptions.clear();
            if (exceptions != null) {for (ClassRef item : exceptions){this.addToExceptions(item);}} return (A) this;
    }

    public A withExceptions(ClassRef... exceptions){
            this.exceptions.clear(); if (exceptions != null) {for (ClassRef item :exceptions){ this.addToExceptions(item);}} return (A) this;
    }

    public MethodFluent.ExceptionsNested<A> addNewException(){
            return new ExceptionsNestedImpl();
    }

    public MethodFluent.ExceptionsNested<A> addNewExceptionLike(ClassRef item){
            return new ExceptionsNestedImpl(item);
    }

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

    public MethodFluent.BlockNested<A> withNewBlock(){
            return new BlockNestedImpl();
    }

    public MethodFluent.BlockNested<A> withNewBlockLike(Block item){
            return new BlockNestedImpl(item);
    }

    public MethodFluent.BlockNested<A> editBlock(){
            return withNewBlockLike(getBlock());
    }

    public MethodFluent.BlockNested<A> editOrNewBlock(){
            return withNewBlockLike(getBlock() != null ? getBlock(): new BlockBuilder().build());
    }

    public MethodFluent.BlockNested<A> editOrNewBlockLike(Block item){
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


    public class AnnotationsNestedImpl<N> extends AnnotationRefFluentImpl<MethodFluent.AnnotationsNested<N>> implements MethodFluent.AnnotationsNested<N>,Nested<N>{

            private final AnnotationRefBuilder builder;
    
            AnnotationsNestedImpl(AnnotationRef item){
                    this.builder = new AnnotationRefBuilder(this, item);
            }
            AnnotationsNestedImpl(){
                    this.builder = new AnnotationRefBuilder(this);
            }
    
    public N and(){
            return (N) MethodFluentImpl.this.addToAnnotations(builder.build());
    }
    public N endAnnotation(){
            return and();
    }

}
    public class ParametersNestedImpl<N> extends TypeParamDefFluentImpl<MethodFluent.ParametersNested<N>> implements MethodFluent.ParametersNested<N>,Nested<N>{

            private final TypeParamDefBuilder builder;
    
            ParametersNestedImpl(TypeParamDef item){
                    this.builder = new TypeParamDefBuilder(this, item);
            }
            ParametersNestedImpl(){
                    this.builder = new TypeParamDefBuilder(this);
            }
    
    public N and(){
            return (N) MethodFluentImpl.this.addToParameters(builder.build());
    }
    public N endParameter(){
            return and();
    }

}
    public class VoidRefReturnTypeNestedImpl<N> extends VoidRefFluentImpl<MethodFluent.VoidRefReturnTypeNested<N>> implements MethodFluent.VoidRefReturnTypeNested<N>,Nested<N>{

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
    public class WildcardRefReturnTypeNestedImpl<N> extends WildcardRefFluentImpl<MethodFluent.WildcardRefReturnTypeNested<N>> implements MethodFluent.WildcardRefReturnTypeNested<N>,Nested<N>{

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
    public class PrimitiveRefReturnTypeNestedImpl<N> extends PrimitiveRefFluentImpl<MethodFluent.PrimitiveRefReturnTypeNested<N>> implements MethodFluent.PrimitiveRefReturnTypeNested<N>,Nested<N>{

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
    public class TypeParamRefReturnTypeNestedImpl<N> extends TypeParamRefFluentImpl<MethodFluent.TypeParamRefReturnTypeNested<N>> implements MethodFluent.TypeParamRefReturnTypeNested<N>,Nested<N>{

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
    public class ClassRefReturnTypeNestedImpl<N> extends ClassRefFluentImpl<MethodFluent.ClassRefReturnTypeNested<N>> implements MethodFluent.ClassRefReturnTypeNested<N>,Nested<N>{

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
    public class ArgumentsNestedImpl<N> extends PropertyFluentImpl<MethodFluent.ArgumentsNested<N>> implements MethodFluent.ArgumentsNested<N>,Nested<N>{

            private final PropertyBuilder builder;
    
            ArgumentsNestedImpl(Property item){
                    this.builder = new PropertyBuilder(this, item);
            }
            ArgumentsNestedImpl(){
                    this.builder = new PropertyBuilder(this);
            }
    
    public N and(){
            return (N) MethodFluentImpl.this.addToArguments(builder.build());
    }
    public N endArgument(){
            return and();
    }

}
    public class ExceptionsNestedImpl<N> extends ClassRefFluentImpl<MethodFluent.ExceptionsNested<N>> implements MethodFluent.ExceptionsNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
    
            ExceptionsNestedImpl(ClassRef item){
                    this.builder = new ClassRefBuilder(this, item);
            }
            ExceptionsNestedImpl(){
                    this.builder = new ClassRefBuilder(this);
            }
    
    public N and(){
            return (N) MethodFluentImpl.this.addToExceptions(builder.build());
    }
    public N endException(){
            return and();
    }

}
    public class BlockNestedImpl<N> extends BlockFluentImpl<MethodFluent.BlockNested<N>> implements MethodFluent.BlockNested<N>,Nested<N>{

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
