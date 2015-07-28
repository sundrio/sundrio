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

import io.sundr.dsl.annotations.All;
import io.sundr.dsl.annotations.Any;
import io.sundr.dsl.annotations.Dsl;
import io.sundr.dsl.annotations.EntryPoint;
import io.sundr.dsl.annotations.MethodName;
import io.sundr.dsl.annotations.InterfaceName;
import io.sundr.dsl.annotations.Terminal;
import io.sundr.examples.kubernetes.annotations.FilterOption;
import io.sundr.examples.kubernetes.annotations.NamedOption;
import io.sundr.examples.kubernetes.annotations.NamespaceOption;
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
    @PodOption
    void pod();

    @EntryPoint
    @ReplicationControllerOption
    void replicationController();

    @EntryPoint
    @ServiceOption
    void service();

    @Terminal
    @MethodName("delete")
    @InterfaceName("PodDeleteable")
    @All({PodOption.class, NamedOption.class, NamespaceOption.class})
    void deletePod();

    @Terminal
    @MethodName("delete")
    @InterfaceName("ServiceDeletable")
    @All({ServiceOption.class, NamedOption.class, NamespaceOption.class})
    void deleteService();

    @Terminal
    @MethodName("delete")
    @InterfaceName("ReplicationControllerDeletable")
    @All({ReplicationControllerOption.class, NamedOption.class, NamespaceOption.class})
    void deleteReplicationController();

    @Terminal
    @MethodName("list")
    @InterfaceName("ServiceListable")
    @All({ServiceOption.class, NamespaceOption.class})
    ServiceList listServices();

    @Terminal
    @MethodName("list")
    @InterfaceName("PodListable")
    @All({PodOption.class, NamespaceOption.class})
    PodList listPods();

    @Terminal
    @MethodName("list")
    @InterfaceName("ReplicationControllerListable")
    @All({ReplicationControllerOption.class, NamespaceOption.class})
    ReplicationControllerList listReplicationControllers();

    @Terminal
    @MethodName("get")
    @InterfaceName("PodGettable")
    @All({PodOption.class, NamedOption.class, NamespaceOption.class})
    Pod getPod();

    @Terminal
    @MethodName("get")
    @InterfaceName("ReplicationControllerGettable")
    @All({ReplicationControllerOption.class, NamedOption.class, NamespaceOption.class})
    ReplicationController getReplicationController();

    @Terminal
    @MethodName("scale")
    @InterfaceName("ReplicationControllerScalable")
    @All({ReplicationControllerOption.class, NamedOption.class, NamespaceOption.class})
    void scale(int replicaCount);

    @Terminal
    @MethodName("get")
    @InterfaceName("ServiceGettable")
    @All({ServiceOption.class, NamedOption.class, NamespaceOption.class})
    Service getService();

    @Terminal
    @MethodName("create")
    @InterfaceName("PodCreator")
    @All({PodOption.class, NamedOption.class, NamespaceOption.class})
    void createPod(Pod pod);

    @Terminal
    @MethodName("create")
    @InterfaceName("ReplicationControllerCreator")
    @All({ReplicationControllerOption.class, NamedOption.class, NamespaceOption.class})
    void createReplicationController(ReplicationController replicationController);

    @Terminal
    @MethodName("create")
    @InterfaceName("ServiceCreator")
    @All({ServiceOption.class, NamedOption.class, NamespaceOption.class})
    void createService(Service service);

    @NamedOption
    @InterfaceName("Nameable")
    void withName(String name);

    //Common Options
    @FilterOption
    @InterfaceName("Labelable")
    void withLabel(String key, String value);

    @NamespaceOption
    @InterfaceName("Namespaceable")
    void inNamespace(String namespace);

}


