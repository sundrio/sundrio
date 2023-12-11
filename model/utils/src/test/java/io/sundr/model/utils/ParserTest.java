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

package io.sundr.model.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.TypeRef;
import io.sundr.model.VoidRef;
import io.sundr.utils.Strings;

public class ParserTest {

  @Test
  public void testSignatureRegex() {
    String content = Strings.loadResourceQuietly("SimpleClass.java");
    String regex = "\\QnewLine\\E\\s*\\Q(\\E\\s*\\s*\\Q)\\E\\s*";
    Matcher matcher = Pattern.compile(regex).matcher(content);
    assertTrue(matcher.find());
  }

  @Test
  public void shouldParseMethodWithNoArguments() {
    String content = Strings.loadResourceQuietly("SimpleClass.java");
    Method m = Method.newMethod("newLine", (TypeRef) new VoidRef());
    String body = Parsers.parseMethodBody(content, m);
    assertNotNull(body);
    assertEquals("System.out.println();", body.trim());
  }

  @Test
  public void shouldParseMethodWithSingleArgument() {
    String content = Strings.loadResourceQuietly("SimpleClass.java");
    Method m = Method.newMethod("newLine", (TypeRef) new VoidRef(), Property.newProperty(Types.PRIMITIVE_INT_REF, "times"));
    String body = Parsers.parseMethodBody(content, m);
    assertNotNull(body);
    assertTrue(body.contains("for (int i=0; i < times; i++)"));
  }

  @Test
  public void shouldParseMethodWithMultipleArguments() {
    String content = Strings.loadResourceQuietly("SimpleClass.java");
    Method m = Method.newMethod("times", (TypeRef) new VoidRef(), Property.newProperty(Types.PRIMITIVE_INT_REF, "times"),
        Property.newProperty(Types.STRING_REF, "str"));
    String body = Parsers.parseMethodBody(content, m);
    assertNotNull(body);
    assertTrue(body.contains("for (int i=0; i < times; i++)"));
    assertTrue(body.contains("System.out.print(str);"));
  }

  @Test
  public void shouldParseMethodWithMultipleArgumentsAndVarArg() {
    String content = Strings.loadResourceQuietly("SimpleClass.java");
    Method m = Method.newMethod("timesV", (TypeRef) new VoidRef(), true, Property.newProperty(Types.PRIMITIVE_INT_REF, "times"),
        Property.newProperty(Types.STRING_REF, "str"));
    String body = Parsers.parseMethodBody(content, m);
    assertNotNull(body);
    assertTrue(body.contains("for (int i=0; i < times; i++)"));
    assertTrue(body.contains("System.out.print(s)"));
  }

}
