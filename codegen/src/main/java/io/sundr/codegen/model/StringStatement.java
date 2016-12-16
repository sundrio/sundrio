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

import io.sundr.Provider;

public class StringStatement implements Statement {

    private final Provider<String> provider;

    public StringStatement(final String data) {
        this.provider = new Provider<String>() {
            @Override
            public String get() {
                return data;
            }
        };
    }

    public StringStatement(final String data, final Object... parameters) {
        this.provider = new Provider<String>() {
            @Override
            public String get() {
                return String.format(data, parameters);
            }
        };
    }

    public StringStatement(Provider<String> provider) {
        this.provider = provider;
    }

    public String getData() {
        return provider.get();
    }

    @Override
    public String toString() {
        return getData();
    }
}
