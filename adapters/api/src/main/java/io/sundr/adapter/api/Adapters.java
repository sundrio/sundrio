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

import io.sundr.model.TypeDef;

public class Adapters {

  public static <T> TypeDef adapt(T input, AdapterContext<T> ctx) {
    if (input == null) {
      throw new IllegalArgumentException("Adapter.adapt(null) is now allowed!");
    }
    return getAdapter(ctx).map(a -> a.adapt(input))
        .orElseThrow(() -> new IllegalStateException("No adapter found for: " + input.getClass()));
  }

  @SuppressWarnings("unchecked")
  public static <T> Optional<Adapter<T>> getAdapter(AdapterContext<T> ctx) {
    Class<T> type = ctx.getType();
    return StreamSupport.stream(ServiceLoader.load(AdapterFactory.class).spliterator(), false)
        .filter(f -> f.getType().isAssignableFrom(type))
        .map(f -> (Adapter<T>) f.create(ctx))
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

    public <T> TypeDef adapt(T input) {
      return Adapters.adapt(input, ctx);
    }
  }
}
