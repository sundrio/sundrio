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

package io.sundr.builder.annotations;

/**
 * Annotation to mark that a {@link Builder} wrapper should be generated, for inlining the builder into a DSL.
 */
public @interface Inline {

    /**
     * The name of the inline build method.
     * @return
     */
    String value();

    /**
     * @return The prefix of the Inlinable.
     */
    String prefix() default "";


    /**
     * @return The name of the Inline builder.
     */
    String name() default "";

    /**
     * The suffix of the inlinable.
     * @return
     */
    String suffix() default "";

    /**
     * @return The type of the item to build "inline"
     */
    Class type();

    /**
     * @return The return type of the "inline" method.
     */
    Class returnType() default None.class;
}
