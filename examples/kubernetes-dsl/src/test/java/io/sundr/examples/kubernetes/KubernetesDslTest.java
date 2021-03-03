/*
 * Copyright 2016 The original authors.
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

import org.junit.Ignore;
import org.junit.Test;

import io.sundr.examples.kubernetes.domain.Pod;
import io.sundr.examples.kubernetes.domain.ReplicationController;
import io.sundr.examples.kubernetes.domain.Service;

public class KubernetesDslTest {

  private Kubernetes kubernetes = null;

  @Ignore
  @Test
  public void testPodDSL() {

    kubernetes.pod().inNamespace("default")
        .withNewLabel().withKey("key1").withValue("value1")
        .withNewLabel().withKey("key2").withValue("value2").list();
    kubernetes.pod().inNamespace("namespace").withName("name").get();
    kubernetes.pod().inNamespace("namespace").withName("name").delete();
    kubernetes.pod().inNamespace("namespace").withName("name").create(new Pod());

    //can't do
    //kubernetes.pod().inNamespace("namespace").create(null);
    //kubernetes.pod().inNamespace("namespace").delete();
  }

  @Ignore
  @Test
  public void testServiceDSL() {
    kubernetes.service().inNamespace("default")
        .withNewLabel().withKey("key1").withValue("value1")
        .withNewLabel().withKey("key2").withValue("value2").list();

    kubernetes.service().inNamespace("namespace").withName("name").get();
    kubernetes.service().inNamespace("namespace").withName("name").delete();
    kubernetes.service().inNamespace("namespace").withName("name").create(new Service());

    //can't do
    //kubernetes.service().inNamespace("namespace").create(null);
    //kubernetes.service().inNamespace("namespace").delete();
  }

  @Ignore
  @Test
  public void testReplicationControllerDSL() {
    kubernetes.replicationController().inNamespace("default").list();

    kubernetes.replicationController().inNamespace("default")
        .withNewLabel().withKey("key1").withValue("value1")
        .withNewLabel().withKey("key2").withValue("value2").list();

    kubernetes.replicationController().inNamespace("namespace").withName("name").get();
    kubernetes.replicationController().inNamespace("namespace").withName("name").delete();
    kubernetes.replicationController().inNamespace("namespace").withName("name").create(new ReplicationController());

    //can't do
    //kubernetes.replicationController().inNamespace("namespace").create(null);
    //kubernetes.replicationController().inNamespace("namespace").delete();
  }
}
