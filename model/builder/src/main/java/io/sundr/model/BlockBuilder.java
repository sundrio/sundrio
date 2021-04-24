package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.model.builder.VisitableBuilder;

public class BlockBuilder extends BlockFluentImpl<BlockBuilder>
    implements io.sundr.model.builder.VisitableBuilder<Block, BlockBuilder> {

  BlockFluent<?> fluent;
  Boolean validationEnabled;

  public BlockBuilder() {
    this(true);
  }

  public BlockBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public BlockBuilder(BlockFluent<?> fluent) {
    this(fluent, true);
  }

  public BlockBuilder(BlockFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public BlockBuilder(BlockFluent<?> fluent, Block instance) {
    this(fluent, instance, true);
  }

  public BlockBuilder(BlockFluent<?> fluent, Block instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withProvider(instance.getProvider());
    this.validationEnabled = validationEnabled;
  }

  public BlockBuilder(Block instance) {
    this(instance, true);
  }

  public BlockBuilder(Block instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withProvider(instance.getProvider());
    this.validationEnabled = validationEnabled;
  }

  public Block build() {
    Block buildable = new Block(fluent.getProvider());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    BlockBuilder that = (BlockBuilder) o;
    if (fluent != null && fluent != this ? !fluent.equals(that.fluent) : that.fluent != null && fluent != this)
      return false;

    if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) : that.validationEnabled != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(fluent, validationEnabled, super.hashCode());
  }

}
