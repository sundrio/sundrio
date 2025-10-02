package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class PostDecrementBuilder extends PostDecrementFluent<PostDecrementBuilder>
    implements VisitableBuilder<PostDecrement, PostDecrementBuilder> {

  PostDecrementFluent<?> fluent;

  public PostDecrementBuilder() {
    this.fluent = this;
  }

  public PostDecrementBuilder(PostDecrementFluent<?> fluent) {
    this.fluent = fluent;
  }

  public PostDecrementBuilder(PostDecrement instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public PostDecrementBuilder(PostDecrementFluent<?> fluent, PostDecrement instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public PostDecrement build() {
    PostDecrement buildable = new PostDecrement(fluent.buildExpression());
    return buildable;
  }

}