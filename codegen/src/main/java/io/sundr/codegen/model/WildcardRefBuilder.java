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

public class WildcardRefBuilder extends WildcardRefFluentImpl<WildcardRefBuilder> implements VisitableBuilder<WildcardRef,WildcardRefBuilder>{

     WildcardRefFluent<?> fluent;

    public WildcardRefBuilder(){
        this(new WildcardRef());
    }
    public WildcardRefBuilder( WildcardRefFluent<?> fluent ){
        this(fluent, new WildcardRef());
    }
    public WildcardRefBuilder( WildcardRefFluent<?> fluent , WildcardRef instance ){
        this.fluent = fluent; fluent.withAttributes(instance.getAttributes()); 
    }
    public WildcardRefBuilder( WildcardRef instance ){
        this.fluent = this; this.withAttributes(instance.getAttributes()); 
    }

public EditableWildcardRef build(){
    EditableWildcardRef buildable = new EditableWildcardRef(fluent.getAttributes());
validate(buildable);
return buildable;

}
public boolean equals( Object o ){
    
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
if (!super.equals(o)) return false;
WildcardRefBuilder that = (WildcardRefBuilder) o;
if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;
return true;

}

private <T> void validate(T item) {}


}
    
