package io.sundr.codegen.model;

import io.sundr.builder.VisitableBuilder;
import java.lang.Object;
import java.lang.Boolean;

public class WildcardRefBuilder extends WildcardRefFluentImpl<WildcardRefBuilder> implements VisitableBuilder<WildcardRef,WildcardRefBuilder>{

    WildcardRefFluent<?> fluent;
    Boolean validationEnabled;

    public WildcardRefBuilder(){
            this(true);
    }
    public WildcardRefBuilder(Boolean validationEnabled){
            this(new WildcardRef(), validationEnabled);
    }
    public WildcardRefBuilder(WildcardRefFluent<?> fluent){
            this(fluent, true);
    }
    public WildcardRefBuilder(WildcardRefFluent<?> fluent,Boolean validationEnabled){
            this(fluent, new WildcardRef(), validationEnabled);
    }
    public WildcardRefBuilder(WildcardRefFluent<?> fluent,WildcardRef instance){
            this(fluent, instance, true);
    }
    public WildcardRefBuilder(WildcardRefFluent<?> fluent,WildcardRef instance,Boolean validationEnabled){
            this.fluent = fluent; 
            fluent.withBoundKind(instance.getBoundKind()); 
            fluent.withBounds(instance.getBounds()); 
            fluent.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }
    public WildcardRefBuilder(WildcardRef instance){
            this(instance,true);
    }
    public WildcardRefBuilder(WildcardRef instance,Boolean validationEnabled){
            this.fluent = this; 
            this.withBoundKind(instance.getBoundKind()); 
            this.withBounds(instance.getBounds()); 
            this.withAttributes(instance.getAttributes()); 
            this.validationEnabled = validationEnabled; 
    }

    public EditableWildcardRef build(){
            EditableWildcardRef buildable = new EditableWildcardRef(fluent.getBoundKind(),fluent.getBounds(),fluent.getAttributes());
            return buildable;
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            WildcardRefBuilder that = (WildcardRefBuilder) o;
            if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;

            if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) :that.validationEnabled != null) return false;
            return true;
    }




}
