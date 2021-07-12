## Sundrio: Code generation toolkit for Java

* ![Build](https://github.com/sundrio/sundrio/actions/workflows/build.yml/badge.svg)
* [![Maven Central](https://img.shields.io/maven-central/v/io.sundr/sundr-core.svg?maxAge=2592000)](http://search.maven.org/#search%7Cga%7C1%7Cg%3Aio.sundr%20a%3Asundr-core)

# Overview

Writing things like:

- Nested Builders
    ```java
        Pod pod = new PodBuilder()
                         .withNewMetadata()
                            .withName("my-pod")
                         .endMetadata()
                         .withNewSpec()
                             .addNewContainer()
                                 .withName("nginx")
                                 .withImage("quay.io/sundrio/nginx")
                                 .withImagePullPolicy("IfNotPresent")
                             .endContainer()
                         .endSpec()
                         .build();
    ```
- Domain Specific Languages
    ```java
        DockerClient client = new DockerClient()
        client.image().build().usingDockerFile("src/main/docker/Dockerfile.jvm")
        client.image().withName("nginx").tag().inRepository("quay.io/sundrio/nginx").force().withTagName("1.0");
        client.image().withName("quay.io/sundrio/nginx").push().withTag("1.0").toRegistry();
        close();
    ```
    
- and pretty much anything useful that requires a lot of boilerplate ...

is a great experience the first time, but a real burden from there after.

Existing tooling is something that works in very strict context, e.g. via annotation processing, maven/gradle tooling etc, but is rarelly portable to an other (are coupled with the context).
For example, some tools work via annotation processing, other works via maven/gradle plugins, other programmatically, but it's quite rare to see tools that can handle all three styles / contexts.

Sundrio, provides an abstract way of representing java code, that allows you to represent, manipulate and generate code, regardless of the context.
In addition, it provides adapters that can be used adapt/convert existing representations to the sundrio model.

On top of this model, it provides tools that perform tasks, like builder generators, dsl generations and more. 

# Features

- Java 
    - [Java code model](model/readme.md)
    - [Adapters](adapters/readme.md)
      - [Annotation processing](adapters/apt/readme.md)
      - [Reflection](adapters/reflection/readme.md)
      - [Github Java Parser](adapters/source/readme.md)
    - Annotation processors
      - [Builder Generator](annotations/builder/readme.md)
      - [DSL Generator](annotations/dsl/readme.md)
      - [Velocity Transformer](annotations/transform/readme.md)
      - [Resourceify](annotations/resourcecify/readme.md)

- Maven 
    - [Maven Bom Generator](maven-plugin/readme.md)


*Note*: Currently, builder generators, dsl generators etc are bound to annotation processing, but we are currently working on decoupling them from apt.

# Code Model 
The java code model is a fluent api that allows you to:
   - create
   - refactor/manipulate
   - render
java code.

To add it to your project:
```xml
        <dependency>
            <groupId>io.sundr</groupId>
            <artifactId>sundr-model</artifactId>
            <version>${project.version}</version>
        </dependency>
```

### A hello world example

This example demonstrate how we can create a `Greeter` interface with a `helloWorld` method and then print it`s code in the system output.

```java
  TypeDef greeter = new TypeDefBuilder()
                        .withKind(Kind.Inteface)
                        .withName("Greeter")
                        .addNewMethod()
                            .withName("helloWorld")
                        .endMethod()
                        .build();
                        
  System.out.println(greeter.render());
```

The output of the code above is expected to be:

```java
   interface Greeter {
      void helloWorld();
   }
```

## Adapters
Having an api to create java source code programmatically is pretty handy at times, however it's more common to manipulate existing code or classes.
So and `Adapter` api is provided and some core adapter implementations for:
 
- Adapting classes via reflection
- Adapting `TypeElement` via annotation processing
- Adapting existing source

### Reflection adapter

The reflection adapter allows you to create `TypeDef` instances, from `Class` instances:

```java
  TypeDef runnable = Adapters.adaptType(Runnable.class, AdapterContext.getContext());
```

To add it to your project:
```xml
        <dependency>
            <groupId>io.sundr</groupId>
            <artifactId>sundr-adapter-reflect</artifactId>
            <version>${project.version}</version>
        </dependency>
```

### Annotation processor adapter

The annotation processor adapter allows you to create `TypeDef` instances, from `TypeElement` instances.
A `TypeElement` is the way annotation processing facilities of the java compiler represent types.

```java
  AptContext aptContext = AptContext.create(processingEnv.getElementUtils(), processingEnv.getTypeUtils());
  TypeDef runnable = Adapters.adaptType(Runnable.class, AdapterContext.getContext());
```

*Note*: An instance of javax.annotation.processing.ProcessingEnvironment (see processingEnv above) is passed to all annotation processors by the compiler.

To add it to your project:
```xml
        <dependency>
            <groupId>io.sundr</groupId>
            <artifactId>sundr-adapter-apt</artifactId>
            <version>${project.version}</version>
        </dependency>
```

### Source adapter

The source adapter allows you to parse java source files into `TypeDef` instances.
The java parser used is under the hood is the [Github Java Parser](https://github.com/javaparser/javaparser).
So, the adapters practically converts `TypeDeclaration` / `CompilationUnit` instances (github java parser) into `TypeDef` ones.

```java
   try (FileInputStream is = new FileInputStream(new File("/path/to/Runnable.java"))) {
    CompilationUnit cu = JavaParser.parser(is);
    TypeDef runnable = Adapters.adaptType(cu, AdapterContext.getContext());
  }
```

A utility to simplify the step above is also provided:

```java
   TypeDef runnable = Adapters.adaptType(new File("/path/to/Runnable.java"), AdapterContext.getContext());
```

To add it to your project:
```xml
        <dependency>
            <groupId>io.sundr</groupId>
            <artifactId>sundr-adapter-source</artifactId>
            <version>${project.version}</version>
        </dependency>
```

The dependency above comes with the [Github Java Parser](https://github.com/javaparser/javaparser) as a transitive dependency.
If you would rather to have that dependency shaded istead:

```xml
        <dependency>
            <groupId>io.sundr</groupId>
            <artifactId>sundr-adapter-source-nodeps</artifactId>
            <version>${project.version}</version>
        </dependency>
```

As all hello worlds, the example is as simple as it get, yet the code model is so rich that allows manipulating:

- superclasses & interfaces
- properties
- methods
- inner classes
- generic parameters

Control is really find grained up to the point of statements, which at the moment are treated as strings. 

## Code manipulation

So far we briefly covered ways for creating `TypeDef` instances that are representing Java code.
Code generation usually requires the manipulation of the code.

As already demonstrated, the code model comes with a rich set of fluent builders that allow manipulation of code. 

### Using the builders

For example let's take the `Runnable` interface (as demonstrated above) and give it a much more interesting name:

```java
  TypeDef runnable = Adapters.adaptType(Runnable.class, AdapterContext.getContext());
  TypeDef forrest = new TypeDefBuilder(runnable).withName("Forrest").build();
  System.out.println(forrest);
  ```
  
The code above should have an output:

```
@FunctionalInterface
public interface Forrest {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see     java.lang.Thread#run()
     */
    public abstract void run();
}
```

#### Using the visitor pattern

Not all code manipulations are as simple as the example above.
Let's consider a more realistic use case, of generating `DTO` class for our plain old java objects.
Let's assume an imaginary Person class:

```java
public class Person extends Entity {
  private final firstName;
  private final lastName;
  
  public Person(String firstName, String lastName) {
     this.firstName = firstName;
     this.lastName = lastName;
  }
  
  public String getFirstName() {
     return this.firstName;
  }
  
  public String getLastName() {
     return this.lastName;
  }

  //More code ...
}
```

We want to generate a `DTO` that

- will have no superclass
- will have no methods
- all fields will be public

Lucky for us, we don't need to traverse all properties one by one, we can just define a visitor for `Property` objects, that will perform the task for us.
So how does this work? Each time we pass a visitor to the `Builder` it will traverse the object graph and if it finds an object that matches the type of the visitor, it will be passed to the Visitors `visit` method.
In our case:

```java
  TypeDef person = Adapters.adaptType(Person.class, AdapterContext.getContext());
  TypeDef dto = new TypeDefBuilder(person)
                       .withName(person.getName() + "DTO")
                       .withMethods(Collections.emptyList())
                       .withConstructors(Collections.emptyList())
                       .withExtendsList(Collections.emptyList())
                       .accept(new TypedVisitor<PropertyBuilder>() {
                             public void visit(PropertyBuilder property) {
                                property.withModifiers(Types.modifiersToInt(Modifier.PUBLIC))
                             }
                        });
  System.out.println(dto);
  ```

The output should be something like:

```java
public class PersonDTO {
   public String firstName;
   public String lastName;
}
```

# Modules

The project also includes so modules that put the code model & adapters into the test. In other words they use code model in order to generate things like:

- Fluent nested hierarchical builders
- Domain specific languages
- Template based code

## [Builders](annotations/builder/readme.md)
## [Domain Specific languages](annotations/dsl/readme.md)
## [Template base code generator](annotations/transform/readme.md)


# Compiling 

The project is meant to be compiled using java 8.
The project internally is using `com.sun:tools` which is found under `$JAVA_HOME/lib/tools.jar` for all java version before 11.
To avoid referencing to that path, which is known to cause issues, its required to install it your maven local repository.

    mvn install:install-file -Dfile=$JAVA_HOME/lib/tools.jar -DgroupId=com.sun -DartifactId=tools -Dversion=8 -Dpackaging=jar

*Note*: To just use this project no action is required as the dependency is only needed to compile sundrio itself.

# Projects using sundrio

- [Fabric8 Kubernetes Client](https://github.com/fabric8io/kubernetes-model)
- [Official Kubernetes Client](https://github.com/kubernetes-client/java)
- [Strimzi Kafka Operator](https://github.com/strimzi/strimzi-kafka-operator)
- [Dekorate](https://github.com/dekorateio/dekorate/)
