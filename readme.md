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

- [Builder Generator](annotations/builder/readme.md)
- [DSL Generator](annotations/dsl/readme.md)
- [Velocity Transformer](annotations/transform/readme.md)
- [Maven Bom Generator](maven-plugin/readme.md)


# Projects using sundrio

- [Fabric8 Kubernetes Client](https://github.com/fabric8io/kubernetes-model)
- [Fabric8 Docker Client](https://github.com/fabric8io/docker-client)
- [Snowdrop Service Catalog Client](https://github.com/snowdrop/service-catalog-java-api)
- [Kafka Operator](https://github.com/strimzi/strimzi-kafka-operator)
