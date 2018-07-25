/*
 *      Copyright 2016 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.codegen.model;

import java.util.Collection;
import java.util.List;

import io.sundr.builder.Builder;
import io.sundr.builder.Fluent;
import io.sundr.builder.Nested;
import io.sundr.builder.Predicate;

public interface BlockFluent<A extends BlockFluent<A>> extends Fluent<A>{


    public A addToStatements(int index, Statement item);
    public A setToStatements(int index, Statement item);
    public A addToStatements(Statement... items);
    public A addAllToStatements(Collection<Statement> items);
    public A removeFromStatements(Statement... items);
    public A removeAllFromStatements(Collection<Statement> items);

    /**
     * This method has been deprecated, please use method buildStatements instead.
     * @return The buildable object.
     */
    @Deprecated
    public List<Statement> getStatements();
    public List<Statement> buildStatements();
    public Statement buildStatement(int index);
    public Statement buildFirstStatement();
    public Statement buildLastStatement();
    public Statement buildMatchingStatement(Predicate<Builder<? extends Statement>> predicate);
    public A withStatements(List<Statement> statements);
    public A withStatements(Statement... statements);
    public Boolean hasStatements();
    public A addToStringStatementStatements(int index, StringStatement item);
    public A setToStringStatementStatements(int index, StringStatement item);
    public A addToStringStatementStatements(StringStatement... items);
    public A addAllToStringStatementStatements(Collection<StringStatement> items);
    public A removeFromStringStatementStatements(StringStatement... items);
    public A removeAllFromStringStatementStatements(Collection<StringStatement> items);
    public StringStatementStatementsNested<A> addNewStringStatementStatement();
    public StringStatementStatementsNested<A> addNewStringStatementStatementLike(StringStatement item);
    public A addNewStringStatementStatement(final String data);
    public A addNewStringStatementStatement(final String data, final Object[] parameters);

    public interface StringStatementStatementsNested<N> extends Nested<N>,StringStatementFluent<StringStatementStatementsNested<N>>{

        
    public N and();    public N endStringStatementStatement();
}


}
