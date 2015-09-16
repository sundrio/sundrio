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

package io.sundr.maven;

import org.apache.maven.lifecycle.internal.LifecycleModuleBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Utility class to work around class visibility issues due to maven exports.
 */
final class Reflections {

    private Reflections() {
      //Utility class
    }

    /**
     * Read using reflection the SessionScope of the {@link org.apache.maven.lifecycle.internal.LifecycleModuleBuilder}.
     * @param source    The {@link org.apache.maven.lifecycle.internal.LifecycleModuleBuilder}
     * @return          The {@link org.apache.maven.session.scope.internal.SessionScope.Memento}
     * @throws Exception
     */
    static Object getMemento(LifecycleModuleBuilder source) throws Exception {
        Field sessionScopeFiled = source.getClass().getDeclaredField("sessionScope");
        sessionScopeFiled.setAccessible(true);
        Object sessionScope = sessionScopeFiled.get(source);
        Method mementoMethod = sessionScope.getClass().getDeclaredMethod("memento");
        mementoMethod.setAccessible(true);
        return mementoMethod.invoke(sessionScope);
    }

    /**
     * Read any field that matches the specified name.
     * @param obj       The obj to read from.
     * @param names     The var-arg of names.
     * @return          The value of the first field that matches or null if no match is found.
     */
    static String readAnyField(Object obj, String ... names) {
        try {
            for (String name : names) {
                try {
                    Field field = obj.getClass().getDeclaredField(name);
                    field.setAccessible(true);
                    return (String) field.get(obj);
                } catch (NoSuchFieldException e) {
                    //ignore and try next field...
                }
            }
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }
}
