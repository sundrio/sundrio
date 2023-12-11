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

package io.sundr.model;

import java.util.List;
import java.util.stream.Collectors;

public interface Commentable extends Node {

  /**
   * Get the list of comments.
   *
   * @return a {@link List} of comments.
   */
  List<String> getComments();

  /**
   * Render the comments.
   *
   * @return the rendered comments as a string.
   */
  default String renderComments() {
    StringBuilder sb = new StringBuilder();
    if (getComments() != null && !getComments().isEmpty()) {
      sb.append(NEWLINE);
      sb.append(OC).append(NEWLINE);
      sb.append(
          getComments().stream().map(line -> CLP + line + NEWLINE).collect(Collectors.joining()));
      sb.append(CC).append(NEWLINE);
    }
    return sb.toString();
  }
}
