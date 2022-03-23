package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class BlockBuilder extends BlockFluentImpl<BlockBuilder>
    implements VisitableBuilder<io.sundr.model.Block, BlockBuilder> {
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

  public BlockBuilder(io.sundr.model.BlockFluent<?> fluent, java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public BlockBuilder(io.sundr.model.BlockFluent<?> fluent, io.sundr.model.Block instance) {
    this(fluent, instance, false);
  }

  public BlockBuilder(io.sundr.model.BlockFluent<?> fluent, io.sundr.model.Block instance,
      java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withStatements(instance.getStatements());
    this.validationEnabled = validationEnabled;
  }

  public BlockBuilder(io.sundr.model.Block instance) {
    this(instance, false);
  }

  public BlockBuilder(io.sundr.model.Block instance, java.lang.Boolean validationEnabled) {
    this.fluent = this;
    this.withStatements(instance.getStatements());
    this.validationEnabled = validationEnabled;
  }

  io.sundr.model.BlockFluent<?> fluent;
  java.lang.Boolean validationEnabled;

  public io.sundr.model.Block build() {
    Block buildable = new Block(fluent.getStatements());
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
