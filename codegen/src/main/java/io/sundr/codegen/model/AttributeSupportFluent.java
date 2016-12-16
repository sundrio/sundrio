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

import io.sundr.builder.Fluent;

public interface AttributeSupportFluent<A extends AttributeSupportFluent<A>> extends Fluent<A>{


    public A addToAttributes(AttributeKey key, Object value);
    public A addToAttributes(Map<AttributeKey, Object> map);
    public A removeFromAttributes(AttributeKey key);
    public A removeFromAttributes(Map<AttributeKey, Object> map);
    public Map<AttributeKey,Object> getAttributes();
    public A withAttributes(Map<AttributeKey, Object> attributes);
    public Boolean hasAttributes();



}
