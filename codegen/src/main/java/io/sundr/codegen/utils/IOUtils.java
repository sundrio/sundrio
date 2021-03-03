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

package io.sundr.codegen.utils;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {

  /**
   * Closes multiple {@link Closeable} objects swallowing exceptions.
   * 
   * @param cloasebales The {@link Closeable} objects.
   */
  public static void closeQuietly(Closeable... cloasebales) {
    if (cloasebales != null) {
      for (Closeable c : cloasebales) {
        try {
          if (c != null) {
            c.close();
          }
        } catch (IOException ex) {
          //ignore
        }
      }
    }
  }

}
