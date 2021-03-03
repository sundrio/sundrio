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

import io.sundr.examples.curator.GetDataInterface;
import io.sundr.examples.curator.PathableAndBytesableInterface;
import io.sundr.examples.curator.StatablePathablePathAndBytesableInterface;
import io.sundr.examples.curator.WatchableStatablePathablePathAndBytesableInterface;

public class GetDataInterfaceImpl implements GetDataInterface,
    WatchableStatablePathablePathAndBytesableInterface<String, Void>,
    StatablePathablePathAndBytesableInterface<String, Void>,
    PathableAndBytesableInterface<String, Void> {

  static {
    new GetDataInterfaceImpl().watched().forPath("somePath");
    new GetDataInterfaceImpl().inBackground().forPath("somePath");
    new GetDataInterfaceImpl().forPath("somePath");
    new GetDataInterfaceImpl().inBackground().forPath("somePath");
    new GetDataInterfaceImpl().inBackground().forPath("somePath");
  }

  @Override
  public String forPath(String path) {
    return "";
  }

  @Override
  public Void forPath(String path, byte[] bytes) {
    return null;
  }

  @Override
  public PathableAndBytesableInterface<String, Void> storingStatIn(String stat) {
    return this;
  }

  @Override
  public WatchableStatablePathablePathAndBytesableInterface<String, Void> inBackground(boolean flag) {
    return this;
  }

  @Override
  public WatchableStatablePathablePathAndBytesableInterface<String, Void> inBackground() {
    return this;
  }

  @Override
  public WatchableStatablePathablePathAndBytesableInterface<String, Void> inBackground(Object context) {
    return this;
  }

  @Override
  public StatablePathablePathAndBytesableInterface<String, Void> watched() {
    return this;
  }
}
