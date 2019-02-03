/*
 *      Copyright 2018 The original authors.
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

package io.sundr.builder.annotations;


/**
 * Configuration for generating a adapter.
 * A adapter is a 'function' that maps an object into an other.
 * Example: When generating a Pojo from an interface or annotation, a adapter can be used to convert the source object into the generated pojo.
 */
public @interface Adapter {

    /**
     * The class name of the generated Adapter.
     * @return The class name, or empty (default) if the name is to be the implicitly specified.
     */
    String name() default "";

    /**
     * The prefix to add to the annotated class in order to create the adapter name.
     * @return  The prefix.
     */
    String prefix() default "";

    /**
     * The suffix to add to the annotated class in order to create the adapter name.
     * @return  The suffix.
     */
    String suffix() default "";


    /**
     * The relative path the pojo adapter be generated.
     * @return The relative path.
     */
    String relativePath() default "";


    /**
     * Flag to incluce mapping methods from map instances.
     * @return True, if adapter method should be included, false, otherwise.
     */
    boolean withMapAdapterMethod() default false;

}
