/*
 * Copyright 2016 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.model;

import java.util.Arrays;
import java.util.List;

public class Block implements Statement {

  private List<Statement> statements;

  public Block(List<Statement> statements) {
    this.statements = statements;
  }

  public Block(Statement... statements) {
    this.statements = Arrays.asList(statements);
  }

  public static Statement wrap(List<Statement> statements) {
    if (statements == null || statements.isEmpty()) {
      return new Block();
    }
    if (statements.size() == 1) {
      return statements.get(0);
    }
    return new Block(statements);
  }

  public static Statement wrap(Statement... statements) {
    if (statements == null || statements.length == 0) {
      return new Block();
    }
    if (statements.length == 1) {
      return statements[0];
    }
    return new Block(statements);
  }

  public List<Statement> getStatements() {
    return statements;
  }

  @Override
  public String render() {
    StringBuilder sb = new StringBuilder();
    for (Statement statement : statements) {
      sb.append(tab(statement.renderStatement()));
    }
    return sb.toString();
  }
}
