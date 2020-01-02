/*
 *      Copyright 2019 The original authors.
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

import io.sundr.builder.Nested;

import java.util.HashMap;
import java.util.Map;

public class AnnotationRefFluentImpl<A extends AnnotationRefFluent<A>> extends AttributeSupportFluentImpl<A> implements AnnotationRefFluent<A>{

    private ClassRefBuilder classRef;
    private final Map<String, Object> parameters = new HashMap<>();

    public AnnotationRefFluentImpl(){
    }
    public AnnotationRefFluentImpl(AnnotationRef instance){
            this.withClassRef(instance.getClassRef());
            this.withParameters(instance.getParameters());
            this.withAttributes(instance.getAttributes());
    }


/**
 * This method has been deprecated, please use method buildClassRef instead.
 * @return The buildable object.
 */
@Deprecated public ClassRef getClassRef(){
            return this.classRef!=null?this.classRef.build():null;
    }

    public ClassRef buildClassRef(){
            return this.classRef!=null?this.classRef.build():null;
    }

    public A withClassRef(ClassRef classRef){
            _visitables.get("classRef").remove(this.classRef);
            if (classRef!=null){ this.classRef= new ClassRefBuilder(classRef); _visitables.get("classRef").add(this.classRef);} return (A) this;
    }

    public Boolean hasClassRef(){
            return this.classRef != null;
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
            if(key != null && this.parameters != null) {this.parameters.remove(key);} return (A)this;
    }

    public A removeFromParameters(Map<String, Object> map){
        if (map != null) {
            map.keySet().forEach(parameters::remove);
        }
        return (A) this;
    }

    public Map<String,Object> getParameters(){
            return this.parameters;
    }

    public A withParameters(Map<String,Object> parameters){
        this.parameters.clear();
        this.parameters.putAll(parameters);
        return (A) this;
    }

    public Boolean hasParameters(){
            return !this.parameters.isEmpty();
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
