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

import java.util.LinkedHashMap;
import java.util.Map;

import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

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

    
/**
 * This method has been deprecated, please use method buildClassRef instead.
 */
@Deprecated public ClassRef getClassRef(){
            return this.classRef!=null?this.classRef.build():null;
    }

    public ClassRef buildClassRef(){
            return this.classRef!=null?this.classRef.build():null;
    }

    public A withClassRef(ClassRef classRef){
            _visitables.remove(this.classRef);
            if (classRef!=null){ this.classRef= new ClassRefBuilder(classRef); _visitables.add(this.classRef);} return (A) this;
    }

    public Boolean hasClassRef(){
            return this.classRef!=null;
    }

    public ClassRefNested<A> withNewClassRef(){
            return new ClassRefNestedImpl();
    }

    public ClassRefNested<A> withNewClassRefLike(ClassRef item){
            return new ClassRefNestedImpl(item);
    }

    public ClassRefNested<A> editClassRef(){
            return withNewClassRefLike(getClassRef());
    }

    public ClassRefNested<A> editOrNewClassRef(){
            return withNewClassRefLike(getClassRef() != null ? getClassRef(): new ClassRefBuilder().build());
    }

    public ClassRefNested<A> editOrNewClassRefLike(ClassRef item){
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

    public Boolean hasParameters(){
            return this.parameters!=null;
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


    public class ClassRefNestedImpl<N> extends ClassRefFluentImpl<ClassRefNested<N>> implements ClassRefNested<N>,Nested<N>{

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
