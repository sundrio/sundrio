/*
 *      Copyright 2017 The original authors.
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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Pojo {

    /**
     * Indicates that the generated pojo will be mutable or not.
     * If not mutable all properties will be final and no default constructor will be provided.
     * @return true if mutable, false otherwise.t
     */
    boolean mutable() default false;

    /**
     * The class name of the generated Pojo.
     * @return The class name, or empty (default) if the name is to be the implicitly specified.
     */
    String name() default "";

    /**
     * The prefix to add to the annotated class in order to create the pojo name.
     * @return  The prefix.
     */
    String prefix() default "";

    /**
     * The suffix to add to the annotated class in order to create the pojo name.
     * @return  The suffix.
     */
    String suffix() default "";


    /**
     * The relative path the pojo will be generated.
     * @return The relative path.
     */
    String relativePath() default "";

    /**
     * The super class to add to the generated pojo.
     * @return the super class.
     */
    Class superClass() default Object.class;

    /**
     * An array of interface to add to the Pojo.
     * @return  The interface array.
     */
    Class[] interfaces() default {};


    /**
     * Flag to include static builder method in the generated pojo.
     * @return  True, if static builder should be included false, otherwise.
     */
    boolean withStaticBuilderMethod() default true;


    /**
     * Flag to include static mapping methods in the generated pojo.
     * The adapter just maps the source interface/annotation to the generated pojo.
     * @return  True, if static adapter should be included false, otherwise.
     */
    boolean withStaticAdapterMethod() default true;


    /**
     * Flag to incluce mapping methods from map instances.
     * @return True, if static adapter should be included, false, otherwise.
     */
    boolean withStaticMapAdapterMethod() default false;


    /**
     * Adapter configuration
     * @return  The adapter configuration.
     */
    Adapter[] adapter() default {};
}
