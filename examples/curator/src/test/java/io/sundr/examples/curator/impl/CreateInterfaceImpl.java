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

package io.sundr.examples.curator.impl;


import io.sundr.examples.curator.CreateInterface;
import io.sundr.examples.curator.ForPathInterface;
import io.sundr.examples.curator.ForPathWithModeInterface;

public class CreateInterfaceImpl implements CreateInterface {

    static {
        new CreateInterfaceImpl().createParentsIfNeeded().withMode(1).forPath("somePath");
        new CreateInterfaceImpl().withMode(1).forPath("somePath");
        new CreateInterfaceImpl().createParentsIfNeeded().forPath("somePath");
        new CreateInterfaceImpl().forPath("somePath");
    }


    @Override
    public ForPathWithModeInterface<String> createParentsIfNeeded() {
        return null;
    }

    @Override
    public String forPath(String path) {
        return null;
    }

    @Override
    public ForPathInterface<String> withMode(Integer mode) {
        return null;
    }
}
