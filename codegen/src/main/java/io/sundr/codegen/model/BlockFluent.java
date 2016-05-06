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

package io.sundr.codegen.model;

import io.sundr.builder.Fluent;
import io.sundr.builder.Nested;

public interface BlockFluent<A extends BlockFluent<A>> extends Fluent<A> {


    public A withStatements(Statement... statements);

    public Statement[] getStatements();

    public A addToStatements(Statement... items);

    public A removeFromStatements(Statement... items);

    public A addToStringStatementStatements(StringStatement... items);

    public A removeFromStringStatementStatements(StringStatement... items);

    public StringStatementStatementsNested<A> addNewStringStatementStatement();

    public StringStatementStatementsNested<A> addNewStringStatementStatementLike(StringStatement item);

    public A addNewStringStatementStatement(String data);

    public interface StringStatementStatementsNested<N> extends Nested<N>, StringStatementFluent<StringStatementStatementsNested<N>> {
        public N endStringStatementStatement();

        public N and();
    }


}
