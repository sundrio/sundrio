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

import static io.sundr.examples.mockito.Mocks.inOrder;
import static io.sundr.examples.mockito.Mocks.mock;
import static io.sundr.examples.mockito.Mocks.verify;
import static io.sundr.examples.mockito.Mocks.verifyNoInteractions;
import static io.sundr.examples.mockito.Mocks.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

/**
 * Demonstrates the generated {@code Mocks} aggregator: a single static import covers the
 * whole suite for both the fluent DSL ({@code mock(x).when()/.verify()}) and plain Mockito
 * ({@code when(...)}/{@code verify(...)}). Overload resolution picks the right DSL from the
 * mock's declared type.
 */
public class MocksAggregatorTest {

  private final OrchestratorTemplateService templates = OrchestratorTemplateServiceMock.mock();
  private final PaymentGateway payments = PaymentGatewayMock.mock();

  @Test
  public void oneImportCoversTheWholeSuite() {
    mock(templates).when().create().withId("MY_ID").thenReturn("TEMPLATE_ID");
    mock(payments).when().charge().withAmountCents(2500L).thenReturn(42L);

    assertEquals("TEMPLATE_ID",
        templates.create("MY_ID", "name", false, new TemplateSpec("v1"), "owner", Map.of(), List.of()));
    assertEquals(42L, payments.charge("ACC-1", 2500L));

    mock(templates).verify().create().withId("MY_ID").called();
    mock(payments).verify().charge().withAccount("ACC-1").called();
    mock(payments).verify().refund().never();
  }

  @Test
  public void sameImportExposesPlainMockito() {
    when(payments.charge("ACC-9", 100L)).thenReturn(7L);

    assertEquals(7L, payments.charge("ACC-9", 100L));

    verify(payments).charge("ACC-9", 100L);
  }

  @Test
  public void exposesCommonMockitoStaticsThroughTheSameImport() {
    verifyNoInteractions(templates);

    payments.charge("ACC-1", 10L);
    payments.refund("ACC-1", 10L);

    InOrder order = inOrder(payments);
    order.verify(payments).charge("ACC-1", 10L);
    order.verify(payments).refund("ACC-1", 10L);
  }
}
