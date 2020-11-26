package io.sundr.codegen.model;

import io.sundr.builder.VisitableBuilder;
import java.util.ArrayList;
import java.lang.String;
import java.util.function.Predicate;
import java.util.List;
import java.lang.Boolean;
import java.util.Collection;
import java.lang.Object;
import java.lang.StringBuilder;
import io.sundr.builder.Nested;
import java.lang.Deprecated;
import java.util.Iterator;
import java.lang.StringBuffer;

public class PropertyFluentImpl<A extends PropertyFluent<A>> extends ModifierSupportFluentImpl<A> implements PropertyFluent<A>{

    private List<AnnotationRefBuilder> annotations =  new ArrayList<AnnotationRefBuilder>();
    private VisitableBuilder<? extends TypeRef,?> typeRef;
    private String name;
    private List<String> comments = new ArrayList<String>();

    public PropertyFluentImpl(){
    }
    public PropertyFluentImpl(Property instance){
            this.withAnnotations(instance.getAnnotations()); 
            this.withTypeRef(instance.getTypeRef()); 
            this.withName(instance.getName()); 
            this.withComments(instance.getComments()); 
            this.withModifiers(instance.getModifiers()); 
            this.withAttributes(instance.getAttributes()); 
    }

    public A addToAnnotations(int index,AnnotationRef item){
            if (this.annotations == null) {this.annotations = new ArrayList<AnnotationRefBuilder>();}
            AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.get("annotations").add(index >= 0 ? index : _visitables.get("annotations").size(), builder);this.annotations.add(index >= 0 ? index : annotations.size(), builder); return (A)this;
    }

    public A setToAnnotations(int index,AnnotationRef item){
            if (this.annotations == null) {this.annotations = new ArrayList<AnnotationRefBuilder>();}
            AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
            if (index < 0 || index >= _visitables.get("annotations").size()) { _visitables.get("annotations").add(builder); } else { _visitables.get("annotations").set(index, builder);}
            if (index < 0 || index >= annotations.size()) { annotations.add(builder); } else { annotations.set(index, builder);}
             return (A)this;
    }

    public A addToAnnotations(AnnotationRef... items){
            if (this.annotations == null) {this.annotations = new ArrayList<AnnotationRefBuilder>();}
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.get("annotations").add(builder);this.annotations.add(builder);} return (A)this;
    }

    public A addAllToAnnotations(Collection<AnnotationRef> items){
            if (this.annotations == null) {this.annotations = new ArrayList<AnnotationRefBuilder>();}
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.get("annotations").add(builder);this.annotations.add(builder);} return (A)this;
    }

    public A removeFromAnnotations(AnnotationRef... items){
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.get("annotations").remove(builder);if (this.annotations != null) {this.annotations.remove(builder);}} return (A)this;
    }

    public A removeAllFromAnnotations(Collection<AnnotationRef> items){
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.get("annotations").remove(builder);if (this.annotations != null) {this.annotations.remove(builder);}} return (A)this;
    }

    public A removeMatchingFromAnnotations(Predicate<AnnotationRefBuilder> predicate){
            if (annotations == null) return (A) this;
            final Iterator<AnnotationRefBuilder> each = annotations.iterator();
            final List visitables = _visitables.get("annotations");
            while (each.hasNext()) {
              AnnotationRefBuilder builder = each.next();
              if (predicate.test(builder)) {
                visitables.remove(builder);
                each.remove();
              }
            }
            return (A)this;
    }

    
/**
 * This method has been deprecated, please use method buildAnnotations instead.
 * @return The buildable object.
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

    public AnnotationRef buildMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate){
            for (AnnotationRefBuilder item: annotations) { if(predicate.test(item)){ return item.build();} } return null;
    }

    public Boolean hasMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate){
            for (AnnotationRefBuilder item: annotations) { if(predicate.test(item)){ return true;} } return false;
    }

    public A withAnnotations(List<AnnotationRef> annotations){
            if (this.annotations != null) { _visitables.get("annotations").removeAll(this.annotations);}
            if (annotations != null) {this.annotations = new ArrayList<AnnotationRefBuilder>(); for (AnnotationRef item : annotations){this.addToAnnotations(item);}} else { this.annotations = null;} return (A) this;
    }

    public A withAnnotations(AnnotationRef... annotations){
            if (this.annotations != null) {this.annotations.clear();}
            if (annotations != null) {for (AnnotationRef item :annotations){ this.addToAnnotations(item);}} return (A) this;
    }

    public Boolean hasAnnotations(){
            return annotations != null && !annotations.isEmpty();
    }

    public PropertyFluent.AnnotationsNested<A> addNewAnnotation(){
            return new AnnotationsNestedImpl();
    }

    public PropertyFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item){
            return new AnnotationsNestedImpl(-1, item);
    }

    public PropertyFluent.AnnotationsNested<A> setNewAnnotationLike(int index,AnnotationRef item){
            return new AnnotationsNestedImpl(index, item);
    }

    public PropertyFluent.AnnotationsNested<A> editAnnotation(int index){
            if (annotations.size() <= index) throw new RuntimeException("Can't edit annotations. Index exceeds size.");
            return setNewAnnotationLike(index, buildAnnotation(index));
    }

    public PropertyFluent.AnnotationsNested<A> editFirstAnnotation(){
            if (annotations.size() == 0) throw new RuntimeException("Can't edit first annotations. The list is empty.");
            return setNewAnnotationLike(0, buildAnnotation(0));
    }

    public PropertyFluent.AnnotationsNested<A> editLastAnnotation(){
            int index = annotations.size() - 1;
            if (index < 0) throw new RuntimeException("Can't edit last annotations. The list is empty.");
            return setNewAnnotationLike(index, buildAnnotation(index));
    }

    public PropertyFluent.AnnotationsNested<A> editMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate){
            int index = -1;
            for (int i=0;i<annotations.size();i++) { 
            if (predicate.test(annotations.get(i))) {index = i; break;}
            } 
            if (index < 0) throw new RuntimeException("Can't edit matching annotations. No match found.");
            return setNewAnnotationLike(index, buildAnnotation(index));
    }

    
/**
 * This method has been deprecated, please use method buildTypeRef instead.
 * @return The buildable object.
 */
@Deprecated public TypeRef getTypeRef(){
            return this.typeRef!=null?this.typeRef.build():null;
    }

    public TypeRef buildTypeRef(){
            return this.typeRef!=null?this.typeRef.build():null;
    }

    public A withTypeRef(TypeRef typeRef){
            _visitables.get("typeRef").remove(this.typeRef);
            if (typeRef instanceof PrimitiveRef){ this.typeRef= new PrimitiveRefBuilder((PrimitiveRef)typeRef); _visitables.get("typeRef").add(this.typeRef);}
            if (typeRef instanceof VoidRef){ this.typeRef= new VoidRefBuilder((VoidRef)typeRef); _visitables.get("typeRef").add(this.typeRef);}
            if (typeRef instanceof WildcardRef){ this.typeRef= new WildcardRefBuilder((WildcardRef)typeRef); _visitables.get("typeRef").add(this.typeRef);}
            if (typeRef instanceof ClassRef){ this.typeRef= new ClassRefBuilder((ClassRef)typeRef); _visitables.get("typeRef").add(this.typeRef);}
            if (typeRef instanceof TypeParamRef){ this.typeRef= new TypeParamRefBuilder((TypeParamRef)typeRef); _visitables.get("typeRef").add(this.typeRef);}
            return (A) this;
    }

    public Boolean hasTypeRef(){
            return this.typeRef != null;
    }

    public A withPrimitiveRefType(PrimitiveRef primitiveRefType){
            _visitables.get("typeRef").remove(this.typeRef);
            if (primitiveRefType!=null){ this.typeRef= new PrimitiveRefBuilder(primitiveRefType); _visitables.get("typeRef").add(this.typeRef);} return (A) this;
    }

    public PropertyFluent.PrimitiveRefTypeNested<A> withNewPrimitiveRefType(){
            return new PrimitiveRefTypeNestedImpl();
    }

    public PropertyFluent.PrimitiveRefTypeNested<A> withNewPrimitiveRefTypeLike(PrimitiveRef item){
            return new PrimitiveRefTypeNestedImpl(item);
    }

    public A withVoidRefType(VoidRef voidRefType){
            _visitables.get("typeRef").remove(this.typeRef);
            if (voidRefType!=null){ this.typeRef= new VoidRefBuilder(voidRefType); _visitables.get("typeRef").add(this.typeRef);} return (A) this;
    }

    public PropertyFluent.VoidRefTypeNested<A> withNewVoidRefType(){
            return new VoidRefTypeNestedImpl();
    }

    public PropertyFluent.VoidRefTypeNested<A> withNewVoidRefTypeLike(VoidRef item){
            return new VoidRefTypeNestedImpl(item);
    }

    public A withWildcardRefType(WildcardRef wildcardRefType){
            _visitables.get("typeRef").remove(this.typeRef);
            if (wildcardRefType!=null){ this.typeRef= new WildcardRefBuilder(wildcardRefType); _visitables.get("typeRef").add(this.typeRef);} return (A) this;
    }

    public PropertyFluent.WildcardRefTypeNested<A> withNewWildcardRefType(){
            return new WildcardRefTypeNestedImpl();
    }

    public PropertyFluent.WildcardRefTypeNested<A> withNewWildcardRefTypeLike(WildcardRef item){
            return new WildcardRefTypeNestedImpl(item);
    }

    public A withClassRefType(ClassRef classRefType){
            _visitables.get("typeRef").remove(this.typeRef);
            if (classRefType!=null){ this.typeRef= new ClassRefBuilder(classRefType); _visitables.get("typeRef").add(this.typeRef);} return (A) this;
    }

    public PropertyFluent.ClassRefTypeNested<A> withNewClassRefType(){
            return new ClassRefTypeNestedImpl();
    }

    public PropertyFluent.ClassRefTypeNested<A> withNewClassRefTypeLike(ClassRef item){
            return new ClassRefTypeNestedImpl(item);
    }

    public A withTypeParamRefType(TypeParamRef typeParamRefType){
            _visitables.get("typeRef").remove(this.typeRef);
            if (typeParamRefType!=null){ this.typeRef= new TypeParamRefBuilder(typeParamRefType); _visitables.get("typeRef").add(this.typeRef);} return (A) this;
    }

    public PropertyFluent.TypeParamRefTypeNested<A> withNewTypeParamRefType(){
            return new TypeParamRefTypeNestedImpl();
    }

    public PropertyFluent.TypeParamRefTypeNested<A> withNewTypeParamRefTypeLike(TypeParamRef item){
            return new TypeParamRefTypeNestedImpl(item);
    }

    public String getName(){
            return this.name;
    }

    public A withName(String name){
            this.name=name; return (A) this;
    }

    public Boolean hasName(){
            return this.name != null;
    }

    public A withNewName(String arg1){
            return (A)withName(new String(arg1));
    }

    public A withNewName(StringBuilder arg1){
            return (A)withName(new String(arg1));
    }

    public A withNewName(StringBuffer arg1){
            return (A)withName(new String(arg1));
    }

    public A addToComments(int index,String item){
            if (this.comments == null) {this.comments = new ArrayList<String>();}
            this.comments.add(index, item);
            return (A)this;
    }

    public A setToComments(int index,String item){
            if (this.comments == null) {this.comments = new ArrayList<String>();}
            this.comments.set(index, item); return (A)this;
    }

    public A addToComments(String... items){
            if (this.comments == null) {this.comments = new ArrayList<String>();}
            for (String item : items) {this.comments.add(item);} return (A)this;
    }

    public A addAllToComments(Collection<String> items){
            if (this.comments == null) {this.comments = new ArrayList<String>();}
            for (String item : items) {this.comments.add(item);} return (A)this;
    }

    public A removeFromComments(String... items){
            for (String item : items) {if (this.comments!= null){ this.comments.remove(item);}} return (A)this;
    }

    public A removeAllFromComments(Collection<String> items){
            for (String item : items) {if (this.comments!= null){ this.comments.remove(item);}} return (A)this;
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
            for (String item: comments) { if(predicate.test(item)){ return item;} } return null;
    }

    public Boolean hasMatchingComment(Predicate<String> predicate){
            for (String item: comments) { if(predicate.test(item)){ return true;} } return false;
    }

    public A withComments(List<String> comments){
            if (this.comments != null) { _visitables.get("comments").removeAll(this.comments);}
            if (comments != null) {this.comments = new ArrayList<String>(); for (String item : comments){this.addToComments(item);}} else { this.comments = null;} return (A) this;
    }

    public A withComments(String... comments){
            if (this.comments != null) {this.comments.clear();}
            if (comments != null) {for (String item :comments){ this.addToComments(item);}} return (A) this;
    }

    public Boolean hasComments(){
            return comments != null && !comments.isEmpty();
    }

    public A addNewComment(String arg1){
            return (A)addToComments(new String(arg1));
    }

    public A addNewComment(StringBuilder arg1){
            return (A)addToComments(new String(arg1));
    }

    public A addNewComment(StringBuffer arg1){
            return (A)addToComments(new String(arg1));
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            PropertyFluentImpl that = (PropertyFluentImpl) o;
            if (annotations != null ? !annotations.equals(that.annotations) :that.annotations != null) return false;
            if (typeRef != null ? !typeRef.equals(that.typeRef) :that.typeRef != null) return false;
            if (name != null ? !name.equals(that.name) :that.name != null) return false;
            if (comments != null ? !comments.equals(that.comments) :that.comments != null) return false;
            return true;
    }


    public class AnnotationsNestedImpl<N> extends AnnotationRefFluentImpl<PropertyFluent.AnnotationsNested<N>> implements PropertyFluent.AnnotationsNested<N>,Nested<N>{

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
            return (N) PropertyFluentImpl.this.setToAnnotations(index,builder.build());
    }
    public N endAnnotation(){
            return and();
    }

}
    public class PrimitiveRefTypeNestedImpl<N> extends PrimitiveRefFluentImpl<PropertyFluent.PrimitiveRefTypeNested<N>> implements PropertyFluent.PrimitiveRefTypeNested<N>,Nested<N>{

            private final PrimitiveRefBuilder builder;
    
            PrimitiveRefTypeNestedImpl(PrimitiveRef item){
                    this.builder = new PrimitiveRefBuilder(this, item);
            }
            PrimitiveRefTypeNestedImpl(){
                    this.builder = new PrimitiveRefBuilder(this);
            }
    
    public N and(){
            return (N) PropertyFluentImpl.this.withTypeRef(builder.build());
    }
    public N endPrimitiveRefType(){
            return and();
    }

}
    public class VoidRefTypeNestedImpl<N> extends VoidRefFluentImpl<PropertyFluent.VoidRefTypeNested<N>> implements PropertyFluent.VoidRefTypeNested<N>,Nested<N>{

            private final VoidRefBuilder builder;
    
            VoidRefTypeNestedImpl(VoidRef item){
                    this.builder = new VoidRefBuilder(this, item);
            }
            VoidRefTypeNestedImpl(){
                    this.builder = new VoidRefBuilder(this);
            }
    
    public N and(){
            return (N) PropertyFluentImpl.this.withTypeRef(builder.build());
    }
    public N endVoidRefType(){
            return and();
    }

}
    public class WildcardRefTypeNestedImpl<N> extends WildcardRefFluentImpl<PropertyFluent.WildcardRefTypeNested<N>> implements PropertyFluent.WildcardRefTypeNested<N>,Nested<N>{

            private final WildcardRefBuilder builder;
    
            WildcardRefTypeNestedImpl(WildcardRef item){
                    this.builder = new WildcardRefBuilder(this, item);
            }
            WildcardRefTypeNestedImpl(){
                    this.builder = new WildcardRefBuilder(this);
            }
    
    public N and(){
            return (N) PropertyFluentImpl.this.withTypeRef(builder.build());
    }
    public N endWildcardRefType(){
            return and();
    }

}
    public class ClassRefTypeNestedImpl<N> extends ClassRefFluentImpl<PropertyFluent.ClassRefTypeNested<N>> implements PropertyFluent.ClassRefTypeNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
    
            ClassRefTypeNestedImpl(ClassRef item){
                    this.builder = new ClassRefBuilder(this, item);
            }
            ClassRefTypeNestedImpl(){
                    this.builder = new ClassRefBuilder(this);
            }
    
    public N and(){
            return (N) PropertyFluentImpl.this.withTypeRef(builder.build());
    }
    public N endClassRefType(){
            return and();
    }

}
    public class TypeParamRefTypeNestedImpl<N> extends TypeParamRefFluentImpl<PropertyFluent.TypeParamRefTypeNested<N>> implements PropertyFluent.TypeParamRefTypeNested<N>,Nested<N>{

            private final TypeParamRefBuilder builder;
    
            TypeParamRefTypeNestedImpl(TypeParamRef item){
                    this.builder = new TypeParamRefBuilder(this, item);
            }
            TypeParamRefTypeNestedImpl(){
                    this.builder = new TypeParamRefBuilder(this);
            }
    
    public N and(){
            return (N) PropertyFluentImpl.this.withTypeRef(builder.build());
    }
    public N endTypeParamRefType(){
            return and();
    }

}


}
