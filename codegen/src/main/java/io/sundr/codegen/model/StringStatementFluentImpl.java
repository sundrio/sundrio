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

public class StringStatementFluentImpl<A extends StringStatementFluent<A>> extends BaseFluent<A> implements StringStatementFluent<A> {

    String data;

    public StringStatementFluentImpl() {

    }

    public StringStatementFluentImpl(StringStatement instance) {
        this.withData(instance.getData());
    }

    public String getData() {
        return this.data;
    }

    public A withData(String data) {
        this.data = data;
        return (A) this;
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringStatementFluentImpl that = (StringStatementFluentImpl) o;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        return true;

    }


}
