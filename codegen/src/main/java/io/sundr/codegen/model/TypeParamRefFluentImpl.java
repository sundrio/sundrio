/*
 * Copyright 2016 The original authors.
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

public class TypeParamRefFluentImpl<A extends TypeParamRefFluent<A>> extends AttributeSupportFluentImpl<A> implements TypeParamRefFluent<A>{

     String name;
public TypeParamRefFluentImpl(){
    
}
public TypeParamRefFluentImpl( TypeParamRef instance ){
    this.withName(instance.getName()); this.withAttributes(instance.getAttributes()); 
}

    public String getName(){
    return this.name;
    }
    public A withName( String name){
    this.name=name; return (A) this;
    }
    public boolean equals( Object o){
    
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
if (!super.equals(o)) return false;
TypeParamRefFluentImpl that = (TypeParamRefFluentImpl) o;
if (name != null ? !name.equals(that.name) :that.name != null) return false;
return true;

    }



}
