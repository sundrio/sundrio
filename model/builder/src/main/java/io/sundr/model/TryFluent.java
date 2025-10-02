package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class TryFluent<A extends TryFluent<A>> extends BaseFluent<A> {

  private List<Try.Catch> catchBlocks = new ArrayList<Try.Catch>();
  private Optional<BlockBuilder> finallyBlock = Optional.empty();
  private BlockBuilder tryBlock;

  public TryFluent() {
  }

  public TryFluent(Try instance) {
    this.copyInstance(instance);
  }

  public A addAllToCatchBlocks(Collection<Try.Catch> items) {
    if (this.catchBlocks == null) {
      this.catchBlocks = new ArrayList<Try.Catch>();
    }
    for (Try.Catch item : items) {
      this.catchBlocks.add(item);
    }
    return (A) this;
  }

  public A addToCatchBlocks(Try.Catch... items) {
    if (this.catchBlocks == null) {
      this.catchBlocks = new ArrayList<Try.Catch>();
    }
    for (Try.Catch item : items) {
      this.catchBlocks.add(item);
    }
    return (A) this;
  }

  public A addToCatchBlocks(int index, Try.Catch item) {
    if (this.catchBlocks == null) {
      this.catchBlocks = new ArrayList<Try.Catch>();
    }
    this.catchBlocks.add(index, item);
    return (A) this;
  }

  public Optional<Block> buildFinallyBlock() {
    return (Optional<Block>) (this.finallyBlock != null && this.finallyBlock.isPresent()
        ? Optional.of(this.finallyBlock.get().build())
        : Optional.empty());
  }

  public Block buildTryBlock() {
    return this.tryBlock != null ? this.tryBlock.build() : null;
  }

  protected void copyInstance(Try instance) {
    if (instance != null) {
      this.withTryBlock(instance.getTryBlock());
      this.withCatchBlocks(instance.getCatchBlocks());
      this.withFinallyBlock(instance.getFinallyBlock());
    }
  }

  public FinallyBlockNested<A> editFinallyBlock() {
    return withNewFinallyBlockLike(
        Optional.ofNullable(buildFinallyBlock()).flatMap(java.util.function.Function.identity()).orElse(null));
  }

  public <T> FinallyBlockNested<?> editOrNewFinallyBlock() {
    return withNewFinallyBlockLike(Optional.ofNullable(buildFinallyBlock()).flatMap(java.util.function.Function.identity())
        .orElse(new BlockBuilder().build()));
  }

  public FinallyBlockNested<A> editOrNewFinallyBlockLike(Block item) {
    return withNewFinallyBlockLike(
        Optional.ofNullable(buildFinallyBlock()).flatMap(java.util.function.Function.identity()).orElse(item));
  }

  public TryBlockNested<A> editOrNewTryBlock() {
    return withNewTryBlockLike(Optional.ofNullable(buildTryBlock()).orElse(new BlockBuilder().build()));
  }

  public TryBlockNested<A> editOrNewTryBlockLike(Block item) {
    return withNewTryBlockLike(Optional.ofNullable(buildTryBlock()).orElse(item));
  }

  public TryBlockNested<A> editTryBlock() {
    return withNewTryBlockLike(Optional.ofNullable(buildTryBlock()).orElse(null));
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    TryFluent that = (TryFluent) o;
    if (!java.util.Objects.equals(tryBlock, that.tryBlock))
      return false;
    if (!java.util.Objects.equals(catchBlocks, that.catchBlocks))
      return false;
    if (!java.util.Objects.equals(finallyBlock, that.finallyBlock))
      return false;
    return true;
  }

  public Try.Catch getCatchBlock(int index) {
    return this.catchBlocks.get(index);
  }

  public List<Try.Catch> getCatchBlocks() {
    return this.catchBlocks;
  }

  public Try.Catch getFirstCatchBlock() {
    return this.catchBlocks.get(0);
  }

  public Try.Catch getLastCatchBlock() {
    return this.catchBlocks.get(catchBlocks.size() - 1);
  }

  public Try.Catch getMatchingCatchBlock(Predicate<Try.Catch> predicate) {
    for (Try.Catch item : catchBlocks) {
      if (predicate.test(item)) {
        return item;
      }
    }
    return null;
  }

  public boolean hasCatchBlocks() {
    return this.catchBlocks != null && !(this.catchBlocks.isEmpty());
  }

  public boolean hasFinallyBlock() {
    return this.finallyBlock != null && this.finallyBlock.isPresent();
  }

  public boolean hasMatchingCatchBlock(Predicate<Try.Catch> predicate) {
    for (Try.Catch item : catchBlocks) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasTryBlock() {
    return this.tryBlock != null;
  }

  public int hashCode() {
    return java.util.Objects.hash(tryBlock, catchBlocks, finallyBlock, super.hashCode());
  }

  public A removeAllFromCatchBlocks(Collection<Try.Catch> items) {
    if (this.catchBlocks == null)
      return (A) this;
    for (Try.Catch item : items) {
      this.catchBlocks.remove(item);
    }
    return (A) this;
  }

  public A removeFromCatchBlocks(Try.Catch... items) {
    if (this.catchBlocks == null)
      return (A) this;
    for (Try.Catch item : items) {
      this.catchBlocks.remove(item);
    }
    return (A) this;
  }

  public A setToCatchBlocks(int index, Try.Catch item) {
    if (this.catchBlocks == null) {
      this.catchBlocks = new ArrayList<Try.Catch>();
    }
    this.catchBlocks.set(index, item);
    return (A) this;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (tryBlock != null) {
      sb.append("tryBlock:");
      sb.append(tryBlock + ",");
    }
    if (catchBlocks != null && !catchBlocks.isEmpty()) {
      sb.append("catchBlocks:");
      sb.append(catchBlocks + ",");
    }
    if (finallyBlock != null) {
      sb.append("finallyBlock:");
      sb.append(finallyBlock);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withCatchBlocks(List<Try.Catch> catchBlocks) {
    if (catchBlocks != null) {
      this.catchBlocks = new ArrayList();
      for (Try.Catch item : catchBlocks) {
        this.addToCatchBlocks(item);
      }
    } else {
      this.catchBlocks = null;
    }
    return (A) this;
  }

  public A withCatchBlocks(Try.Catch... catchBlocks) {
    if (this.catchBlocks != null) {
      this.catchBlocks.clear();
      _visitables.remove("catchBlocks");
    }
    if (catchBlocks != null) {
      for (Try.Catch item : catchBlocks) {
        this.addToCatchBlocks(item);
      }
    }
    return (A) this;
  }

  public A withFinallyBlock(Optional<Block> finallyBlock) {
    if (finallyBlock == null || !(finallyBlock.isPresent())) {
      this.finallyBlock = Optional.empty();
    } else {
      BlockBuilder b = new BlockBuilder(finallyBlock.get());
      _visitables.get("finallyBlock").add(b);
      this.finallyBlock = Optional.of(b);
    }
    return (A) this;
  }

  public A withFinallyBlock(Block finallyBlock) {
    if (finallyBlock == null) {
      this.finallyBlock = Optional.empty();
    } else {
      BlockBuilder b = new BlockBuilder(finallyBlock);
      _visitables.get("finallyBlock").add(b);
      this.finallyBlock = Optional.of(b);
    }
    return (A) this;
  }

  public FinallyBlockNested<A> withNewFinallyBlock() {
    return new FinallyBlockNested(null);
  }

  public FinallyBlockNested<A> withNewFinallyBlockLike(Block item) {
    return new FinallyBlockNested(item);
  }

  public TryBlockNested<A> withNewTryBlock() {
    return new TryBlockNested(null);
  }

  public TryBlockNested<A> withNewTryBlockLike(Block item) {
    return new TryBlockNested(item);
  }

  public A withTryBlock(Block tryBlock) {
    this._visitables.remove("tryBlock");
    if (tryBlock != null) {
      this.tryBlock = new BlockBuilder(tryBlock);
      this._visitables.get("tryBlock").add(this.tryBlock);
    } else {
      this.tryBlock = null;
      this._visitables.get("tryBlock").remove(this.tryBlock);
    }
    return (A) this;
  }

  public class FinallyBlockNested<N> extends BlockFluent<FinallyBlockNested<N>> implements Nested<N> {

    BlockBuilder builder;

    FinallyBlockNested(Block item) {
      this.builder = new BlockBuilder(this, item);
    }

    public N and() {
      return (N) TryFluent.this.withFinallyBlock(builder.build());
    }

    public N endFinallyBlock() {
      return and();
    }

  }

  public class TryBlockNested<N> extends BlockFluent<TryBlockNested<N>> implements Nested<N> {

    BlockBuilder builder;

    TryBlockNested(Block item) {
      this.builder = new BlockBuilder(this, item);
    }

    public N and() {
      return (N) TryFluent.this.withTryBlock(builder.build());
    }

    public N endTryBlock() {
      return and();
    }

  }
}
