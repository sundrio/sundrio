package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class NotEqualsBuilder extends NotEqualsFluent<NotEqualsBuilder>
    implements VisitableBuilder<NotEquals, NotEqualsBuilder> {

  NotEqualsFluent<?> fluent;

  public NotEqualsBuilder() {
    this.fluent = this;
  }

  public NotEqualsBuilder(NotEqualsFluent<?> fluent) {
    this.fluent = fluent;
  }

  public NotEqualsBuilder(NotEquals instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public NotEqualsBuilder(NotEqualsFluent<?> fluent, NotEquals instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public NotEquals build() {
    NotEquals buildable = new NotEquals(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}