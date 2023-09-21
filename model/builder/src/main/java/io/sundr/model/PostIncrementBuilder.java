package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class PostIncrementBuilder extends PostIncrementFluent<PostIncrementBuilder>
    implements VisitableBuilder<PostIncrement, PostIncrementBuilder> {
  public PostIncrementBuilder() {
    this(false);
  }

  public PostIncrementBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public PostIncrementBuilder(PostIncrementFluent<?> fluent) {
    this(fluent, false);
  }

  public PostIncrementBuilder(PostIncrementFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public PostIncrementBuilder(PostIncrementFluent<?> fluent, PostIncrement instance) {
    this(fluent, instance, false);
  }

  public PostIncrementBuilder(PostIncrementFluent<?> fluent, PostIncrement instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
    }
    this.validationEnabled = validationEnabled;
  }

  public PostIncrementBuilder(PostIncrement instance) {
    this(instance, false);
  }

  public PostIncrementBuilder(PostIncrement instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
    }
    this.validationEnabled = validationEnabled;
  }

  PostIncrementFluent<?> fluent;
  Boolean validationEnabled;

  public PostIncrement build() {
    PostIncrement buildable = new PostIncrement(fluent.buildExpression());
    return buildable;
  }

}
