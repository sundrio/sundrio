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

import io.sundr.dsl.examples.curator.InBackgroundForPathForPathAndBytesInterface;
import io.sundr.dsl.examples.curator.generated.SetDataInterface;

public class SetDataInterfaceImpl implements SetDataInterface {


    static {
        new SetDataInterfaceImpl().forPath("somePath");
        new SetDataInterfaceImpl().forPath("somePath", (byte) 0);
        new SetDataInterfaceImpl().inBackground().forPath("somePath");
        new SetDataInterfaceImpl().forPath("somePath");
        new SetDataInterfaceImpl().watched().forPath("somePath");
    }

    @Override
    public Void forPath(String path, byte data) {
        return null;
    }

    @Override
    public String forPath(String path) {
        return "";
    }

    @Override
    public io.sundr.dsl.examples.curator.ForPathForPathAndBytesInterface<String, Void> inBackground() {
        return null;
    }

    @Override
    public InBackgroundForPathForPathAndBytesInterface<Void, String> watched() {
        return null;
    }
}
