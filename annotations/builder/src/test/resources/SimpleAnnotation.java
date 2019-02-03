/*
 *      Copyright 2019 The original authors.
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

package testpackage;

import io.sundr.builder.annotations.Pojo;

@Pojo
public @interface SimpleAnnotation {

    int radius() default 10;
    int[] coords() default {};
    int[] center() default {1,1};
    String[] labels() default {};
    Tag tag() default @Tag();

}