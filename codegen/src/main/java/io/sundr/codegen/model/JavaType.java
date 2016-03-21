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

package io.sundr.codegen.model;

import io.sundr.Function;
import io.sundr.codegen.Type;
import io.sundr.codegen.utils.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class JavaType extends AttributeSupport implements Type {

    private final JavaKind kind;
    private final String packageName;
    private final String className;
    private final boolean array;
    private final boolean collection;
    private final boolean concrete;
    private final JavaType defaultImplementation;
    private final JavaType superClass;
    private final Set<JavaType> interfaces;
    private final JavaType[] genericTypes;

    public JavaType(JavaKind kind, String packageName, String className, boolean array, boolean collection, boolean concrete, JavaType defaultImplementation, JavaType superClass, Set<JavaType> interfaces, JavaType[] genericTypes, Map<String, Object> attributes) {
        super(attributes);
        this.kind = kind;
        this.packageName = packageName;
        this.className = className;
        this.array = array;
        this.collection = collection;
        this.concrete = concrete;
        this.defaultImplementation = defaultImplementation;
        this.superClass = superClass;
        this.interfaces = interfaces;
        this.genericTypes = genericTypes;
    }

    /**
     * Returns the fully qualified name of the type.
     * @return
     */
    public String getFullyQualifiedName() {
        if (packageName != null && !packageName.isEmpty()) {
            return getPackageName() + "." + getClassName();
        } else {
            return getClassName();
        }
    }

    /**
     * Returns the simple name of the type.
     * @return
     */
    public String getSimpleName() {
        return getSimpleName("");
    }

    /**
     * Returns the simple name of the type.
     * @return
     */
    public String getSimpleName(String suffix) {
        StringBuilder sb = new StringBuilder();
        sb.append(className);
        sb.append(suffix);
        if (genericTypes.length > 0) {
            sb.append("<");
            sb.append(StringUtils.join(genericTypes, new Function<JavaType, String>() {
                @Override
                public String apply(JavaType item) {
                    if (item.getKind() == JavaKind.GENERIC && item.getSuperClass() != null) {
                        return item.getSimpleName() + " extends " + item.getSuperClass().getSimpleName();
                    } else {
                     return item.getSimpleName();
                    }
                }
            }, ", "));
            sb.append(">");
        }
        if (isArray()) {
            sb.append("[]");
        }
        return sb.toString();
    }

    public boolean isBoolean() {
        return (("boolean".equals(className)) ||
                ("Boolean".equals(className)));
    }

    public JavaKind getKind() {
        return kind;
    }
    @Override
    public String getPackageName() {
        return packageName;
    }

    @Override
    public String getClassName() {
        return className;
    }
    @Override
    public boolean isArray() {
        return array;
    }
    
    @Override
    public boolean isCollection() {
        return collection;
    }

    @Override
    public boolean isConcrete() {
        return concrete;
    }

    @Override
    public JavaType getDefaultImplementation() {
        return defaultImplementation;
    }
    
    @Override
    public JavaType getSuperClass() {
        return superClass;
    }

    public Set<JavaType> getInterfaces() {
        return interfaces;
    }

    @Override
    public JavaType[] getGenericTypes() {
        return genericTypes;
    }


    public boolean isAssignable(JavaType o) {
        if (this == o || this.equals(o)) {
            return true;
        }

        if (packageName == null && "java.lang".equals(o.packageName) && className.equalsIgnoreCase(o.className)) {
            return true;
        }
        if (o.packageName == null &&  "java.lang".equals(packageName) && className.equalsIgnoreCase(o.className)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JavaType type = (JavaType) o;

        if (packageName != null ? !packageName.equals(type.packageName) : type.packageName != null) return false;
        if (className != null ? !className.equals(type.className) : type.className != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(genericTypes, type.genericTypes);

    }

    @Override
    public int hashCode() {
        int result = packageName != null ? packageName.hashCode() : 0;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (genericTypes != null ? Arrays.hashCode(genericTypes) : 0);
        return result;
    }

    @Override
    public String toString() {
        return className;
    }
}
