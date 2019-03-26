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

package io.sundr.codegen.model;

import io.sundr.builder.BaseFluent;

import java.util.LinkedHashMap;
import java.util.Map;

public class AttributeSupportFluentImpl<A extends AttributeSupportFluent<A>> extends BaseFluent<A> implements AttributeSupportFluent<A>{

    private Map<AttributeKey,Object> attributes = new LinkedHashMap<AttributeKey,Object>();

    public AttributeSupportFluentImpl(){
    }
    public AttributeSupportFluentImpl(AttributeSupport instance){
            this.withAttributes(instance.getAttributes()); 
    }

    public A addToAttributes(AttributeKey key,Object value){
            if(this.attributes == null && key != null && value != null) { this.attributes = new LinkedHashMap<AttributeKey,Object>(); }
            if(key != null && value != null) {this.attributes.put(key, value);} return (A)this;
    }

    public A addToAttributes(Map<AttributeKey,Object> map){
            if(this.attributes == null && map != null) { this.attributes = new LinkedHashMap<AttributeKey,Object>(); }
            if(map != null) { this.attributes.putAll(map);} return (A)this;
    }

    public A removeFromAttributes(AttributeKey key){
            if(this.attributes == null) { return (A) this; }
            if(key != null && this.attributes != null) {this.attributes.remove(key);} return (A)this;
    }

    public A removeFromAttributes(Map<AttributeKey,Object> map){
            if(this.attributes == null) { return (A) this; }
            if(map != null) { for(Object key : map.keySet()) {if (this.attributes != null){this.attributes.remove(key);}}} return (A)this;
    }

    public Map<AttributeKey,Object> getAttributes(){
            return this.attributes;
    }

    public A withAttributes(Map<AttributeKey,Object> attributes){
            if (attributes == null) { this.attributes =  new LinkedHashMap<AttributeKey,Object>();} else {this.attributes = new LinkedHashMap<AttributeKey,Object>(attributes);} return (A) this;
    }

    public Boolean hasAttributes(){
            return this.attributes != null;
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AttributeSupportFluentImpl that = (AttributeSupportFluentImpl) o;
            if (attributes != null ? !attributes.equals(that.attributes) :that.attributes != null) return false;
            return true;
    }




}
