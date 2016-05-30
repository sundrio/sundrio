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

import io.sundr.builder.VisitableBuilder;

public class TypeDefBuilder extends TypeDefFluentImpl<TypeDefBuilder> implements VisitableBuilder<TypeDef,TypeDefBuilder>{

     TypeDefFluent<?> fluent;

    public TypeDefBuilder(){
        this.fluent = this;
    }
    public TypeDefBuilder( TypeDefFluent<?> fluent ){
        this.fluent = fluent;
    }
    public TypeDefBuilder( TypeDefFluent<?> fluent , TypeDef instance ){
        this.fluent = fluent; fluent.withKind(instance.getKind()); fluent.withPackageName(instance.getPackageName()); fluent.withName(instance.getName()); fluent.withAnnotations(instance.getAnnotations()); fluent.withExtendsList(instance.getExtendsList()); fluent.withImplementsList(instance.getImplementsList()); fluent.withParameters(instance.getParameters()); fluent.withProperties(instance.getProperties()); fluent.withConstructors(instance.getConstructors()); fluent.withMethods(instance.getMethods()); fluent.withOuterType(instance.getOuterType()); fluent.withInnerTypes(instance.getInnerTypes()); fluent.withModifiers(instance.getModifiers()); fluent.withAttributes(instance.getAttributes()); 
    }
    public TypeDefBuilder( TypeDef instance ){
        this.fluent = this; this.withKind(instance.getKind()); this.withPackageName(instance.getPackageName()); this.withName(instance.getName()); this.withAnnotations(instance.getAnnotations()); this.withExtendsList(instance.getExtendsList()); this.withImplementsList(instance.getImplementsList()); this.withParameters(instance.getParameters()); this.withProperties(instance.getProperties()); this.withConstructors(instance.getConstructors()); this.withMethods(instance.getMethods()); this.withOuterType(instance.getOuterType()); this.withInnerTypes(instance.getInnerTypes()); this.withModifiers(instance.getModifiers()); this.withAttributes(instance.getAttributes()); 
    }

public EditableTypeDef build(){
    EditableTypeDef buildable = new EditableTypeDef(fluent.getKind(),fluent.getPackageName(),fluent.getName(),fluent.getAnnotations(),fluent.getExtendsList(),fluent.getImplementsList(),fluent.getParameters(),fluent.getProperties(),fluent.getConstructors(),fluent.getMethods(),fluent.getOuterType(),fluent.getInnerTypes(),fluent.getModifiers(),fluent.getAttributes());
validate(buildable);
return buildable;

}
public boolean equals( Object o ){
    
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
if (!super.equals(o)) return false;
TypeDefBuilder that = (TypeDefBuilder) o;
if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;
return true;

}

private <T> void validate(T item) {}


}
    
