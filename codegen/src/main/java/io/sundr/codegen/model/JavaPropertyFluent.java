/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.codegen.model;

import io.sundr.builder.Fluent;
import io.sundr.builder.Nested;

public class JavaPropertyFluent<T extends JavaPropertyFluent<T>> extends AttributeSupportFluent<T> implements Fluent<T>{

    private JavaType type ;
    private String name ;
    private boolean array ;

    public JavaType getType(){
    return this.type;
    }
    public T withType(JavaType type){
    this.type=type; return (T) this;
    }
    public String getName(){
    return this.name;
    }
    public T withName(String name){
    this.name=name; return (T) this;
    }
    public boolean isArray(){
    return this.array;
    }
    public T withArray(boolean array){
    this.array=array; return (T) this;
    }
    public TypeNested<T> addType(){
    return new TypeNested<T>();
    }

    public class TypeNested<N> extends JavaTypeFluent<TypeNested<N>> implements Nested<N>{
    
            public N and(){
            return (N) withType(new JavaTypeBuilder(this).build());
        }
            public N endType(){
            return and();
        }
    
}


}
