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

package io.sundr.swagger.language;

import static org.junit.Assert.*;

import org.junit.Ignore;

@Ignore
public class JavaFluentCodegenTest {

  /*
   * @Test
   * public void simpleModelTest() {
   * final Model model = new ModelImpl()
   * .description("a sample model")
   * .property("id", new LongProperty())
   * .property("name", new StringProperty()
   * .example("Tony"))
   * .property("createdAt", new DateTimeProperty())
   * .required("id")
   * .required("name");
   * final DefaultCodegen codegen = new JavaFluentCodegen();
   * final CodegenModel cm = codegen.fromModel("sample", model);
   * 
   * Assert.assertEquals(cm.name, "sample");
   * Assert.assertEquals(cm.classname, "Sample");
   * Assert.assertEquals(cm.description, "a sample model");
   * Assert.assertEquals(cm.vars.size(), 3);
   * 
   * final List<CodegenProperty> vars = cm.vars;
   * 
   * final CodegenProperty property1 = vars.get(0);
   * Assert.assertEquals(property1.baseName, "id");
   * Assert.assertEquals(property1.getter, "getId");
   * Assert.assertEquals(property1.setter, "setId");
   * Assert.assertEquals(property1.datatype, "Long");
   * Assert.assertEquals(property1.name, "id");
   * Assert.assertEquals(property1.defaultValue, "null");
   * Assert.assertEquals(property1.baseType, "Long");
   * Assert.assertTrue(property1.hasMore);
   * Assert.assertTrue(property1.required);
   * Assert.assertTrue(property1.isNotContainer);
   * 
   * final CodegenProperty property2 = vars.get(1);
   * Assert.assertEquals(property2.baseName, "name");
   * Assert.assertEquals(property2.getter, "getName");
   * Assert.assertEquals(property2.setter, "setName");
   * Assert.assertEquals(property2.datatype, "String");
   * Assert.assertEquals(property2.name, "name");
   * Assert.assertEquals(property2.defaultValue, "null");
   * Assert.assertEquals(property2.baseType, "String");
   * Assert.assertEquals(property2.example, "Tony");
   * Assert.assertTrue(property2.hasMore);
   * Assert.assertTrue(property2.required);
   * Assert.assertTrue(property2.isNotContainer);
   * 
   * final CodegenProperty property3 = vars.get(2);
   * Assert.assertEquals(property3.baseName, "createdAt");
   * Assert.assertEquals(property3.getter, "getCreatedAt");
   * Assert.assertEquals(property3.setter, "setCreatedAt");
   * Assert.assertEquals(property3.datatype, "Date");
   * Assert.assertEquals(property3.name, "createdAt");
   * Assert.assertEquals(property3.defaultValue, "null");
   * Assert.assertEquals(property3.baseType, "Date");
   * Assert.assertFalse(property3.hasMore);
   * Assert.assertFalse(property3.required);
   * Assert.assertTrue(property3.isNotContainer);
   * }
   */
}
