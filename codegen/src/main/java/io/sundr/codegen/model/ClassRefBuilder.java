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

import io.sundr.builder.VisitableBuilder;
import java.lang.Boolean;
import java.lang.Object;

public class ClassRefBuilder extends ClassRefFluentImpl<ClassRefBuilder> implements VisitableBuilder<ClassRef,ClassRefBuilder>{

    ClassRefFluent<?> fluent;
    Boolean validationEnabled;

    public ClassRefBuilder(){
            this(true);
    }
    public ClassRefBuilder(Boolean validationEnabled){
            this.fluent = this; this.validationEnabled=validationEnabled;
    }
    public ClassRefBuilder(ClassRefFluent<?> fluent){
            this(fluent, true);
    }
    public ClassRefBuilder(ClassRefFluent<?> fluent,Boolean validationEnabled){
            this.fluent = fluent; this.validationEnabled=validationEnabled;
    }
    public ClassRefBuilder(ClassRefFluent<?> fluent,ClassRef instance){
            this(fluent, instance, true);
    }
    public ClassRefBuilder(ClassRefFluent<?> fluent,ClassRef instance,Boolean validationEnabled){
            this.fluent = fluent; 
            fluent.withDefinition(instance.getDefinition()); 
            fluent.withFullyQualifiedName(instance.getFullyQualifiedName()); 
            fluent.withDimensions(instance.getDimensions()); 
            fluent.withArguments(instance.getArguments()); 
            fluent.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }
    public ClassRefBuilder(ClassRef instance){
            this(instance,true);
    }
    public ClassRefBuilder(ClassRef instance,Boolean validationEnabled){
            this.fluent = this; 
            this.withDefinition(instance.getDefinition()); 
            this.withFullyQualifiedName(instance.getFullyQualifiedName()); 
            this.withDimensions(instance.getDimensions()); 
            this.withArguments(instance.getArguments()); 
            this.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }

    public EditableClassRef build(){
            EditableClassRef buildable = new EditableClassRef(fluent.getDefinition(),fluent.getFullyQualifiedName(),fluent.getDimensions(),fluent.getArguments(),fluent.getAttributes());
            validate(buildable);
            return buildable;
    }

    private <T>void validate(T item){
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            ClassRefBuilder that = (ClassRefBuilder) o;
            if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;

            if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) :that.validationEnabled != null) return false;
            return true;
    }




}
