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

package io.sundr.examples.mockito;

import static io.sundr.examples.mockito.Stubs.stub;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Demonstrates the generated {@code Stubs} aggregator: one static import covers every
 * mock of the suite, for stubbing and verification alike. Overload resolution picks the
 * right DSL from the mock's declared type.
 */
public class StubsAggregatorTest {

  private final OrchestratorTemplateService templates = OrchestratorTemplateServiceMock.mock();
  private final PaymentGateway payments = PaymentGatewayMock.mock();

  @Test
  public void oneImportCoversTheWholeSuite() {
    stub(templates).when().create().withId("MY_ID").thenReturn("TEMPLATE_ID");
    stub(payments).when().charge().withAmountCents(2500L).thenReturn(42L);

    assertEquals("TEMPLATE_ID",
        templates.create("MY_ID", "name", false, new TemplateSpec("v1"), "owner", Map.of(), List.of()));
    assertEquals(42L, payments.charge("ACC-1", 2500L));

    stub(templates).verify().create().withId("MY_ID").called();
    stub(payments).verify().charge().withAccount("ACC-1").called();
    stub(payments).verify().refund().never();
  }
}
