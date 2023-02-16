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

package io.sundr.maven;

import java.lang.reflect.Constructor;
import java.util.Objects;

import org.apache.maven.artifact.versioning.ComparableVersion;
import org.apache.maven.execution.MavenExecutionResult;
import org.apache.maven.lifecycle.internal.LifecycleModuleBuilder;
import org.apache.maven.lifecycle.internal.ProjectIndex;
import org.apache.maven.lifecycle.internal.ReactorBuildStatus;
import org.apache.maven.lifecycle.internal.ReactorContext;

public class ReactorContextFactory {

  private static ComparableVersion VERSION_3_0_0 = new ComparableVersion("3.0.0");
  private static ComparableVersion VERSION_3_3_0 = new ComparableVersion("3.3.0");
  private static ComparableVersion VERSION_3_8_5 = new ComparableVersion("3.8.5");
  private static ComparableVersion VERSION_3_9_0 = new ComparableVersion("3.9.0");

  private final ComparableVersion version;

  public ReactorContextFactory(String version) {
    this.version = new ComparableVersion(Objects.requireNonNull(version, "Maven version must not be null"));
  }

  public ReactorContext create(MavenExecutionResult result, ProjectIndex index, ClassLoader classLoader,
      ReactorBuildStatus status, LifecycleModuleBuilder builder) {
    final ReactorContext context;
    if (version.compareTo(VERSION_3_0_0) < 0) {
      throw new UnsupportedOperationException("ReactorContext is not supported in maven version: " + version);
    } else if (version.compareTo(VERSION_3_3_0) < 0 || version.compareTo(VERSION_3_8_5) == 0) {
      context = create_3_2_x(result, index, classLoader, status);
    } else if (version.compareTo(VERSION_3_9_0) < 0) {
      context = create_3_3_x(result, index, classLoader, status, builder);
    } else {
      context = create_3_9_x(result, index, classLoader, status, builder);
    }

    if (context == null) {
      throw new IllegalStateException("Unable to create ReactorContext");
    }
    return context;
  }

  private static ReactorContext create_3_2_x(MavenExecutionResult result, ProjectIndex index, ClassLoader classLoader,
      ReactorBuildStatus status) {
    try {
      Constructor<ReactorContext> constructor = ReactorContext.class.getDeclaredConstructor(MavenExecutionResult.class,
          ProjectIndex.class, ClassLoader.class, ReactorBuildStatus.class);
      return constructor.newInstance(result, index, classLoader, status);
    } catch (NoSuchMethodException e) {
      return null;
    } catch (Throwable t) {
      throw new RuntimeException("Could not create ReactorContext.", t);
    }
  }

  private static ReactorContext create_3_3_x(MavenExecutionResult result, ProjectIndex index, ClassLoader classLoader,
      ReactorBuildStatus status, LifecycleModuleBuilder builder) {
    try {
      Constructor<ReactorContext> constructor = (Constructor<ReactorContext>) ReactorContext.class.getDeclaredConstructors()[0];
      return constructor.newInstance(result, index, classLoader, status, Reflections.getMemento(builder));
    } catch (Throwable t) {
      throw new RuntimeException("Could not create ReactorContext.", t);
    }
  }

  private static ReactorContext create_3_9_x(MavenExecutionResult result, ProjectIndex index, ClassLoader classLoader,
      ReactorBuildStatus status, LifecycleModuleBuilder builder) {
    try {
      Constructor<ReactorContext> constructor = (Constructor<ReactorContext>) ReactorContext.class.getDeclaredConstructors()[0];
      return constructor.newInstance(result, index, classLoader, status);
    } catch (Throwable t) {
      throw new RuntimeException("Could not create ReactorContext.", t);
    }
  }

}
