package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class PostIncrementBuilder extends PostIncrementFluent<PostIncrementBuilder>
    implements VisitableBuilder<PostIncrement, PostIncrementBuilder> {
  public PostIncrementBuilder() {
    this.fluent = this;
  }

  public PostIncrementBuilder(PostIncrementFluent<?> fluent) {
    this.fluent = fluent;
  }

  public PostIncrementBuilder(PostIncrementFluent<?> fluent, PostIncrement instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public PostIncrementBuilder(PostIncrement instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  PostIncrementFluent<?> fluent;

  public PostIncrement build() {
    PostIncrement buildable = new PostIncrement(fluent.buildExpression());
    return buildable;
  }

}