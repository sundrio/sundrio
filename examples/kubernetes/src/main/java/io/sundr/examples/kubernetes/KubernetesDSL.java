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

import io.sundr.dsl.annotations.Any;
import io.sundr.dsl.annotations.Dsl;
import io.sundr.dsl.annotations.EntryPoint;
import io.sundr.dsl.annotations.MethodName;
import io.sundr.dsl.annotations.Previous;
import io.sundr.dsl.annotations.InterfaceName;
import io.sundr.dsl.annotations.Terminal;
import io.sundr.examples.kubernetes.annotations.CommonOption;
import io.sundr.examples.kubernetes.annotations.PodOption;
import io.sundr.examples.kubernetes.annotations.ReplicationControllerOption;
import io.sundr.examples.kubernetes.annotations.ServiceOption;
import io.sundr.examples.kubernetes.domain.Pod;
import io.sundr.examples.kubernetes.domain.PodList;
import io.sundr.examples.kubernetes.domain.ReplicationController;
import io.sundr.examples.kubernetes.domain.ReplicationControllerList;
import io.sundr.examples.kubernetes.domain.Service;
import io.sundr.examples.kubernetes.domain.ServiceList;

@Dsl
@InterfaceName("Kubernetes")
public interface KubernetesDSL {

    @EntryPoint
    @Any({CommonOption.class, PodOption.class})
    void pod();

    @EntryPoint
    @Any({CommonOption.class, ReplicationControllerOption.class})
    void replicationController();

    @EntryPoint
    @Any({CommonOption.class, ServiceOption.class})
    void service();

    @Terminal
    @CommonOption
    void delete();

    @Terminal
    @ServiceOption
    @MethodName("list")
    @InterfaceName("ServiceListable")
    ServiceList listServices();

    @Terminal
    @PodOption
    @MethodName("list")
    @InterfaceName("PodListable")
    PodList listPods();

    @Terminal
    @ReplicationControllerOption
    @MethodName("list")
    @InterfaceName("ReplicationControllerListable")
    ReplicationControllerList listReplicationControllers();

    @Terminal
    @PodOption
    @MethodName("get")
    @InterfaceName("PodGettable")
    Pod getPod();

    @Terminal
    @ReplicationControllerOption
    @MethodName("get")
    @InterfaceName("ReplicationControllerGettable")
    ReplicationController getReplicationController();

    @Terminal
    @ServiceOption
    @MethodName("get")
    @InterfaceName("ServiceGettable")
    Service getService();

    @Terminal
    @PodOption
    @MethodName("create")
    @InterfaceName("PodCreator")
    void createPod(Pod pod);

    @Terminal
    @ReplicationControllerOption
    @MethodName("create")
    @InterfaceName("ReplicationControllerCreator")
    void createReplicationController(ReplicationController replicationController);

    @Terminal
    @ServiceOption
    @MethodName("create")
    @InterfaceName("ServiceCreator")
    void createService(Service service);

    @CommonOption
    @Previous
    void withName(String name);

    //Common Options
    @CommonOption
    @Previous
    void withLabel(String key, String value);

    @CommonOption
    @Previous
    void inNamespace(String namespace);

}


