package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class BlockBuilder extends BlockFluent<BlockBuilder> implements VisitableBuilder<Block, BlockBuilder> {
  public BlockBuilder() {
    this.fluent = this;
  }

  public BlockBuilder(BlockFluent<?> fluent) {
    this.fluent = fluent;
  }

  public BlockBuilder(BlockFluent<?> fluent, Block instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public BlockBuilder(Block instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  BlockFluent<?> fluent;

  public Block build() {
    Block buildable = new Block(fluent.buildStatements());
    return buildable;
  }

}