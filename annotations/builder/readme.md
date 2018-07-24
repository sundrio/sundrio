## Builder Annotations


### Overview

Even though builder is a very common pattern and all modern IDEs are
able to generate them on the fly, they tend to get messy when concepts
like *inheritance* or *nesting* get into the picture and they are not 
that easy to keep up to date.

This project provides annotation processors the provide the following features:

- Compile time builder generation.
- Separation of the Fluent behavior from the Builder.
- Support for hierarchical and nested builders.
- Support for nesting builders.
- Support for the Visitor Pattern (easily navigating complex structures).
- Support for inlines (builder like objects that expose a custom function).
- Support for Bean Validation (JSR 303)
- Support for JDK8 Optional types.
- Support for generating Pojos and Builders from interfaces.
- Support generating Builders for 3rd party classes.

#### The builder interface
All the generated builders implement the `Builder` interface which looks like:

```java
public interface Builder<T> {
    T build();
}
```

#### The fluent interface

For usability and readability builders are in most cases fluent
interfaces and use method cascading.  This means that all but the
*build* method return the builder instance itself. This project makes
a distinction between the *Builder* and *Fluent* part, by generating
both a `Builder` and a `Fluent` class *(with the `Builder` extending
the `Fluent`)*.

The fluent part implements the following interface.

```java
public interface Fluent<F extends Fluent<F>> {
}
```

#### Hierarchical builder and fluent implementations

Obviously a single class can't implement `Builder`s with different
type parameters and thus it's not possible to have a builder extend an
other build *(unless of course they use the same or generic type
parameters and abuse generics)*.

To keep things as simple as possible and maximize re-usability of the
generated bits, this project allows inheritance between `Fluent`
implementations (which contain all the reusable bits) and then each
builder just extends the corresponding `Fluent` implementation. This
minimizes boilerplate and also allows the use of `Fluent`, outside the
context of the builder ([see below](#builder-inheritance)).

### Generating Builders

On any POJO you can add the `@Buildable` annotation on a single
constructor. On compile time a builder will get generated that will
contain methods for all of the constructor arguments. For example:

```java
@Buildable
public Circle(int x, int y, int radius)
```

This will generate a builder that you can then use:

```java
Circle myCircle = new CircleBuilder().withX(0).withY(0).withRadius(10).build();
```

An other example could be the:

```java
@Buildable
public Square(int x, int y, int size)
```

Which you could use like:

```java
Square mySquare = new SquareBuilder().withX(0).withY(0).withSize(10).build();
```

The builder can also be used to edit existing objects. For example let's
change the size of existing Square:

```java
Square newSquare = new SquareBuilder(existingSquare).withSize(10).build();
```

This is quite handy, especially in cases where the target object are immutable.
An alternative way of doing the above is to use the Editable version of the object
which is also generated (by default).

The Editable version will still be immutable, but will provide an edit()
method which will return a builder for the editing the object. Also all builders, 
will now return the editable version of the object.

```java
EditableSquare mySquare = new SquareBuilder().withX(0).withY(0).withSize(10).build();
EditableSquare newSquare = mySquare.edit().withSize(10).done();
```

### Builder Inheritance

In the examples above, both `Circle` and `Square` are actually
*Shapes*. We could have the class `Shape` as a superclass of `Circle`
and `Square` and move fields x and y there.

```java
@Buildable
public Shape(int x, int y)
```

In this case the `CircleFluent` and `SquareFluent` will extend the
`ShapeFluent` and each builder will extend the corresponding `Fluent`.

As shown below:

             Fluent              Builder
             ------          ----------------
               ^               ^     ^     ^
               |               |     |     |
               |               |     |     |
           ShapeFluent         |     |     |
           -----------         |     |     |
             ^  ^  ^           |     |     |
             |  |  |           |     |     |
             |  |  +-- ShapeBuilder  |     |
             |  |      ------------  |     |
             |  |                    |     |
        +----+  +-------+            |     |
        |               |            |     |
        CircleFluent    SquareFluent |     |
        ------------    ------------ |     |
          ^                ^         |     |
          |                |         |     |
          |         SquareBuilder----+     |
          |                                |
          |                                |
          |  +-----------------------------+
          |  |
        CircleBuilder

### Nested Builders

Sometimes we want to create a builder for an object that contains fields
that we also want to create builders for. 

**Example**: 
Let's add the Circle and Square objects we used above inside a Canvas object
and see how using the CanvasBuilder would look like:

    Canvas canvas = new CanvasBuilder()
                        .withCircle(new CircleBuilder().withRadius(10)
                                                       .withX(0)
                                                       .withY(0)
                                                       .build())
                        .withSquare(new SrqureBuilder().withSize(5)
                                                       .withX(10)
                                                       .withY(10)
                                                       .build()) 
                        build();
                                                                                                             
There is nothing wrong with the snippet above, but it would look much nicer 
like this:

    Canvas canvas = new CanvasBuilder()
                        .withNewCircle().withRadius(10)
                                        .withX(0)
                                        .withY(0)
                                        .and()
                        .withNewSquare().withSize(5)
                                        .withX(10)
                                        .withY(10)
                                        .and() 
                        build();

It's even more handy when we want to edit one of the nesting objects:
                                                            
    Canvas canvas = new CanvasBuilder(oldCanvas)
                        .editCircle()
                            .withRadius(10)
                        .and() 
                        build();                                                            
                                                                                                                                                                                                                                                 
These are examples of what we call nested builders. Nested builders can also 
support hierarchies and Collections. So if Canvas instead a single circle and
a single square had a collection of shapes, the generated CanvasBuilder would be 
used like this:

    Canvas canvas = new CanvasBuilder()
                        .addNewCircleShape().withRadius(10)
                                        .withX(0)
                                        .withY(0)
                                        .and()
                        .addNewSquareShape().withSize(5)
                                        .withX(10)
                                        .withY(10)
                                        .and() 
                        build();                                                                                                                                                                                                                          
                  
This is really handy, because you don't have keep track of which builders 
are available, the structure of your generated builder guides you instead.
                                                                        
But what happens if we want to edit one of the circles now?

When the object structure is getting more complex, the Visitors come to the rescue.

### Visitor Pattern Support

The visitor pattern in general allows to to perform an operation on objects
without having to know how to reach these objects. In the nested builder case
this is really handy as it allows us to edit complex object structure without having 
to `know` and `couple` our code with the structure.
  
In the previous example let's assume that we want to move all circles to the right by 10.
We will have to pass a visitor to the CanvasBuilder that will visit all the nested circle builders
and perform the change.
 
    Canvas canvas = new CanvasBuilder(oldCanvas).accept(new TypedVisitor<CircleBuilder>() {         
            public void visit(CircleBuilder c) {
                c.withX(c.getX() + 10);
            }
    }).build();

Now let's do the same with all objects:

    Canvas canvas = new CanvasBuilder(oldCanvas).accept(new TypedVisitor<ShapeFluent>() {         
            public void visit(ShapeFluent s) {
                s.withX(s.getX() + 10);
            }
    }).build();                          
                          
In the snippet above we choose to visit the ShapeFluent as its the common interface
implemented by all shape builders. 

What happens when we need to visit buildable objects that don't have something in common.
For this case we can just use a plain visitor rather than a typed one:

    Canvas canvas = new CanvasBuilder(oldCanvas).accept(new Visitor() {         
            public void visit(Object o) {
                if (o instanceof NotAShapeBuilder) {
                    // perform some changes
                } else if (o instanceof CircleBuilder) {
                    // do something else ...
                }
            }
    }).build();
          
In cases where we have more complex objects, we may want the visitor to know of the parent 
of the visited objects or even the full path. In this case we can use a PathAwareTypedVisitor.

But let's use an example. Assume that all shapes have a Color field, similar to the java.awt.Color:

    public Color {
        private final int red;
        private final int grey;
        private final int blue;
        
        //Not hard to image code...    
    }
                                                       
And we want to increase the amount of red in all circle shapes in the Canvas.
The challenge here is that we don't want to modify all color builders, but
only those nested under circle objects. Here's how the PathAwareTypedVisitor helps:

    Canvas canvas = new CanvasBuilder(oldCanvas).accept(new PathAwareTypedVisitor<ColorBuilder>() {         
            public void visit(ColorBuilder c) {
                if (getParent() instanceof CircleBuilder) {
                    c.witRed(c.getRed() + 10);
                }
            }
    }).build();

PathAwareTypedVisitor has access to getParent() and getPath() methods, which provide
all the required information about the path of the visitable object.                                                                                                                                                                                                                        

### Integration with Bean Validation

The generated builders can validate the objects before returning them
(if a validation provider is available). To enable this feature just
set the `validationEnabled` flag to `true`:

```java
package my.demo
    
import javax.validation.constraints.NotNull;

public class Demo {
    @NotNull
    private String value;
    
    @Buildable(validationEnabled=true)
    public Demo(....) {
            // ...
    }
}
```

### Editable Builders

By default the objects created by the builder are *editable*. This
means that the created object automatically implement a method
`edit()` which, if called, returns a new builder initialized with the
data of the current object.

Consider for example the `Circle` object then you can use the
following code to easily create a modified clone of it:

```java
Circle circle = new CircleBuilder()
                      .withX(10)
                      .withY(10)
                      .withRadius(100)
                      .build();

// Create a new CircleBuilder with edit(), set props and the build
Circle clone = ((Editable) circle).edit().withRadius(120).build();
```

You can disable this behaviour by using `@Buildable(editableEnabled =
false)` 


### Eliminating runtime dependencies

In case you need to avoid having any kind of runtime dependency to
this tools interfaces like `Builder` or `Fluent` interfaces, you can
set the `generateBuilderPackage` flag on the `@Buildable` annotation
to `true`. This will result in generating the base interfaces
themselves in the generated source directory, eliminating all runtime
dependencies to this too. The target package can be set with
`builderPackage`. If not given, the default `io.sundr.builder` is
used (in this case sundr-core needs to be on the classpath).

```java
package my.demo

public class Demo {
    @Buildable(generateBuilderPackage=true,builderPackage="my.pkg")
    public Demo(....) {
        //  ...
    }
}
```

#### Using a generated builder package from an other artifact.
If you've generated the builder package say in artifact A and you have artifact B depend on A, you can reuse the builder 
package from A, so no need to also generate it inside B.

If this is TOO complicated, just remember that sundrio generated builders need on runtime some classes (BaseFluent, Visitor etc).
These classes can come from `sundr-core` or any other artifact that had them generated. It doesn't matter where these classes come from, 
as long as point sundrio to them (as shown above).

### Generating Builders and Fluents for 3rd party classes

In case you want to generate Builders and Fluents for 3rd party
classes you can use the `@ExternalBuilder` annotation.  This
annotation can be added on top of any Class. Inside the annotation you
can specify the fully qualified names of the types that for which you
want to generate builders.

```java
@ExternalBuilders({"some.ExternalClass", "yet.another.thirdPartyClass"})
public class MyExternalCase {
}
```

### Referencing existing Buildables

We've already mentioned the case, where we have multiple modules generating builders. If these modules depende on each other
it is likely that the builders themselves depend on each other (after all the generated builders are meant to be hierarchical).

Since, builder generation happens via annotation processors and annotation information are not retained inside classes, we do need a way
to let sundrio know about `know buildables` This is done using the `refs` attribute on the `@Buildable` annotation.

For example if our buildable classes depend on a buildable class, say `ObjectMeta` that lives in an other artifact, we can express
that `ObjectMeta` is buildalbe using:

    @Buildable(refs = {
        @BuildableReference(ObjectMeta.class)
    })

### Generating Pojos

When the `@Buildable` annotation is added to an interface the processor will generate a Pojo and then Fleunt and Builder
for the Pojo, as if the Pojo itself was annotated with @Buildable. The Pojo name will be named after the interface and
will be prefix with the literal `Default`.

To select a custom name for the Pojo, you can use the `@Pojo` annotation:

```java
@Pojo(name="MyReact")
@Buildable
public interface Rectangle {
    
}
```

With the @Pojo annotation one can also specify the super class of the generated pojo:

```java
@Pojo(name="MyReact", superClass="AbstractShape")
@Buildable
public interface Rectangle {
    
}
```

in which case the generated class will look like:
```java
public class MyRect extends AbstractShape implements Rectangle {
    
}
```
    
    

