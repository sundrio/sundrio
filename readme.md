## Sundrio: Code generation toolkit for Java

* ![Build](https://github.com/sundrio/sundrio/actions/workflows/build.yml/badge.svg)
* [![Maven Central](https://img.shields.io/maven-central/v/io.sundr/sundr-core.svg?maxAge=2592000)](http://search.maven.org/#search%7Cga%7C1%7Cg%3Aio.sundr%20a%3Asundr-core)

# Overview

Writing things like:

- Nested Builders
- Domain Specific Languages
- and more...

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

### Annotation processor adapter

The annotation processor adapter allows you to create `TypeDef` instances, from `TypeElement` instances.
A `TypeElement` is the way annotation processing facilities of the java compiler represent types.

```java
  AptContext aptContext = AptContext.create(processingEnv.getElementUtils(), processingEnv.getTypeUtils());
  TypeDef runnable = Adapters.adaptType(Runnable.class, AdapterContext.getContext());
```

*Note*: An instance of javax.annotation.processing.ProcessingEnvironment (see processingEnv above) is passed to all annotation processors by the compiler.

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

As all hello worlds, the example is as simple as it get, yet the code model is so rich that allows manipulating:

- superclasses & interfaces
- properties
- methods
- inner classes
- generic parameters

Control is really find grained up to the point of statements, which at the moment are treated as strings. 

# Compiling 

The project is meant to be compiled using java 8.
The project internally is using `com.sun:tools` which is found under `$JAVA_HOME/lib/tools.jar` for all java version before 11.
To avoid referencing to that path, which is known to cause issues, its required to install it your maven local repository.

    mvn install:install-file -Dfile=$JAVA_HOME/lib/tools.jar -DgroupId=com.sun -DartifactId=tools -Dversion=8 -Dpackaging=jar

*Note*: To just use this project no action is required as the dependency is only needed to compile sundrio itself.

# Projects using sundrio

- [Fabric8 Kubernetes Client](https://github.com/fabric8io/kubernetes-model)
- [Official Kubernetes Client](https://github.com/kubernetes-client/java)
- [Kafka Operator](https://github.com/strimzi/strimzi-kafka-operator)
- [Dekorate](https://github.com/dekorateio/dekorate/)
