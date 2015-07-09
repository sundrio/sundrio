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

package io.sundr.examples.kubernetes;

import io.sundr.dsl.annotations.AnnotationTransition;
import io.sundr.dsl.annotations.Dsl;
import io.sundr.dsl.annotations.EntryPoint;
import io.sundr.dsl.annotations.Previous;
import io.sundr.dsl.annotations.TargetName;
import io.sundr.dsl.annotations.Terminal;
import io.sundr.examples.kubernetes.annotations.CommonOption;
import io.sundr.examples.kubernetes.annotations.PodOption;
import io.sundr.examples.kubernetes.annotations.ReplicationControllerOption;
import io.sundr.examples.kubernetes.annotations.ServiceOption;
import io.sundr.examples.kubernetes.domain.PodList;
import io.sundr.examples.kubernetes.domain.ReplicationControllerList;
import io.sundr.examples.kubernetes.domain.ServiceList;

import java.util.List;

@Dsl
@TargetName("Kubernetes")
public interface KubernetesDSL {


    @EntryPoint
    @AnnotationTransition({CommonOption.class, PodOption.class})
    void pod();

    @EntryPoint
    @AnnotationTransition({CommonOption.class, ReplicationControllerOption.class})
    void replicationController();

    @EntryPoint
    @AnnotationTransition({CommonOption.class, ServiceOption.class})
    void service();

    @Terminal
    @CommonOption
    void delete();

    @Terminal
    @ServiceOption
    ServiceList listServices();

    @Terminal
    @PodOption
    PodList listPods();

    @Terminal
    @PodOption
    ReplicationControllerList listReplicationControllers();

    @Terminal
    @CommonOption
    Object get();

    @Terminal
    @CommonOption
    void create();

    //Common Options
    @CommonOption
    @Previous
    void withLabel(String key, String value);

    @CommonOption
    @Previous
    void withName(String name);
}


