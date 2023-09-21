package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class NotEqualsBuilder extends NotEqualsFluent<NotEqualsBuilder>
    implements VisitableBuilder<NotEquals, NotEqualsBuilder> {
  public NotEqualsBuilder() {
    this(false);
  }

  public NotEqualsBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public NotEqualsBuilder(NotEqualsFluent<?> fluent) {
    this(fluent, false);
  }

  public NotEqualsBuilder(NotEqualsFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public NotEqualsBuilder(NotEqualsFluent<?> fluent, NotEquals instance) {
    this(fluent, instance, false);
  }

  public NotEqualsBuilder(NotEqualsFluent<?> fluent, NotEquals instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public NotEqualsBuilder(NotEquals instance) {
    this(instance, false);
  }

  public NotEqualsBuilder(NotEquals instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  NotEqualsFluent<?> fluent;
  Boolean validationEnabled;

  public NotEquals build() {
    NotEquals buildable = new NotEquals(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
