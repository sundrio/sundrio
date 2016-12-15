/*
 *      Copyright 2016 The original authors.
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

package io.sundr.codegen.model;

import java.util.Map;

public class AnnotationRef extends AttributeSupport {

    private final ClassRef classRef;
    private final Map<String, Object> parameters;

    public AnnotationRef(ClassRef classRef, Map<String, Object> parameters, Map<AttributeKey, Object> attributes) {
        super(attributes);
        this.classRef = classRef;
        this.parameters = parameters;
    }

    public ClassRef getClassRef() {
        return classRef;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnnotationRef that = (AnnotationRef) o;

        if (!classRef.equals(that.classRef)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return classRef.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AT).append(classRef.toString());

        if (parameters != null && parameters.size() > 0) {
            sb.append(OP);
            boolean first = true;
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(SPACE).append(COMA);
                }

                sb.append(entry.getKey()).append(SPACE).append(EQ).append(SPACE).append(entry.getValue());

            }
            sb.append(CP);
        }
        return sb.toString();
    }
}
