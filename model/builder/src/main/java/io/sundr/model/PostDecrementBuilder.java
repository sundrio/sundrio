package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class PostDecrementBuilder extends PostDecrementFluent<PostDecrementBuilder>
    implements VisitableBuilder<PostDecrement, PostDecrementBuilder> {
  public PostDecrementBuilder() {
    this(false);
  }

  public PostDecrementBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public PostDecrementBuilder(PostDecrementFluent<?> fluent) {
    this(fluent, false);
  }

  public PostDecrementBuilder(PostDecrementFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public PostDecrementBuilder(PostDecrementFluent<?> fluent, PostDecrement instance) {
    this(fluent, instance, false);
  }

  public PostDecrementBuilder(PostDecrementFluent<?> fluent, PostDecrement instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
    }
    this.validationEnabled = validationEnabled;
  }

  public PostDecrementBuilder(PostDecrement instance) {
    this(instance, false);
  }

  public PostDecrementBuilder(PostDecrement instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
    }
    this.validationEnabled = validationEnabled;
  }

  PostDecrementFluent<?> fluent;
  Boolean validationEnabled;

  public PostDecrement build() {
    PostDecrement buildable = new PostDecrement(fluent.buildExpression());
    return buildable;
  }

}
