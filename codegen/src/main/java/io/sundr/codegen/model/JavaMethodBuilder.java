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

import io.sundr.builder.VisitableBuilder;

public class JavaMethodBuilder extends JavaMethodFluent<JavaMethodBuilder> implements VisitableBuilder<JavaMethod,JavaMethodBuilder>{

     JavaMethodFluent<?> fluent;

    public JavaMethodBuilder(){
        this.fluent = this;
    }
    public JavaMethodBuilder( JavaMethodFluent<?> fluent ){
        this.fluent = fluent;
    }
    public JavaMethodBuilder( JavaMethodFluent<?> fluent , JavaMethod instance ){
        this.fluent = fluent; fluent.withAnnotations(instance.getAnnotations()); fluent.withModifiers(instance.getModifiers()); fluent.withTypeParameters(instance.getTypeParameters()); fluent.withName(instance.getName()); fluent.withReturnType(instance.getReturnType()); fluent.withArguments(instance.getArguments()); fluent.withExceptions(instance.getExceptions()); fluent.withAttributes(instance.getAttributes()); 
    }
    public JavaMethodBuilder( JavaMethod instance ){
        this.fluent = this; this.withAnnotations(instance.getAnnotations()); this.withModifiers(instance.getModifiers()); this.withTypeParameters(instance.getTypeParameters()); this.withName(instance.getName()); this.withReturnType(instance.getReturnType()); this.withArguments(instance.getArguments()); this.withExceptions(instance.getExceptions()); this.withAttributes(instance.getAttributes()); 
    }

public EditableJavaMethod build(){
    EditableJavaMethod buildable = new EditableJavaMethod(fluent.getAnnotations(),fluent.getModifiers(),fluent.getTypeParameters(),fluent.getName(),fluent.getReturnType(),fluent.getArguments(),fluent.getExceptions(),fluent.getAttributes());
validate(buildable);
return buildable;

}

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fluent == null) ? 0 : fluent.hashCode());
    return result;
}

public boolean equals( Object o ){
    
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
if (!super.equals(o)) return false;
JavaMethodBuilder that = (JavaMethodBuilder) o;
if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;
return true;

}

private <T> void validate(T item) {}


}
    
