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

public interface Type {

    /**
     * @return The package of the type.
     */
    String getPackageName();

    /**
     * @return The class name.
     */
    String getClassName();

    /**
     * @return true if type is an array type.
     */
    boolean isArray();

    /**
     * @return true if type is a Collection type.
     */    
    boolean isCollection();

    /**
     * @return true if type is a concrete type.
     */
    boolean isConcrete();

    /**
     * Return the default implementation of the type.
     * @return
     */
    Type getDefaultImplementation();

    /**
     * @return the super class of the type.
     */
    Type getSuperClass();

    /**
     * An array with the generic types.
     * @return
     */
    Type[] getGenericTypes();
}
