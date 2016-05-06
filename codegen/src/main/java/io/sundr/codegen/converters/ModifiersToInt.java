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

package io.sundr.codegen.converters;

import io.sundr.Function;

import javax.lang.model.element.Modifier;
import java.util.Collection;

public class ModifiersToInt implements Function<Collection<Modifier>, Integer> {

    public Integer apply(Collection<Modifier> modifiers) {
        int result = 0;

        for (Modifier m : modifiers) {
            switch (m) {
                case ABSTRACT:
                    result = result | java.lang.reflect.Modifier.ABSTRACT;
                    break;
                case FINAL:
                    result = result | java.lang.reflect.Modifier.FINAL;
                    break;
                case NATIVE:
                    result = result | java.lang.reflect.Modifier.NATIVE;
                    break;
                case PRIVATE:
                    result = result | java.lang.reflect.Modifier.PRIVATE;
                    break;
                case PROTECTED:
                    result = result | java.lang.reflect.Modifier.PROTECTED;
                    break;
                case PUBLIC:
                    result = result | java.lang.reflect.Modifier.PUBLIC;
                    break;
                case STATIC:
                    result = result | java.lang.reflect.Modifier.STATIC;
                    break;
                case SYNCHRONIZED:
                    result = result | java.lang.reflect.Modifier.SYNCHRONIZED;
                    break;
                case TRANSIENT:
                    result = result | java.lang.reflect.Modifier.TRANSIENT;
                    break;
            }
        }

        return result;
    }
}
