## Sundrio: compile time tooling for generating builder and fluent DSLs.


### Overview

Even though builder is a very common pattern and all modern IDEs are
able to generate them on the fly, they tend to get messy when concepts
like *inheritance* or *nesting* get into the picture.

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

### Eliminating runtime dependencies

In case you need to avoid having any kind of runtime dependency to
this tools interfaces like `Builder` or `Fluent` interfaces, you can
set the `generateBuilderPackage` flag on the `@Buildable` annotation
to `true`. This will result in generating the base interfaces
themselves in the generated source directory, eliminating all runtime
dependencies to this too. The target package can be set with
`builderPackage`. If not given, the default `io.sundr.builder` is
used.

```java
package my.demo

public class Demo {
    @Buildable(generateBuilderPackage=true,builderPackage="my.pkg")
    public Demo(....) {
        //  ...
    }
}
```

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
