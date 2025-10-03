/*
 *      Copyright 2018 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.builder;

/**
 * Interface for objects that can be "done" or completed.
 * This is typically used in builder patterns to signal that the building process
 * is complete and the final object should be returned.
 *
 * @param <T> the type of object that is produced when done
 */
public interface Doneable<T> {

  /**
   * Completes the building process and returns the final object.
   *
   * @return the completed object
   */
  T done();
}