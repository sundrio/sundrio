/*
 *      Copyright 2019 The original authors.
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

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;
import io.sundr.builder.Predicate;
import io.sundr.builder.VisitableBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlockFluentImpl<A extends BlockFluent<A>> extends BaseFluent<A> implements BlockFluent<A>{

    private List<VisitableBuilder<? extends Statement,?>> statements =  new ArrayList<VisitableBuilder<? extends Statement,?>>();

    public BlockFluentImpl(){
    }
    public BlockFluentImpl(Block instance){
            this.withStatements(instance.getStatements()); 
    }

    public A addToStatements(VisitableBuilder<? extends Statement,?> builder){
            if (this.statements == null) {this.statements = new ArrayList<VisitableBuilder<? extends Statement,?>>();}
            _visitables.add(builder);this.statements.add(builder); return (A)this;
    }

    public A addToStatements(int index,VisitableBuilder<? extends Statement,?> builder){
            if (this.statements == null) {this.statements = new ArrayList<VisitableBuilder<? extends Statement,?>>();}
            _visitables.add(index, builder);this.statements.add(index, builder); return (A)this;
    }

    public A addToStatements(int index,Statement item){
            if (item instanceof StringStatement){addToStringStatementStatements(index, (StringStatement)item);}

            return (A)this;
    }

    public A setToStatements(int index,Statement item){
            if (item instanceof StringStatement){setToStringStatementStatements(index, (StringStatement)item);}

            return (A)this;
    }

    public A addToStatements(Statement... items){
             if (items != null && items.length > 0 && this.statements== null) {this.statements = new ArrayList<VisitableBuilder<? extends Statement,?>>();}
            for (Statement item : items) { 
            if (item instanceof StringStatement){addToStringStatementStatements((StringStatement)item);}

            else {  VisitableBuilder<? extends Statement,?> builder = builderOf(item); _visitables.add(builder);this.statements.add(builder); }
            } return (A)this;
    }

    public A addAllToStatements(Collection<Statement> items){
             if (items != null && items.size() > 0 && this.statements== null) {this.statements = new ArrayList<VisitableBuilder<? extends Statement,?>>();}
            for (Statement item : items) { 
            if (item instanceof StringStatement){addToStringStatementStatements((StringStatement)item);}

            else {  VisitableBuilder<? extends Statement,?> builder = builderOf(item); _visitables.add(builder);this.statements.add(builder); }
            } return (A)this;
    }

    public A removeFromStatements(VisitableBuilder<? extends Statement,?> builder){
            if (this.statements == null) {this.statements = new ArrayList<VisitableBuilder<? extends Statement,?>>();}
            _visitables.remove(builder);this.statements.remove(builder); return (A)this;
    }

    public A removeFromStatements(Statement... items){
            for (Statement item : items) {if (item instanceof StringStatement){removeFromStringStatementStatements((StringStatement)item);}

            else {  VisitableBuilder<? extends Statement,?> builder = builderOf(item); _visitables.remove(builder);this.statements.remove(builder); }
            } return (A)this;
    }

    public A removeAllFromStatements(Collection<Statement> items){
            for (Statement item : items) {if (item instanceof StringStatement){removeFromStringStatementStatements((StringStatement)item);}

            else {  VisitableBuilder<? extends Statement,?> builder = builderOf(item); _visitables.remove(builder);this.statements.remove(builder); }
            } return (A)this;
    }

    
/**
 * This method has been deprecated, please use method buildStatements instead.
 * @return The buildable object.
 */
@Deprecated public List<Statement> getStatements(){
            return build(statements);
    }

    public List<Statement> buildStatements(){
            return build(statements);
    }

    public Statement buildStatement(int index){
            return this.statements.get(index).build();
    }

    public Statement buildFirstStatement(){
            return this.statements.get(0).build();
    }

    public Statement buildLastStatement(){
            return this.statements.get(statements.size() - 1).build();
    }

    public Statement buildMatchingStatement(Predicate<VisitableBuilder<? extends Statement,?>> predicate){
            for (VisitableBuilder<? extends Statement,?> item: statements) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public Boolean hasMatchingStatement(Predicate<VisitableBuilder<? extends Statement,?>> predicate){
            for (VisitableBuilder<? extends Statement,?> item: statements) { if(predicate.apply(item)){return true;} } return false;
    }

    public A withStatements(List<Statement> statements){
            if (this.statements != null) { _visitables.removeAll(this.statements);}
            if (statements != null) {this.statements = new ArrayList<VisitableBuilder<? extends Statement,?>>(); for (Statement item : statements){this.addToStatements(item);}} else { this.statements = new ArrayList<VisitableBuilder<? extends Statement,?>>();} return (A) this;
    }

    public A withStatements(Statement... statements){
            if (this.statements != null) {this.statements.clear();}
            if (statements != null) {for (Statement item :statements){ this.addToStatements(item);}} return (A) this;
    }

    public Boolean hasStatements(){
            return statements != null && !statements.isEmpty();
    }

    public A addToStringStatementStatements(int index,StringStatement item){
            if (this.statements == null) {this.statements = new ArrayList<VisitableBuilder<? extends Statement,?>>();}
            StringStatementBuilder builder = new StringStatementBuilder(item);_visitables.add(index >= 0 ? index : _visitables.size(), builder);this.statements.add(index >= 0 ? index : statements.size(), builder); return (A)this;
    }

    public A setToStringStatementStatements(int index,StringStatement item){
            if (this.statements == null) {this.statements = new ArrayList<VisitableBuilder<? extends Statement,?>>();}
            StringStatementBuilder builder = new StringStatementBuilder(item);
            if (index < 0 || index >= _visitables.size()) { _visitables.add(builder); } else { _visitables.set(index, builder);}
            if (index < 0 || index >= statements.size()) { statements.add(builder); } else { statements.set(index, builder);}
             return (A)this;
    }

    public A addToStringStatementStatements(StringStatement... items){
            if (this.statements == null) {this.statements = new ArrayList<VisitableBuilder<? extends Statement,?>>();}
            for (StringStatement item : items) {StringStatementBuilder builder = new StringStatementBuilder(item);_visitables.add(builder);this.statements.add(builder);} return (A)this;
    }

    public A addAllToStringStatementStatements(Collection<StringStatement> items){
            if (this.statements == null) {this.statements = new ArrayList<VisitableBuilder<? extends Statement,?>>();}
            for (StringStatement item : items) {StringStatementBuilder builder = new StringStatementBuilder(item);_visitables.add(builder);this.statements.add(builder);} return (A)this;
    }

    public A removeFromStringStatementStatements(StringStatement... items){
            for (StringStatement item : items) {StringStatementBuilder builder = new StringStatementBuilder(item);_visitables.remove(builder);if (this.statements != null) {this.statements.remove(builder);}} return (A)this;
    }

    public A removeAllFromStringStatementStatements(Collection<StringStatement> items){
            for (StringStatement item : items) {StringStatementBuilder builder = new StringStatementBuilder(item);_visitables.remove(builder);if (this.statements != null) {this.statements.remove(builder);}} return (A)this;
    }

    public BlockFluent.StringStatementStatementsNested<A> addNewStringStatementStatement(){
            return new StringStatementStatementsNestedImpl();
    }

    public BlockFluent.StringStatementStatementsNested<A> addNewStringStatementStatementLike(StringStatement item){
            return new StringStatementStatementsNestedImpl(-1, item);
    }

    public A addNewStringStatementStatement(final String data){
            return (A)addToStringStatementStatements(new StringStatement(data));
    }

    public A addNewStringStatementStatement(final String data,final Object[] parameters){
            return (A)addToStringStatementStatements(new StringStatement(data, parameters));
    }

    public BlockFluent.StringStatementStatementsNested<A> setNewStringStatementStatementLike(int index,StringStatement item){
            return new StringStatementStatementsNestedImpl(index, item);
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BlockFluentImpl that = (BlockFluentImpl) o;
            if (statements != null ? !statements.equals(that.statements) :that.statements != null) return false;
            return true;
    }


    public class StringStatementStatementsNestedImpl<N> extends StringStatementFluentImpl<BlockFluent.StringStatementStatementsNested<N>> implements BlockFluent.StringStatementStatementsNested<N>,Nested<N>{

            private final StringStatementBuilder builder;
        private final int index;
    
            StringStatementStatementsNestedImpl(int index,StringStatement item){
                    this.index = index;
                    this.builder = new StringStatementBuilder(this, item);
            }
            StringStatementStatementsNestedImpl(){
                    this.index = -1;
                    this.builder = new StringStatementBuilder(this);
            }
    
    public N and(){
            return (N) BlockFluentImpl.this.setToStringStatementStatements(index, builder.build());
    }
    public N endStringStatementStatement(){
            return and();
    }

}


}
