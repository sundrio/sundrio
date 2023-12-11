/**
 * Copyright 2015 The original authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
**/

package io.sundr.adapter.source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;

import io.sundr.model.Block;
import io.sundr.model.BlockBuilder;

public class BlockStmtToBlock implements Function<BlockStmt, Block> {

  @Override
  public Block apply(BlockStmt block) {
    List<Statement> statements = block != null && block.getStmts() != null ? block.getStmts() : Collections.emptyList();
    List<io.sundr.model.Statement> converted = new ArrayList<>();
    for (Statement statement : statements) {
      converted.add(StatementConverter.convertStatement(statement));
    }

    return new BlockBuilder().withStatements(converted).build();
  }
}
