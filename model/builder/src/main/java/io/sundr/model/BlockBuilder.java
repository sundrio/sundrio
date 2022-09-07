package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class BlockBuilder extends BlockFluentImpl<BlockBuilder> implements VisitableBuilder<Block, BlockBuilder> {
  public BlockBuilder() {
    this(false);
  }

  public BlockBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public BlockBuilder(BlockFluent<?> fluent) {
    this(fluent, false);
  }

  public BlockBuilder(BlockFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public BlockBuilder(BlockFluent<?> fluent, Block instance) {
    this(fluent, instance, false);
  }

  public BlockBuilder(BlockFluent<?> fluent, Block instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withStatements(instance.getStatements());
    this.validationEnabled = validationEnabled;
  }

  public BlockBuilder(Block instance) {
    this(instance, false);
  }

  public BlockBuilder(Block instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withStatements(instance.getStatements());
    this.validationEnabled = validationEnabled;
  }

  BlockFluent<?> fluent;
  Boolean validationEnabled;

  public Block build() {
    Block buildable = new Block(fluent.getStatements());
    return buildable;
  }

}
