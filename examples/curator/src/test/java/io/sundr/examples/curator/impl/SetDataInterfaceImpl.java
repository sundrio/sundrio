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

import io.sundr.examples.curator.ForPathForPathAndBytesInterface;
import io.sundr.examples.curator.ForPathForPathAndBytesWatchableInterface;
import io.sundr.examples.curator.SetDataInterface;

public class SetDataInterfaceImpl implements SetDataInterface {
    @Override
    public ForPathForPathAndBytesWatchableInterface<String, Void> inBackground(Object context) {
        return null;
    }

    @Override
    public ForPathForPathAndBytesWatchableInterface<String, Void> inBackground() {
        return null;
    }

    @Override
    public ForPathForPathAndBytesWatchableInterface<String, Void> inBackground(boolean flag) {
        return null;
    }

    @Override
    public Void forPath(String path, byte data) {
        return null;
    }

    @Override
    public String forPath(String path) {
        return null;
    }

    @Override
    public ForPathForPathAndBytesInterface<String, Void> watched() {
        return null;
    }


    static {
        new SetDataInterfaceImpl().forPath("somePath");
        new SetDataInterfaceImpl().forPath("somePath", (byte) 0);
        new SetDataInterfaceImpl().inBackground().forPath("somePath", (byte) 0);
        new SetDataInterfaceImpl().forPath("somePath");
        new SetDataInterfaceImpl().watched().forPath("somePath");
        new SetDataInterfaceImpl().inBackground().forPath("somePath", (byte) 9);
        String s = new SetDataInterfaceImpl().inBackground().forPath("somePath");
    }


    
}
