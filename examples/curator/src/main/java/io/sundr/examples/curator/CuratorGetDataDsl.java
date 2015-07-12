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
import io.sundr.dsl.annotations.Previous;
import io.sundr.dsl.annotations.InterfaceName;
import io.sundr.dsl.annotations.Terminal;
import io.sundr.examples.curator.annotations.BackgroundOption;
import io.sundr.examples.curator.annotations.CommonOption;
import io.sundr.examples.curator.annotations.ConfigOption;
import io.sundr.examples.curator.annotations.JoinOption;
import io.sundr.examples.curator.annotations.LeaveOption;
import io.sundr.examples.curator.annotations.WatchOption;
import io.sundr.examples.curator.annotations.WithMembersOption;

@Dsl
@InterfaceName("CuratorFramework")
public interface CuratorGetDataDsl {

    @EntryPoint
    @Any({CommonOption.class, WatchOption.class, BackgroundOption.class, GetDataOption.class})
    void getData();

    @EntryPoint
    @Any({CommonOption.class, BackgroundOption.class, SetDataOption.class})
    void setData();

    @EntryPoint
    @InterfaceName("ReConfigBuilder")
    @Any({JoinOption.class, LeaveOption.class, WithMembersOption.class})
    void reconfig();

    @EntryPoint
    @InterfaceName("GetConfigBuilder")
    @Any(ConfigOption.class)
    void config();

    @InterfaceName("Leavable")
    @LeaveOption
    @Any(ReconfigOption.class)
    void leaving(String... server);

    @InterfaceName("Joinable")
    @JoinOption
    @Any(ReconfigOption.class)
    void joining(String... server);

    @InterfaceName("Memberable")
    @WithMembersOption
    @Any(ReconfigOption.class)
    void withMembers(String... server);
    
    @CommonOption
    @Previous
    @InterfaceName("Backgroundable")
    void inBackground(boolean flag);

    @InterfaceName("Backgroundable")
    @BackgroundOption
    @Previous
    void inBackground();

    @InterfaceName("Backgroundable")
    @BackgroundOption
    @Previous
    void inBackground(Object context);

    @WatchOption
    @Previous
    @InterfaceName("Watchable")
    void watched();
    
    @Previous
    @ReconfigOption
    @ConfigOption
    @InterfaceName("Statable")
    void storingStatIn(String stat);


    @Previous
    @ReconfigOption
    @ConfigOption
    @InterfaceName("Configurable")
    void fromConfig(Long config);
    
    @Terminal
    @ReconfigOption
    @ConfigOption
    @InterfaceName("Ensemblable")
    byte[] forEnsemble();

    @Terminal
    @GetDataOption
    @InterfaceName("Pathable")
    String forPath(String path);

    @Terminal
    @SetDataOption
    @InterfaceName("PathAndBytesable")
    void forPath(String path, byte[] bytes);


}
