package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class PostIncrementBuilder extends PostIncrementFluent<PostIncrementBuilder>
    implements VisitableBuilder<PostIncrement, PostIncrementBuilder> {

  PostIncrementFluent<?> fluent;

  public PostIncrementBuilder() {
    this.fluent = this;
  }

  public PostIncrementBuilder(PostIncrementFluent<?> fluent) {
    this.fluent = fluent;
  }

  public PostIncrementBuilder(PostIncrement instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public PostIncrementBuilder(PostIncrementFluent<?> fluent, PostIncrement instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public PostIncrement build() {
    PostIncrement buildable = new PostIncrement(fluent.buildExpression());
    return buildable;
  }

}
