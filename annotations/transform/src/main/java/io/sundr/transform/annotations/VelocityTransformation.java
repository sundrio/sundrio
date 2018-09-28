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

package io.sundr.transform.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation, that marks that we want to transform the annotated class using a Velocity template.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface VelocityTransformation {

    /**
     * The name of a classpath resource containing the template.
     * @return The name of the template.
     */
    String value();


    /**
     * The relative path of the file that will be generated.
     * When this option is omitted the output file is determined by the fully qualified name of the generated class.
     * Apparently, the option is required for resources (where fqcn is not available).
     * @return
     */
    String outputPath() default "";


    /**
     * Flag to mark that the the specified template will gather all annotated resources, instead of being applied individually to every single one of them.
     * When gather is used the model feed to the template will be a map FQCN -> TypeDef (where TypeDef is the representation of the class).
     * @return
     */
    boolean gather() default false;
}