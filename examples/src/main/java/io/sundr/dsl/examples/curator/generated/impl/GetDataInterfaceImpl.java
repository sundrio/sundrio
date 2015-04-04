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

package io.sundr.dsl.examples.curator.generated.impl;

import io.sundr.dsl.examples.curator.generated.ForPathInterface;
import io.sundr.dsl.examples.curator.generated.GetDataInterface;
import io.sundr.dsl.examples.curator.generated.InBackgroundForPathInterface;

public class GetDataInterfaceImpl implements GetDataInterface {
    

    static {
        new GetDataInterfaceImpl().watched().forPath("somePath");
        new GetDataInterfaceImpl().inBackground().forPath("somePath");
        new GetDataInterfaceImpl().forPath("somePath");
    }

    @Override
    public String forPath(String path) {
        return null;
    }

    @Override
    public ForPathInterface<String> inBackground() {
        return null;
    }

    @Override
    public InBackgroundForPathInterface<String> watched() {
        return null;
    }
}
