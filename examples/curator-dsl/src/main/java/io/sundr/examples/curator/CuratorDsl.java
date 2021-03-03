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

package io.sundr.examples.curator;

import io.sundr.dsl.annotations.Any;
import io.sundr.dsl.annotations.Dsl;
import io.sundr.dsl.annotations.EntryPoint;
import io.sundr.dsl.annotations.InterfaceName;
import io.sundr.dsl.annotations.None;
import io.sundr.dsl.annotations.Terminal;

@Dsl
@InterfaceName("CuratorFramework")
public interface CuratorDsl {

  @EntryPoint
  void getData();

  @EntryPoint
  void setData();

  @EntryPoint
  @InterfaceName("ReConfigBuilder")
  void reconfig();

  @EntryPoint
  @InterfaceName("GetConfigBuilder")
  void config();

  @InterfaceName("Leavable")
  @Any(methods = "reconfig")
  @None(methods = "withMembers")
  void leaving(String... server);

  @InterfaceName("Joinable")
  @Any(methods = "reconfig")
  @None(methods = "withMembers")
  void joining(String... server);

  @InterfaceName("Memberable")
  @Any(methods = "reconfig")
  @None(methods = { "joining", "leaving" })
  void withMembers(String... server);

  @InterfaceName("Backgroundable")
  void inBackground(boolean flag);

  @InterfaceName("Backgroundable")
  void inBackground();

  @InterfaceName("Backgroundable")
  void inBackground(Object context);

  @InterfaceName("Watchable")
  void watched();

  @Any(methods = { "getData", "setData" })
  @InterfaceName("Statable")
  void storingStatIn(String stat);

  @Any(methods = "config")
  @InterfaceName("Configurable")
  void fromConfig(Long config);

  @Terminal
  @InterfaceName("Ensemblable")
  @Any(methods = { "reconfig", "config" })
  byte[] forEnsemble();

  @Terminal
  @InterfaceName("Pathable")
  @Any(methods = { "getData", "setData" })
  String forPath(String path);

  @Terminal
  @InterfaceName("PathAndBytesable")
  @Any(methods = { "getData", "setData" })
  void forPath(String path, byte[] bytes);
}
