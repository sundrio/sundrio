## Sundrio: compile time tooling for generating stuff.

* [![CircleCI](https://circleci.com/gh/sundrio/sundrio/tree/master.svg?style=svg)](https://circleci.com/gh/sundrio/sundrio/tree/master)
* [![Maven Central](https://img.shields.io/maven-central/v/io.sundr/sundr-core.svg?maxAge=2592000)](http://search.maven.org/#search%7Cga%7C1%7Cg%3Aio.sundr%20a%3Asundr-core)

# Overview

Writing things like:

- Nested Builders
- Domain Specific Languages
- and more...

is a great experience the first time, but a real burden from there after.
This project was created to generate the boilerplate on compile time for you.

# Features

- Java compile time generators (annotation processors)
    - [Builder Generator](annotations/builder/readme.md)
    - [DSL Generator](annotations/dsl/readme.md)
    - [Velocity Transformer](annotations/transform/readme.md)
    - [Resourceify](annotations/resourcecify/readme.md)

- Maven tooling generators
    - [Maven Bom Generator](maven-plugin/readme.md)


# Compiling 

The project is meant to be compiled using java 8.
The project internally is using `com.sun:tools` which is found under `$JAVA_HOME/lib/tools.jar` for all java version before 11.
To avoid referencing to that path, which is known to cause issues, its required to install it your maven local repository.

    mvn install:install-file -Dfile=$JAVA_HOME/lib/tools.jar -DgroupId=com.sun -DartifactId=tools -Dversion=8 -Dpackaging=jar

*Note*: To just use this project no action is required as the dependency is only needed to compile sundrio itself.

# Projects using sundrio

- [Fabric8 Kubernetes Client](https://github.com/fabric8io/kubernetes-model)
- [Fabric8 Docker Client](https://github.com/fabric8io/docker-client)
- [Snowdrop Service Catalog Client](https://github.com/snowdrop/service-catalog-java-api)
- [Kafka Operator](https://github.com/strimzi/strimzi-kafka-operator)
- [Ap4k](https://github.com/ap4k/ap4k/)
