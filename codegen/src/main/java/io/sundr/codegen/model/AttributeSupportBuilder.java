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

public class AttributeSupportBuilder extends AttributeSupportFluentImpl<AttributeSupportBuilder> implements VisitableBuilder<AttributeSupport,AttributeSupportBuilder>{

     AttributeSupportFluent<?> fluent;

    public AttributeSupportBuilder(){
        this.fluent = this;
    }
    public AttributeSupportBuilder( AttributeSupportFluent<?> fluent ){
        this.fluent = fluent;
    }
    public AttributeSupportBuilder( AttributeSupportFluent<?> fluent , AttributeSupport instance ){
        this.fluent = fluent; fluent.withAttributes(instance.getAttributes()); 
    }
    public AttributeSupportBuilder( AttributeSupport instance ){
        this.fluent = this; this.withAttributes(instance.getAttributes()); 
    }

public EditableAttributeSupport build(){
    EditableAttributeSupport buildable = new EditableAttributeSupport(fluent.getAttributes());
validate(buildable);
return buildable;

}
public boolean equals( Object o ){
    
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
if (!super.equals(o)) return false;
AttributeSupportBuilder that = (AttributeSupportBuilder) o;
if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;
return true;

}

private <T> void validate(T item) {}


}
    
