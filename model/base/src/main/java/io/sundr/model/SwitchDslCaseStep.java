package io.sundr.model;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SwitchDslCaseStep implements Statement {
  private Expression expression;
  private Map<ValueRef, Block> cases;

  SwitchDslCaseStep(Expression expression) {
    this.expression = expression;
    this.cases = new LinkedHashMap<>();
  }

  SwitchDslCaseStep(Expression expression, Map<ValueRef, Block> cases) {
    this.expression = expression;
    this.cases = new LinkedHashMap<>(cases);
  }

  public SwitchDslCaseStep inCase(Object key, Statement... statements) {
    ValueRef keyRef = key instanceof ValueRef ? (ValueRef) key : ValueRef.from(key);
    cases.put(keyRef, new Block(Arrays.asList(statements)));
    return this;
  }

  public SwitchDslCaseStep inCase(ValueRef key, Statement... statements) {
    cases.put(key, new Block(Arrays.asList(statements)));
    return this;
  }

  public SwitchDslCaseStep inCase(Object key, List<Statement> statements) {
    ValueRef keyRef = key instanceof ValueRef ? (ValueRef) key : ValueRef.from(key);
    cases.put(keyRef, new Block(statements));
    return this;
  }

  public SwitchDslCaseStep inCase(ValueRef key, List<Statement> statements) {
    cases.put(key, new Block(statements));
    return this;
  }

  public SwitchDslCaseStep inCase(Object key, Block block) {
    ValueRef keyRef = key instanceof ValueRef ? (ValueRef) key : ValueRef.from(key);
    cases.put(keyRef, block);
    return this;
  }

  public SwitchDslCaseStep inCase(ValueRef key, Block block) {
    cases.put(key, block);
    return this;
  }

  public SwitchDslCaseStep cases(Map<ValueRef, Block> additionalCases) {
    this.cases.putAll(additionalCases);
    return this;
  }

  public Switch defaultCase(Statement... statements) {
    return new Switch(expression, cases, Optional.of(new Block(Arrays.asList(statements))));
  }

  public Switch defaultCase(List<Statement> statements) {
    return new Switch(expression, cases, Optional.of(new Block(statements)));
  }

  public Switch defaultCase(Block block) {
    return new Switch(expression, cases, Optional.of(block));
  }

  public Switch end() {
    return new Switch(expression, cases, Optional.empty());
  }

  @Override
  public String render() {
    return end().render();
  }
}