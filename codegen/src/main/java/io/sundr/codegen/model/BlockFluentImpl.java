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

import io.sundr.builder.BaseFluent;
import io.sundr.builder.VisitableBuilder;

import java.util.ArrayList;
import java.util.List;

public class BlockFluentImpl<A extends BlockFluent<A>> extends BaseFluent<A> implements BlockFluent<A> {

    List<Statement> statements = new ArrayList();
    List<VisitableBuilder<StringStatement, ?>> stringStatementStatements = new ArrayList();

    public BlockFluentImpl() {

    }

    public BlockFluentImpl(Block instance) {
        this.withStatements(instance.getStatements());
    }

    public A withStatements(Statement... statements) {
        this.statements.clear();
        if (statements != null) {
            for (Statement item : statements) {
                this.addToStatements(item);
            }
        }
        return (A) this;
    }

    public Statement[] getStatements() {
        Statement[] result = new Statement[statements.size()];
        int index = 0;
        for (Statement item : statements) {
            result[index++] = item;
        }
        return result;

    }

    public A addToStatements(Statement... items) {
        for (Statement item : items) {
            if (item instanceof StringStatement) {
                addToStringStatementStatements((StringStatement) item);
            }
        }
        return (A) this;
    }

    public A removeFromStatements(Statement... items) {
        for (Statement item : items) {
            if (item instanceof StringStatement) {
                removeFromStringStatementStatements((StringStatement) item);
            }
        }
        return (A) this;
    }

    public A addToStringStatementStatements(StringStatement... items) {
        for (StringStatement item : items) {
            StringStatementBuilder builder = new StringStatementBuilder(item);
            _visitables.add(builder);
            this.stringStatementStatements.add(builder);
        }
        return (A) this;
    }

    public A removeFromStringStatementStatements(StringStatement... items) {
        for (StringStatement item : items) {
            StringStatementBuilder builder = new StringStatementBuilder(item);
            _visitables.remove(builder);
            this.stringStatementStatements.remove(builder);
        }
        return (A) this;
    }

    public StringStatementStatementsNested<A> addNewStringStatementStatement() {
        return new StringStatementStatementsNestedImpl();
    }

    public StringStatementStatementsNested<A> addNewStringStatementStatementLike(StringStatement item) {
        return new StringStatementStatementsNestedImpl(item);
    }

    public A addNewStringStatementStatement(String data) {
        return addToStringStatementStatements(new StringStatement(data));
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockFluentImpl that = (BlockFluentImpl) o;
        if (statements != null ? !statements.equals(that.statements) : that.statements != null) return false;
        if (stringStatementStatements != null ? !stringStatementStatements.equals(that.stringStatementStatements) : that.stringStatementStatements != null)
            return false;
        return true;

    }

    public class StringStatementStatementsNestedImpl<N> extends StringStatementFluentImpl<StringStatementStatementsNested<N>> implements StringStatementStatementsNested<N> {

        private final StringStatementBuilder builder;

        StringStatementStatementsNestedImpl() {
            this.builder = new StringStatementBuilder(this);
        }

        StringStatementStatementsNestedImpl(StringStatement item) {
            this.builder = new StringStatementBuilder(this, item);
        }

        public N endStringStatementStatement() {
            return and();
        }

        public N and() {
            return (N) BlockFluentImpl.this.addToStringStatementStatements(builder.build());
        }

    }


}
