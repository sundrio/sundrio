package io.sundr.model;

import java.util.Collections;
import java.util.Set;

public class Empty implements ExpressionOrStatement {

  @Override
  public Set<ClassRef> getReferences() {
    return Collections.emptySet();
  }

  public String render() {
    return SEMICOLN;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Empty;
  }

  @Override
  public int hashCode() {
    // All instances are considered equal, so return a constant hash code
    return 31;
  }

}
