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

package io.sundr.codegen;

import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class CodegenContext {

    private static CodegenContext INSTANCE;

    private final Types types;
    private final Elements elements;

    private CodegenContext(Elements elements, Types types) {
        this.types = types;
        this.elements = elements;
    }

    public synchronized static CodegenContext create(Elements elements, Types types) {
        INSTANCE = new CodegenContext(elements, types);
        return INSTANCE;
    }

    public synchronized static CodegenContext getContext() {
        if (INSTANCE == null) {
            throw new IllegalStateException("CodeGenContext has not been created, yet.");
        }
        return INSTANCE;
    }

    public Types getTypes() {
        return types;
    }

    public Elements getElements() {
        return elements;
    }
}
