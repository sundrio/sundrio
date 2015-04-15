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

package io.sundr.examples.shapes;

import org.junit.Assert;
import org.junit.Test;

public class ShapesTest {

    @Test
    public void testCircleBuilder() {
        Circle circle = new CircleBuilder().withX(0).withY(0).withRadius(10).build();
        Assert.assertEquals(0, circle.getX());
        Assert.assertEquals(0, circle.getY());
        Assert.assertEquals(10, circle.getRadius());

    }

    @Test
    public void testCircleEdit() {
        Circle circle = new CircleBuilder().withX(0).withY(0).withRadius(10).build();
        Assert.assertEquals(0, circle.getX());
        Assert.assertEquals(0, circle.getY());
        Assert.assertEquals(10, circle.getRadius());
        Circle circle2 = new CircleBuilder(circle).build();
        Assert.assertEquals(0, circle2.getX());
        Assert.assertEquals(0, circle2.getY());
        Assert.assertEquals(10, circle2.getRadius());
        Circle circle3 = new CircleBuilder(circle).withX(1).withY(1).withRadius(20).build();
        Assert.assertEquals(1, circle3.getX());
        Assert.assertEquals(1, circle3.getY());
        Assert.assertEquals(20, circle3.getRadius());
    }

}
