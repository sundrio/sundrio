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

import io.sundr.dsl.annotations.AnnotationTransition;
import io.sundr.dsl.annotations.Dsl;
import io.sundr.dsl.annotations.EntryPoint;
import io.sundr.dsl.annotations.Previous;
import io.sundr.dsl.annotations.TargetName;
import io.sundr.dsl.annotations.Terminal;
import io.sundr.examples.curator.annotations.BackgroundOption;
import io.sundr.examples.curator.annotations.CommonOption;
import io.sundr.examples.curator.annotations.ConfigOption;
import io.sundr.examples.curator.annotations.JoinOption;
import io.sundr.examples.curator.annotations.LeaveOption;
import io.sundr.examples.curator.annotations.WatchOption;
import io.sundr.examples.curator.annotations.WithMembersOption;

@Dsl
@TargetName("CuratorFramework")
public interface CuratorGetDataDsl {

    @EntryPoint
    @AnnotationTransition({CommonOption.class, WatchOption.class, BackgroundOption.class, GetDataOption.class})
    void getData();

    @EntryPoint
    @AnnotationTransition({CommonOption.class, BackgroundOption.class, SetDataOption.class})
    void setData();

    @EntryPoint
    @TargetName("ReConfigBuilder")
    @AnnotationTransition({JoinOption.class, LeaveOption.class, WithMembersOption.class})
    void reconfig();

    @EntryPoint
    @TargetName("GetConfigBuilder")
    @AnnotationTransition(ConfigOption.class)
    void config();

    @TargetName("Leavable")
    @LeaveOption
    @AnnotationTransition(ReconfigOption.class)
    void leaving(String... server);

    @TargetName("Joinable")
    @JoinOption
    @AnnotationTransition(ReconfigOption.class)
    void joining(String... server);

    @TargetName("Memberable")
    @WithMembersOption
    @AnnotationTransition(ReconfigOption.class)
    void withMembers(String... server);
    
    @CommonOption
    @Previous
    @TargetName("Backgroundable")
    void inBackground(boolean flag);

    @TargetName("Backgroundable")
    @BackgroundOption
    @Previous
    void inBackground();

    @TargetName("Backgroundable")
    @BackgroundOption
    @Previous
    void inBackground(Object context);

    @WatchOption
    @Previous
    @TargetName("Watchable")
    void watched();
    
    @Previous
    @ReconfigOption
    @ConfigOption
    @TargetName("Statable")
    void storingStatIn(String stat);


    @Previous
    @ReconfigOption
    @ConfigOption
    @TargetName("Configurable")
    void fromConfig(Long config);
    
    @Terminal
    @ReconfigOption
    @ConfigOption
    @TargetName("Ensemblable")
    byte[] forEnsemble();

    @Terminal
    @GetDataOption
    @TargetName("Pathable")
    String forPath(String path);

    @Terminal
    @SetDataOption
    @TargetName("PathAndBytesable")
    void forPath(String path, byte[] bytes);


}
