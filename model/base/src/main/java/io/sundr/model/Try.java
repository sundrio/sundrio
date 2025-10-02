package io.sundr.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Try implements Statement {

  private final List<Statement> resources;
  private final Block tryBlock;
  private final List<Catch> catchBlocks;
  private final Optional<Block> finallyBlock;

  public Try(Block tryBlock, List<Catch> catchBlocks, Optional<Block> finallyBlock) {
    this(Collections.emptyList(), tryBlock, catchBlocks, finallyBlock);
  }

  public Try(List<Statement> resources, Block tryBlock, List<Catch> catchBlocks, Optional<Block> finallyBlock) {
    this.resources = resources;
    this.tryBlock = tryBlock;
    this.catchBlocks = catchBlocks;
    this.finallyBlock = finallyBlock;
  }

  public List<Statement> getResources() {
    return resources;
  }

  public Block getTryBlock() {
    return tryBlock;
  }

  public List<Catch> getCatchBlocks() {
    return catchBlocks;
  }

  public Optional<Block> getFinallyBlock() {
    return finallyBlock;
  }

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    if (resources != null) {
      for (Statement resource : resources) {
        if (resource != null) {
          refs.addAll(resource.getReferences());
        }
      }
    }
    if (tryBlock != null) {
      refs.addAll(tryBlock.getReferences());
    }
    if (catchBlocks != null) {
      for (Catch catchBlock : catchBlocks) {
        if (catchBlock != null) {
          refs.addAll(catchBlock.getReferences());
        }
      }
    }
    finallyBlock.ifPresent(block -> refs.addAll(block.getReferences()));
    return refs;
  }

  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append("try");

    if (resources != null && !resources.isEmpty()) {
      sb.append(SPACE);
      sb.append(OP);
      for (int i = 0; i < resources.size(); i++) {
        if (i > 0) {
          sb.append(SEMICOLN);
          sb.append(SPACE);
        }
        sb.append(resources.get(i).render());
      }
      sb.append(CP);
    }

    sb.append(SPACE);
    sb.append(tryBlock.render());

    for (Catch catchBlock : catchBlocks) {
      sb.append(SPACE);
      sb.append(catchBlock.render());
    }

    finallyBlock.ifPresent(block -> {
      sb.append(SPACE);
      sb.append("finally");
      sb.append(SPACE);
      sb.append(block.render());
    });

    return sb.toString();
  }

  public static class Catch {
    private final Property parameter;
    private final Block block;

    public Catch(Property parameter, Block block) {
      this.parameter = parameter;
      this.block = block;
    }

    public Property getParameter() {
      return parameter;
    }

    public Block getBlock() {
      return block;
    }

    public Set<ClassRef> getReferences() {
      Set<ClassRef> refs = new HashSet<>();
      if (parameter != null) {
        refs.addAll(parameter.getReferences());
      }
      if (block != null) {
        refs.addAll(block.getReferences());
      }
      return refs;
    }

    public String render() {
      StringBuilder sb = new StringBuilder();
      sb.append("catch");
      sb.append(SPACE);
      sb.append(OP);
      sb.append(parameter.render());
      sb.append(CP);
      sb.append(SPACE);
      sb.append(block.render());
      return sb.toString();
    }
  }
}