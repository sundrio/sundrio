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

package o.sundr.examples.kubernetes;

import io.sundr.examples.kubernetes.Kubernetes;
import io.sundr.examples.kubernetes.domain.Pod;
import io.sundr.examples.kubernetes.domain.PodList;
import io.sundr.examples.kubernetes.domain.ReplicationController;
import io.sundr.examples.kubernetes.domain.ReplicationControllerList;
import io.sundr.examples.kubernetes.domain.Service;
import io.sundr.examples.kubernetes.domain.ServiceList;
import org.junit.Ignore;
import org.junit.Test;

public class KubernetesDslTest {

    private Kubernetes kubernetes = null;


    @Ignore
    @Test
    public void testPodDSL() {
        kubernetes.pod().inNamespace("default").list();
        kubernetes.pod().withNewLabel().withKey("key").withValue("value").inNamespace("default").list();
        kubernetes.pod().withName("name").inNamespace("namespace").list();
        kubernetes.pod().withName("name").inNamespace("namespace").get();
        kubernetes.pod().withName("name").inNamespace("namespace").delete();
        kubernetes.pod().withName("name").inNamespace("namespace").create(new Pod());

        //can't do
        //kubernetes.pod().inNamespace("namespace").create(null);
        //kubernetes.pod().inNamespace("namespace").delete();
    }

    @Ignore
    @Test
    public void testServiceDSL() {
        kubernetes.service().inNamespace("default").list();
        kubernetes.service().withNewLabel().withKey("key").withValue("value").inNamespace("default").list();
        kubernetes.service().withNewLabel()
                .withKey("mykey")
                .withValue("sdasda");
        kubernetes.service().withName("name").inNamespace("namespace").list();
        kubernetes.service().withName("name").inNamespace("namespace").get();
        kubernetes.service().withName("name").inNamespace("namespace").delete();
        kubernetes.service().withName("name").inNamespace("namespace").create(new Service());

        //can't do
        //kubernetes.service().inNamespace("namespace").create(null);
        //kubernetes.service().inNamespace("namespace").delete();
    }

    @Ignore
    @Test
    public void testReplicationControllerDSL() {
        kubernetes.replicationController().inNamespace("default").list();
        kubernetes.replicationController().withNewLabel().withKey("key").withValue("value").inNamespace("default").list();
        kubernetes.replicationController().withName("name").inNamespace("namespace").list();
        kubernetes.replicationController().withName("name").inNamespace("namespace").get();
        kubernetes.replicationController().withName("name").inNamespace("namespace").delete();
        kubernetes.replicationController().withName("name").inNamespace("namespace").create(new ReplicationController());

        //can't do
        //kubernetes.replicationController().inNamespace("namespace").create(null);
        //kubernetes.replicationController().inNamespace("namespace").delete();
    }
}
