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

package my.pkg;

import java.util.Arrays;

public class SimpleClass {

  public void times(int times, String str) {
    for (int i=0; i < times; i++) {
      System.out.print(str);
    }
  }

  public void timesV(int times, String ...str) {
    for (int i=0; i < times; i++) {
      Arrays.stream(str).forEach(s -> System.out.print(s));
      System.out.println();
    }
  }


  public void newLine(int times) {
    for (int i=0; i < times; i++) {
      newLine();
    }
  }

  public void newLine() {
    System.out.println();
  }
}
