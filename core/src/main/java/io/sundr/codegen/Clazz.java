/*
 * Copyright 2015 The original authors.
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

import java.util.Set;

public interface Clazz<T extends Type, P extends Property<T>> {

    T getType();
    Set<? extends Method<T, P>> getConstructors();
    Set<? extends Method<T, P>> getMethods();
    Set<P> getFields();
    Set<T> getImports();
    Set<? extends Clazz> getNested();
}
