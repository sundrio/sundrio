/**
 * Copyright 2015 The original authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.sundr.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import io.sundr.builder.Visitor;
import io.sundr.builder.Visitors;

public class VisitorFilteringTest {

  @Test
  public void testFiltering() {
    Visitor<SelectorBuilder> visitor = Visitors.newVisitor(SelectorBuilder.class, s -> s.addToLabels("foo", "bar"))
        .addRequirement(DeploymentBuilder.class, d -> d.getName().equals("my-deployment"));

    Deployment myDeployment = new DeploymentBuilder()
        .withName("my-deployment")
        .withNewSpec()
        .withNewSelector()
        .endSelector()
        .endSpec()
        .accept(visitor)
        .build();

    assertFalse(myDeployment.getSpec().getSelector().getLabels().isEmpty());
    assertEquals("bar", myDeployment.getSpec().getSelector().getLabels().get("foo"));

    Deployment otherDeployment = new DeploymentBuilder()
        .withName("other-deployment")
        .withNewSpec()
        .withNewSelector()
        .endSelector()
        .endSpec()
        .accept(visitor)
        .build();

    assertNull(otherDeployment.getSpec().getSelector().getLabels());

    Service myService = new ServiceBuilder()
        .withName("my-service")
        .withNewSpec()
        .withNewSelector()
        .endSelector()
        .endSpec()
        .accept(visitor)
        .build();

    assertNull(myService.getSpec().getSelector().getLabels());
  }
}
