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

package io.sundr.adapter.source;

import java.io.File;

import io.sundr.adapter.api.Adapter;
import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.api.AdapterFactory;

public class SourceFileAdapterFactory implements AdapterFactory<File, Object, Object, Object> {

  @Override
  public Adapter<File, Object, Object, Object> create(AdapterContext ctx) {
    return new SourceFileAdapter(ctx);
  }

  @Override
  public Class<File> getTypeAdapterType() {
    return File.class;
  }

  @Override
  public Class<Object> getReferenceAdapterType() {
    return Object.class;
  }

  @Override
  public Class<Object> getMethodAdapterType() {
    return Object.class;
  }

  @Override
  public Class<Object> getPropertyAdapterType() {
    return Object.class;
  }
}
