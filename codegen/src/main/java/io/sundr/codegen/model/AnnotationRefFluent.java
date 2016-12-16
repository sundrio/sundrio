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

import java.util.Map;

import io.sundr.builder.Nested;

public interface AnnotationRefFluent<A extends AnnotationRefFluent<A>> extends AttributeSupportFluent<A>{


    
/**
 * This method has been deprecated, please use method buildClassRef instead.
 */
@Deprecated public ClassRef getClassRef();
    public ClassRef buildClassRef();
    public A withClassRef(ClassRef classRef);
    public Boolean hasClassRef();
    public ClassRefNested<A> withNewClassRef();
    public ClassRefNested<A> withNewClassRefLike(ClassRef item);
    public ClassRefNested<A> editClassRef();
    public ClassRefNested<A> editOrNewClassRef();
    public ClassRefNested<A> editOrNewClassRefLike(ClassRef item);
    public A addToParameters(String key, Object value);
    public A addToParameters(Map<String, Object> map);
    public A removeFromParameters(String key);
    public A removeFromParameters(Map<String, Object> map);
    public Map<String,Object> getParameters();
    public A withParameters(Map<String, Object> parameters);
    public Boolean hasParameters();

    public interface ClassRefNested<N> extends Nested<N>,ClassRefFluent<ClassRefNested<N>>{

        
    public N and();    public N endClassRef();
}


}
