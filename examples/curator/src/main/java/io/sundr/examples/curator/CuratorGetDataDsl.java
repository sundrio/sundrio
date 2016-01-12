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
import io.sundr.dsl.annotations.Keyword;
import io.sundr.dsl.annotations.None;
import io.sundr.dsl.annotations.Terminal;

@Dsl
@InterfaceName("CuratorFramework")
public interface CuratorGetDataDsl {

    @EntryPoint
    @Keyword("GetDataOption")
    void getData();

    @EntryPoint
    @Keyword("SetDataOption")
    void setData();

    @EntryPoint
    @InterfaceName("ReConfigBuilder")
    @Keyword("ReconfigOption")
    void reconfig();

    @EntryPoint
    @InterfaceName("GetConfigBuilder")
    @Keyword("ReconfigOption")
    void config();

    @InterfaceName("Leavable")
    @Any(keywords = "ReconfigOption")
    @None(keywords = "WithMembersOption")
    @Keyword("LeaveOption")
    void leaving(String... server);

    @InterfaceName("Joinable")
    @Keyword("JoinOption")
    @Any(keywords = "ReconfigOption")
    @None(keywords = "WithMembersOption")
    void joining(String... server);

    @InterfaceName("Memberable")
    @Keyword("WithMembersOption")
    @Any(keywords = "ReconfigOption")
    @None(keywords = {"JoinOption", "LeaveOption"})
    void withMembers(String... server);
    
    @Keyword("BackgroundOption")
    @InterfaceName("Backgroundable")
    void inBackground(boolean flag);

    @InterfaceName("Backgroundable")
    @Keyword("BackgroundOption")
    void inBackground();

    @InterfaceName("Backgroundable")
    @Keyword("BackgroundOption")
    void inBackground(Object context);

    @Keyword("WatchOption")
    @InterfaceName("Watchable")
    void watched();

    @Keyword("StatOption")
    @Any(keywords = {"SetDataOption", "GetDataOption"})
    @InterfaceName("Statable")
    void storingStatIn(String stat);

    @Any(keywords = "ConfigOption")
    @InterfaceName("Configurable")
    void fromConfig(Long config);
    
    @Terminal
    @InterfaceName("Ensemblable")
    @Any(keywords = {"ReconfigOption", "ConfigOption"})
    byte[] forEnsemble();

    @Terminal
    @InterfaceName("Pathable")
    @Any(keywords = {"GetDataOption", "SetDataOption"})
    String forPath(String path);

    @Terminal
    @InterfaceName("PathAndBytesable")
    @Any(keywords = {"GetDataOption", "SetDataOption"})
    void forPath(String path, byte[] bytes);
}
