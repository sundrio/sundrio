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

package io.sundr;

public class SundrException extends RuntimeException {

  public SundrException() {
    super();
  }

  public SundrException(String message) {
    super(message);
  }

  public SundrException(String message, Throwable cause) {
    super(message, cause);
  }

  public SundrException(Throwable cause) {
    super(cause);
  }

  public RuntimeException launderThrowable(Throwable cause) {
    if (cause instanceof RuntimeException) {
      return (RuntimeException) cause;
    } else if (cause instanceof Error) {
      throw (Error) cause;
    } else {
      throw new SundrException("An error has occurred.", cause);
    }
  }
}
