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
import io.sundr.dsl.annotations.Begin;
import io.sundr.dsl.annotations.Dsl;
import io.sundr.dsl.annotations.End;
import io.sundr.dsl.annotations.EntryPoint;
import io.sundr.dsl.annotations.InterfaceName;
import io.sundr.dsl.annotations.Keyword;
import io.sundr.dsl.annotations.MethodName;
import io.sundr.dsl.annotations.Multiple;
import io.sundr.dsl.annotations.None;
import io.sundr.dsl.annotations.Terminal;
import io.sundr.examples.kubernetes.domain.Pod;
import io.sundr.examples.kubernetes.domain.PodList;
import io.sundr.examples.kubernetes.domain.ReplicationController;
import io.sundr.examples.kubernetes.domain.ReplicationControllerList;
import io.sundr.examples.kubernetes.domain.Service;
import io.sundr.examples.kubernetes.domain.ServiceList;

@Dsl
@InterfaceName("Kubernetes")
public interface KubernetesDsl {

    @EntryPoint
    void pod();

    @EntryPoint
    void replicationController();

    @EntryPoint
    void service();

    @Any(keywords = {"pod()", "replicationController()", "service()"})
    void inNamespace(String namespace);

    @Any(keywords = {"pod()", "replicationController()", "service()"})
    void withName(String name);

    @Terminal
    @Any(keywords = {"pod()", "replicationController()", "service()"})
    @All(keywords = {"withName()", "inNamespace()"})
    void delete();

    @Terminal
    @MethodName("list")
    @InterfaceName("ServiceListable")
    @All(keywords = {"service()", "inNamespace()"})
    @None(keywords = "withName()")
    ServiceList listServices();

    @Terminal
    @MethodName("list")
    @InterfaceName("PodListable")
    @All(keywords = {"pod()", "inNamespace()"})
    @None(keywords = "withName()")
    PodList listPods();

    @Terminal
    @MethodName("list")
    @InterfaceName("ReplicationControllerListable")
    @All(keywords = {"replicationController()", "inNamespace()"})
    @None(keywords = "withName()")
    ReplicationControllerList listReplicationControllers();

    @Terminal
    @MethodName("get")
    @InterfaceName("PodGettable")
    @All(keywords = {"pod()", "withName()", "inNamespace()"})
    Pod getPod();

    @Terminal
    @MethodName("get")
    @InterfaceName("ReplicationControllerGettable")
    @All(keywords = {"replicationController()", "withName()", "inNamespace()"})
    ReplicationController getReplicationController();

    @Terminal
    @All(keywords = {"replicationController()", "withName()", "inNamespace()"})
    void scale(int replicaCount);

    @Terminal
    @MethodName("get")
    @InterfaceName("ServiceGettable")
    @All(keywords = {"service()", "withName()", "inNamespace()"})
    Service getService();

    @Terminal
    @MethodName("create")
    @InterfaceName("PodCreator")
    @All(keywords = {"pod()", "withName()", "inNamespace()"})
    void createPod(Pod pod);

    @Terminal
    @MethodName("create")
    @InterfaceName("ReplicationControllerCreator")
    @All(keywords = {"replicationController()", "withName()", "inNamespace()"})
    void createReplicationController(ReplicationController replicationController);

    @Terminal
    @MethodName("create")
    @InterfaceName("ServiceCreator")
    @All(keywords = {"service()", "withName()", "inNamespace()"})
    void createService(Service service);


    //Common Options
    @None(keywords = "withName()")
    @Begin("Label")
    @Multiple
    void withNewLabel();

    @Keyword({"Label", "HasKey"})
    @All(keywords = "Label")
    void withKey(String key);

    @End("Label")
    @All(keywords = {"Label","withKey()"})
    void withValue(String key);

}


