package io.sundr.codegen.model;

import io.sundr.builder.VisitableBuilder;

import java.lang.Boolean;
import java.lang.Object;

public class AnnotationRefBuilder extends AnnotationRefFluentImpl<AnnotationRefBuilder> implements VisitableBuilder<AnnotationRef, AnnotationRefBuilder> {

    AnnotationRefFluent<?> fluent;
    Boolean validationEnabled;

    public AnnotationRefBuilder() {
        this(true);
    }

    public AnnotationRefBuilder(Boolean validationEnabled) {
        this.fluent = this;
        this.validationEnabled = validationEnabled;
    }

    public AnnotationRefBuilder(AnnotationRefFluent<?> fluent) {
        this(fluent, true);
    }

    public AnnotationRefBuilder(AnnotationRefFluent<?> fluent, Boolean validationEnabled) {
        this.fluent = fluent;
        this.validationEnabled = validationEnabled;
    }

    public AnnotationRefBuilder(AnnotationRefFluent<?> fluent, AnnotationRef instance) {
        this(fluent, instance, true);
    }

    public AnnotationRefBuilder(AnnotationRefFluent<?> fluent, AnnotationRef instance, Boolean validationEnabled) {
        this.fluent = fluent;
        fluent.withClassRef(instance.getClassRef());
        fluent.withParameters(instance.getParameters());
        fluent.withAttributes(instance.getAttributes());
        this.validationEnabled = validationEnabled;
    }

    public AnnotationRefBuilder(AnnotationRef instance) {
        this(instance, true);
    }

    public AnnotationRefBuilder(AnnotationRef instance, Boolean validationEnabled) {
        this.fluent = this;
        this.withClassRef(instance.getClassRef());
        this.withParameters(instance.getParameters());
        this.withAttributes(instance.getAttributes());
        this.validationEnabled = validationEnabled;
    }

    public EditableAnnotationRef build() {
        EditableAnnotationRef buildable = new EditableAnnotationRef(fluent.getClassRef(), fluent.getParameters(), fluent.getAttributes());
        validate(buildable);
        return buildable;
    }

    private <T> void validate(T item) {
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AnnotationRefBuilder that = (AnnotationRefBuilder) o;
        if (fluent != null && fluent != this ? !fluent.equals(that.fluent) : that.fluent != null && fluent != this)
            return false;

        if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) : that.validationEnabled != null)
            return false;
        return true;
    }


}
