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

import io.sundr.codegen.annotations.AnnotationSelector;
import io.sundr.codegen.annotations.PackageSelector;
import io.sundr.codegen.annotations.ResourceSelector;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface VelocityTransformations {

    /**
     * A list of transformations to apply.
     * @return
     */
    VelocityTransformation[] value();

    /**
     * A list of packages for selecting transformation target.
     * Classes that match the package and regex will be selected.
     * @return
     */
    PackageSelector[] packages() default {};

    /**
     * A list of annotations to use for selecting transformation target.
     * Classes annotated with the annotations will be selected.
     * Note: This only works for local classes and NOT for dependencies.
     * If you need to transform 3rd party classes prefer the resource selector.
     * You can also specify the package selector if you are specify the package explicitly.
     * @return
     */
    AnnotationSelector[] annotations() default {};

    /**
     * A list of resources to use for selecting transformation target.
     * These resources are expected to contain one FQCN per line.
     * @return
     */
    ResourceSelector[] resources() default {};

}