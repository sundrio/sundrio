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
import io.sundr.dsl.annotations.Exclusive;
import io.sundr.dsl.annotations.InterfaceName;
import io.sundr.dsl.annotations.Terminal;
import io.sundr.examples.curator.annotations.BackgroundOption;
import io.sundr.examples.curator.annotations.ConfigOption;
import io.sundr.examples.curator.annotations.JoinOption;
import io.sundr.examples.curator.annotations.LeaveOption;
import io.sundr.examples.curator.annotations.StatOption;
import io.sundr.examples.curator.annotations.WatchOption;
import io.sundr.examples.curator.annotations.WithMembersOption;

@Dsl
@InterfaceName("CuratorFramework")
public interface CuratorGetDataDsl {

    @EntryPoint
    @GetDataOption
    void getData();

    @EntryPoint
    @SetDataOption
    void setData();

    @EntryPoint
    @InterfaceName("ReConfigBuilder")
    @ReconfigOption
    void reconfig();

    @EntryPoint
    @InterfaceName("GetConfigBuilder")
    @ConfigOption
    void config();

    @InterfaceName("Leavable")
    @LeaveOption
    @Any(ReconfigOption.class)
    @Exclusive({WithMembersOption.class})
    void leaving(String... server);

    @InterfaceName("Joinable")
    @JoinOption
    @Any(ReconfigOption.class)
    @Exclusive(WithMembersOption.class)
    void joining(String... server);

    @InterfaceName("Memberable")
    @WithMembersOption
    @Any(ReconfigOption.class)
    @Exclusive({JoinOption.class, LeaveOption.class})
    void withMembers(String... server);
    
    @BackgroundOption
    @InterfaceName("Backgroundable")
    void inBackground(boolean flag);

    @InterfaceName("Backgroundable")
    @BackgroundOption
    void inBackground();

    @InterfaceName("Backgroundable")
    @BackgroundOption
    void inBackground(Object context);

    @WatchOption
    @InterfaceName("Watchable")
    void watched();

    @StatOption
    @Any({SetDataOption.class, GetDataOption.class})
    @InterfaceName("Statable")
    void storingStatIn(String stat);

    @Any(ConfigOption.class)
    @InterfaceName("Configurable")
    void fromConfig(Long config);
    
    @Terminal
    @InterfaceName("Ensemblable")
    @Any({ReconfigOption.class, ConfigOption.class})
    byte[] forEnsemble();

    @Terminal
    @InterfaceName("Pathable")
    @Any({GetDataOption.class, SetDataOption.class})
    String forPath(String path);

    @Terminal
    @InterfaceName("PathAndBytesable")
    @Any({GetDataOption.class, SetDataOption.class})
    void forPath(String path, byte[] bytes);


}
