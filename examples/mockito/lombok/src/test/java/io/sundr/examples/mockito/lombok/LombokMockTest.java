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

package io.sundr.examples.mockito.lombok;

import static io.sundr.examples.mockito.lombok.Mocks.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * Integration test proving the mockito-annotations DSL is generated against Lombok-generated
 * members. Lombok and the MockableProcessor both run on the annotation-processor path of this
 * real Maven module (compile-testing cannot run Lombok). The marker flow keeps the production
 * types plain Lombok classes; {@code MockTargets} lists them via {@code @Mockables}.
 */
public class LombokMockTest {

  @Test
  public void stubsLombokGetters() {
    CloudConfig cfg = CloudConfigMock.mock();

    mock(cfg).when().getZone().thenReturn("eu-west");
    mock(cfg).when().getReplicas().thenReturn(3);
    mock(cfg).when().isEnabled().thenReturn(true);

    assertEquals("eu-west", cfg.getZone());
    assertEquals(3, cfg.getReplicas());
    assertTrue(cfg.isEnabled());
  }

  @Test
  public void stubsAndVerifiesLombokSetter() {
    CloudConfig cfg = CloudConfigMock.mock();

    mock(cfg).when().setZone().withZone("x").doNothing();

    cfg.setZone("x");

    mock(cfg).verify().setZone().withZone("x").called();
  }

  @Test
  public void stubsGetterOnlyValueType() {
    Endpoint endpoint = EndpointMock.mock();

    mock(endpoint).when().getUrl().thenReturn("https://api.internal");
    mock(endpoint).when().getWeight().thenReturn(10);

    assertEquals("https://api.internal", endpoint.getUrl());
    assertEquals(10, endpoint.getWeight());
  }

  @Test
  public void stubsGetterSetterType() {
    ProxySettings proxy = ProxySettingsMock.mock();

    mock(proxy).when().getHost().thenReturn("proxy.local");
    mock(proxy).when().setPort().withPort(8080).doNothing();

    proxy.setPort(8080);

    assertEquals("proxy.local", proxy.getHost());
    mock(proxy).verify().setPort().withPort(8080).called();
  }

  @Test
  public void excludesEqualsHashCodeToString() {
    Set<String> routerMethods = Arrays.stream(CloudConfigMock.Stub.class.getDeclaredMethods())
        .map(Method::getName)
        .collect(Collectors.toSet());

    assertTrue(routerMethods.contains("getZone"), "expected the Lombok getter to be stubbable");
    assertTrue(routerMethods.contains("setZone"), "expected the Lombok setter to be stubbable");
    assertFalse(routerMethods.contains("equals"), "equals must not be exposed as a DSL method");
    assertFalse(routerMethods.contains("hashCode"), "hashCode must not be exposed as a DSL method");
    assertFalse(routerMethods.contains("toString"), "toString must not be exposed as a DSL method");
  }
}
