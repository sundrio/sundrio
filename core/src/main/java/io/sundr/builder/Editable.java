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

package io.sundr.builder;

/**
 * An interface that describes an Editable object.
 * Editable objects are objects that can by edited by obtaining
 * a new instance of the appropriate {@link Builder} feed with the information encapsulated
 * by the current instance.
 * @param <T>  The object to edit.
 */
public interface Editable<T> {

    T edit();
}
