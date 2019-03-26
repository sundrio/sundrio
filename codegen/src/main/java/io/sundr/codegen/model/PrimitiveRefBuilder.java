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

import io.sundr.builder.VisitableBuilder;

public class PrimitiveRefBuilder extends PrimitiveRefFluentImpl<PrimitiveRefBuilder> implements VisitableBuilder<PrimitiveRef,PrimitiveRefBuilder>{

    PrimitiveRefFluent<?> fluent;
    Boolean validationEnabled;

    public PrimitiveRefBuilder(){
            this(true);
    }
    public PrimitiveRefBuilder(Boolean validationEnabled){
            this.fluent = this; this.validationEnabled=validationEnabled;
    }
    public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent){
            this(fluent, true);
    }
    public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent,Boolean validationEnabled){
            this.fluent = fluent; this.validationEnabled=validationEnabled;
    }
    public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent,PrimitiveRef instance){
            this(fluent, instance, true);
    }
    public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent,PrimitiveRef instance,Boolean validationEnabled){
            this.fluent = fluent; 
            fluent.withName(instance.getName()); 
            fluent.withDimensions(instance.getDimensions()); 
            fluent.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }
    public PrimitiveRefBuilder(PrimitiveRef instance){
            this(instance,true);
    }
    public PrimitiveRefBuilder(PrimitiveRef instance,Boolean validationEnabled){
            this.fluent = this; 
            this.withName(instance.getName()); 
            this.withDimensions(instance.getDimensions()); 
            this.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }

    public EditablePrimitiveRef build(){
            EditablePrimitiveRef buildable = new EditablePrimitiveRef(fluent.getName(),fluent.getDimensions(),fluent.getAttributes());
            return buildable;
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            PrimitiveRefBuilder that = (PrimitiveRefBuilder) o;
            if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;

            if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) :that.validationEnabled != null) return false;
            return true;
    }




}
