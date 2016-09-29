package io.sundr.codegen.model;

import java.util.Map;
import io.sundr.builder.VisitableBuilder;
import java.util.LinkedHashMap;
import java.lang.String;
import io.sundr.builder.Nested;
import java.lang.Object;

public class AnnotationRefFluentImpl<A extends AnnotationRefFluent<A>> extends AttributeSupportFluentImpl<A> implements AnnotationRefFluent<A>{

    private VisitableBuilder<? extends ClassRef,?> classRef;
    private Map<String,Object> parameters = new LinkedHashMap<String,Object>();

    public AnnotationRefFluentImpl(){
    }
    public AnnotationRefFluentImpl(AnnotationRef instance){
            this.withClassRef(instance.getClassRef()); 
            this.withParameters(instance.getParameters()); 
            this.withAttributes(instance.getAttributes()); 
    }

    public ClassRef getClassRef(){
            return this.classRef!=null?this.classRef.build():null;
    }

    public A withClassRef(ClassRef classRef){
            if (classRef!=null){ this.classRef= new ClassRefBuilder(classRef); _visitables.add(this.classRef);} return (A) this;
    }

    public AnnotationRefFluent.ClassRefNested<A> withNewClassRef(){
            return new ClassRefNestedImpl();
    }

    public AnnotationRefFluent.ClassRefNested<A> withNewClassRefLike(ClassRef item){
            return new ClassRefNestedImpl(item);
    }

    public AnnotationRefFluent.ClassRefNested<A> editClassRef(){
            return withNewClassRefLike(getClassRef());
    }

    public AnnotationRefFluent.ClassRefNested<A> editOrNewClassRef(){
            return withNewClassRefLike(getClassRef() != null ? getClassRef(): new ClassRefBuilder().build());
    }

    public AnnotationRefFluent.ClassRefNested<A> editOrNewClassRefLike(ClassRef item){
            return withNewClassRefLike(getClassRef() != null ? getClassRef(): item);
    }

    public A addToParameters(String key,Object value){
            if(key != null && value != null) {this.parameters.put(key, value);} return (A)this;
    }

    public A addToParameters(Map<String,Object> map){
            if(map != null) { this.parameters.putAll(map);} return (A)this;
    }

    public A removeFromParameters(String key){
            if(key != null) {this.parameters.remove(key);} return (A)this;
    }

    public A removeFromParameters(Map<String,Object> map){
            if(map != null) { for(Object key : map.keySet()) {this.parameters.remove(key);}} return (A)this;
    }

    public Map<String,Object> getParameters(){
            return this.parameters;
    }

    public A withParameters(Map<String,Object> parameters){
            this.parameters.clear();
            if (parameters != null) {this.parameters.putAll(parameters);} return (A) this;
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            AnnotationRefFluentImpl that = (AnnotationRefFluentImpl) o;
            if (classRef != null ? !classRef.equals(that.classRef) :that.classRef != null) return false;
            if (parameters != null ? !parameters.equals(that.parameters) :that.parameters != null) return false;
            return true;
    }


    public class ClassRefNestedImpl<N> extends ClassRefFluentImpl<AnnotationRefFluent.ClassRefNested<N>> implements AnnotationRefFluent.ClassRefNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
    
            ClassRefNestedImpl(ClassRef item){
                    this.builder = new ClassRefBuilder(this, item);
            }
            ClassRefNestedImpl(){
                    this.builder = new ClassRefBuilder(this);
            }
    
    public N and(){
            return (N) AnnotationRefFluentImpl.this.withClassRef(builder.build());
    }
    public N endClassRef(){
            return and();
    }

}


}
