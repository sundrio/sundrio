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

import io.sundr.builder.VisitableBuilder;

public class BlockBuilder extends BlockFluentImpl<BlockBuilder> implements VisitableBuilder<Block,BlockBuilder>{

    BlockFluent<?> fluent;
    Boolean validationEnabled;

    public BlockBuilder(){
            this(true);
    }
    public BlockBuilder(Boolean validationEnabled){
            this.fluent = this; this.validationEnabled=validationEnabled;
    }
    public BlockBuilder(BlockFluent<?> fluent){
            this(fluent, true);
    }
    public BlockBuilder(BlockFluent<?> fluent,Boolean validationEnabled){
            this.fluent = fluent; this.validationEnabled=validationEnabled;
    }
    public BlockBuilder(BlockFluent<?> fluent,Block instance){
            this(fluent, instance, true);
    }
    public BlockBuilder(BlockFluent<?> fluent,Block instance,Boolean validationEnabled){
            this.fluent = fluent; 
            fluent.withStatements(instance.getStatements()); 
            this.validationEnabled = validationEnabled; 
    }
    public BlockBuilder(Block instance){
            this(instance,true);
    }
    public BlockBuilder(Block instance,Boolean validationEnabled){
            this.fluent = this; 
            this.withStatements(instance.getStatements()); 
            this.validationEnabled = validationEnabled; 
    }

    public EditableBlock build(){
            EditableBlock buildable = new EditableBlock(fluent.getStatements());
            return buildable;
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            BlockBuilder that = (BlockBuilder) o;
            if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;

            if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) :that.validationEnabled != null) return false;
            return true;
    }




}
