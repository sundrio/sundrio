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

import io.sundr.dsl.annotations.Dsl;
import io.sundr.dsl.annotations.EntryPoint;
import io.sundr.dsl.annotations.Keyword;
import io.sundr.dsl.annotations.TargetName;
import io.sundr.dsl.annotations.Terminal;

@Dsl
@TargetName("CuratorFramework")
public interface CuratorDsl {

    @EntryPoint
    @GetDataOption
    void getData();

    @EntryPoint
    @SetDataOption
    void setData();

    @EntryPoint
    @DeleteOption
    void delete();

    @EntryPoint
    @ExistsOption
    void exists();

    @EntryPoint
    @CreateOption
    void create();

    @EntryPoint
    @ReconfigOption
    @TargetName("ReconfigBuilder")
    void reconfig();

    @EntryPoint
    @ConfigOption
    @TargetName("GetConfigBuilder")
    void getConfig();

    @CreateOption
    void createParentsIfNeeded();

    @DeleteOption
    void deleteChildrenIfNeeded();

    @Keyword
    @CreateOption
    void withMode(Integer mode);
    

    @Keyword
    @GetDataOption
    @SetDataOption
    @DeleteOption
    @ExistsOption
    @TargetName("Backgroundable")
    void inBackground(boolean flag);

    @TargetName("Leavable")
    @ReconfigOption
    void leaving(String... server);

    @TargetName("Joinable")
    @ReconfigOption
    void joining(String... server);

    @TargetName("Memberable")
    @ReconfigOption
    void withMembers(String... server);

    @TargetName("Configurable")
    @ReconfigOption
    void fromConfig(long config) throws Exception;

    @TargetName("Statable")
    @ConfigOption
    @ExistsOption
    void storingStatIn(String stat);

    @TargetName("Backgroundable")
    @GetDataOption
    @SetDataOption
    @DeleteOption
    @ExistsOption
    @ReconfigOption
    void inBackground();

    @TargetName("Backgroundable")
    @GetDataOption
    @SetDataOption
    @DeleteOption
    @ExistsOption
    @ReconfigOption
    void inBackground(Object context);

    @ConfigOption
    @GetDataOption
    @SetDataOption
    @TargetName("Watchable")
    void watched();

    @Terminal
    @ReconfigOption
    @ConfigOption
    @TargetName("Ensemblable")
    byte[] forEnsemble();

    @Terminal
    @CreateOption
    @GetDataOption
    @DeleteOption
    @SetDataOption
    @ExistsOption
    String forPath(String path);


    @Terminal
    @SetDataOption
    @TargetName("ForPathAndBytesInterface")
    void forPath(String path, byte[] data);

}
