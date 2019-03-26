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

package io.sundr.examples.codegen;

import io.sundr.Provider;
import io.sundr.builder.annotations.Buildable;

import java.util.List;

public class Block {

    private final Provider<List<Statement>> provider;

    public Block(Provider<List<Statement>> provider) {
        this.provider = provider;
    }

    @Buildable(lazyCollectionInitEnabled=false)
    public Block(final List<Statement> statements) {
        this.provider = new Provider<List<Statement>>() {
            @Override
            public List<Statement> get() {
                return statements;
            }
        };
    }

    public List<Statement> getStatements() {
        return provider.get();
    }


}
