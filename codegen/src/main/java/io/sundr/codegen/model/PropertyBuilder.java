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

public class PropertyBuilder extends PropertyFluentImpl<PropertyBuilder> implements VisitableBuilder<Property,PropertyBuilder>{

    PropertyFluent<?> fluent;
    Boolean validationEnabled;

    public PropertyBuilder(){
            this(true);
    }
    public PropertyBuilder(Boolean validationEnabled){
            this.fluent = this; this.validationEnabled=validationEnabled;
    }
    public PropertyBuilder(PropertyFluent<?> fluent){
            this(fluent, true);
    }
    public PropertyBuilder(PropertyFluent<?> fluent,Boolean validationEnabled){
            this.fluent = fluent; this.validationEnabled=validationEnabled;
    }
    public PropertyBuilder(PropertyFluent<?> fluent,Property instance){
            this(fluent, instance, true);
    }
    public PropertyBuilder(PropertyFluent<?> fluent,Property instance,Boolean validationEnabled){
            this.fluent = fluent; 
            fluent.withAnnotations(instance.getAnnotations()); 
            fluent.withTypeRef(instance.getTypeRef()); 
            fluent.withName(instance.getName()); 
            fluent.withModifiers(instance.getModifiers()); 
            fluent.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }
    public PropertyBuilder(Property instance){
            this(instance,true);
    }
    public PropertyBuilder(Property instance,Boolean validationEnabled){
            this.fluent = this; 
            this.withAnnotations(instance.getAnnotations()); 
            this.withTypeRef(instance.getTypeRef()); 
            this.withName(instance.getName()); 
            this.withModifiers(instance.getModifiers()); 
            this.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }

    public EditableProperty build(){
            EditableProperty buildable = new EditableProperty(fluent.getAnnotations(),fluent.getTypeRef(),fluent.getName(),fluent.getModifiers(),fluent.getAttributes());
            validate(buildable);
            return buildable;
    }

    private <T>void validate(T item){
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            PropertyBuilder that = (PropertyBuilder) o;
            if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;

            if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) :that.validationEnabled != null) return false;
            return true;
    }




}
