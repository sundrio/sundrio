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

package io.sundr.adapter.api;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;

public class Adapters {

  public static <T, R, P, M> TypeDef adaptType(T input, AdapterContextAware ctx) {
    if (input == null) {
      throw new IllegalArgumentException("Adapter.adapt(null, ctx) is now allowed!");
    }
    return getAdapterForType(input.getClass(), ctx).map(a -> a.adaptType(input))
        .orElseThrow(() -> new IllegalStateException("No adapter found for type: " + input.getClass()));
  }

  public static <T, R, P, M> TypeRef adaptReference(R input, AdapterContextAware ctx) {
    if (input == null) {
      throw new IllegalArgumentException("Adapter.adapt(null, ctx) is now allowed!");
    }
    return getAdapterForReference(input.getClass(), ctx).map(a -> a.adaptReference(input))
        .orElseThrow(() -> new IllegalStateException("No adapter found for reference: " + input.getClass()));
  }

  public static <T, R, P, M> Property adaptProperty(P input, AdapterContextAware ctx) {
    if (input == null) {
      throw new IllegalArgumentException("Adapter.adapt(null, ctx) is now allowed!");
    }
    return getAdapterForProperty(input.getClass(), ctx).map(a -> a.adaptProperty(input))
        .orElseThrow(() -> new IllegalStateException("No adapter found for property: " + input.getClass()));
  }

  public static <T, R, P, M> Method adaptMethod(M input, AdapterContextAware ctx) {
    if (input == null) {
      throw new IllegalArgumentException("Adapter.adapt(null, ctx) is now allowed!");
    }
    return getAdapterForMethod(input.getClass(), ctx).map(a -> a.adaptMethod(input))
        .orElseThrow(() -> new IllegalStateException("No adapter found for method: " + input.getClass()));
  }

  @SuppressWarnings("unchecked")
  public static <T, R, P, M> Optional<Adapter<T, R, P, M>> getAdapterForType(Class type, AdapterContextAware ctx) {
    return StreamSupport.stream(ServiceLoader.load(AdapterFactory.class, Adapter.class.getClassLoader()).spliterator(), false)
        .filter(f -> f.getTypeAdapterType() != null)
        .filter(f -> f.getTypeAdapterType().isAssignableFrom(type))
        .map(f -> (Adapter<T, R, P, M>) f.create(ctx.getAdapterContext()))
        .findFirst();
  }

  @SuppressWarnings("unchecked")
  public static <T, R, P, M> Optional<Adapter<T, R, P, M>> getAdapterForReference(Class type, AdapterContextAware ctx) {
    return StreamSupport.stream(ServiceLoader.load(AdapterFactory.class, Adapter.class.getClassLoader()).spliterator(), false)
        .filter(f -> f.getReferenceAdapterType() != null)
        .filter(f -> f.getReferenceAdapterType().isAssignableFrom(type))
        .map(f -> (Adapter<T, R, P, M>) f.create(ctx.getAdapterContext()))
        .findFirst();
  }

  @SuppressWarnings("unchecked")
  public static <T, R, P, M> Optional<Adapter<T, R, P, M>> getAdapterForProperty(Class type, AdapterContextAware ctx) {
    return StreamSupport.stream(ServiceLoader.load(AdapterFactory.class, Adapter.class.getClassLoader()).spliterator(), false)
        .filter(f -> f.getPropertyAdapterType() != null)
        .filter(f -> f.getPropertyAdapterType().isAssignableFrom(type))
        .map(f -> (Adapter<T, R, P, M>) f.create(ctx.getAdapterContext()))
        .findFirst();
  }

  @SuppressWarnings("unchecked")
  public static <T, R, P, M> Optional<Adapter<T, R, P, M>> getAdapterForMethod(Class type, AdapterContextAware ctx) {
    return StreamSupport.stream(ServiceLoader.load(AdapterFactory.class, Adapter.class.getClassLoader()).spliterator(), false)
        .filter(f -> f.getMethodAdapterType() != null)
        .filter(f -> f.getMethodAdapterType().isAssignableFrom(type))
        .map(f -> (Adapter<T, R, P, M>) f.create(ctx.getAdapterContext()))
        .findFirst();
  }

  public static WithContext withContext(AdapterContext ctx) {
    return new WithContext(ctx);
  }

  public static class WithContext {

    private final AdapterContext ctx;

    public WithContext(AdapterContext ctx) {
      this.ctx = ctx;
    }

    public <T> TypeDef adaptType(T input) {
      return Adapters.adaptType(input, ctx);
    }

    public <R> TypeRef adaptReference(R input) {
      return Adapters.adaptReference(input, ctx);
    }

    public <P> Property adaptProperty(P input) {
      return Adapters.adaptProperty(input, ctx);
    }

    public <M> Method adaptMethod(M input) {
      return Adapters.adaptMethod(input, ctx);
    }

  }
}
