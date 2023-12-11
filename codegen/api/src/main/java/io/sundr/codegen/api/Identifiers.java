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

package io.sundr.codegen.api;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.StreamSupport;

import io.sundr.SundrException;

public class Identifiers {

  private static Identifier SCOPE;

  public static <T> Optional<Identifier<T>> findIdentifier(Class<T> type) {
    return StreamSupport.stream(ServiceLoader.load(Identifier.class, Identifier.class.getClassLoader()).spliterator(), false)
        .filter(r -> r.getType().isAssignableFrom(type))
        .map(r -> (Identifier<T>) r)
        .findFirst();
  }

  public static synchronized Identifier getIdentifier() {
    return SCOPE;
  }

  public static <T> WithIdentifier withIdentifier(final Function<T, String> identifier) {
    return withIdentifier(new Identifier<T>() {
      @Override
      public Class<T> getType() {
        return (Class<T>) Class.class;
      }

      @Override
      public Function<T, String> getFunction() {
        return identifier;
      }
    });
  }

  public static WithIdentifier withIdentifier(Identifier identifier) {
    return new WithIdentifier(identifier);
  }

  public static class WithIdentifier {

    public WithIdentifier(Identifier identifier) {
      this.identifier = identifier;
    }

    private final Identifier identifier;

    public synchronized <V> V apply(Function<Identifier, V> function) {
      try {
        SCOPE = identifier;
        return function.apply(identifier);
      } catch (Exception e) {
        throw new SundrException(e);
      } finally {
        SCOPE = null;
      }
    }

    public synchronized <V> V call(Callable<V> callable) {
      try {
        SCOPE = identifier;
        return callable.call();
      } catch (Exception e) {
        throw new SundrException(e);
      } finally {
        SCOPE = null;
      }
    }
  }
}
