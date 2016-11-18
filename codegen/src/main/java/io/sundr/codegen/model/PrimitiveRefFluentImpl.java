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

import java.lang.String;
import java.lang.Object;

public class PrimitiveRefFluentImpl<A extends PrimitiveRefFluent<A>> extends TypeRefFluentImpl<A> implements PrimitiveRefFluent<A> {

    private String name;
    private int dimensions;

    public PrimitiveRefFluentImpl() {
    }

    public PrimitiveRefFluentImpl(PrimitiveRef instance) {
        this.withName(instance.getName());
        this.withDimensions(instance.getDimensions());
        this.withAttributes(instance.getAttributes());
    }

    public String getName() {
        return this.name;
    }

    public A withName(String name) {
        this.name = name;
        return (A) this;
    }

    public int getDimensions() {
        return this.dimensions;
    }

    public A withDimensions(int dimensions) {
        this.dimensions = dimensions;
        return (A) this;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PrimitiveRefFluentImpl that = (PrimitiveRefFluentImpl) o;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (dimensions != that.dimensions) return false;
        return true;
    }


}
