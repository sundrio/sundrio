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

import java.util.Collections;
import java.util.List;

public class Source {

    private final List<TypeDef> types;

    public Source() {
        this(Collections.EMPTY_LIST);
    }

    public Source(List<TypeDef> types) {
        this.types = types;
    }

    public List<TypeDef> getTypes() {
        return types;
    }
}
