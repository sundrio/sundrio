
SHELL := /bin/bash

all: build

build:
	mvn clean install -pl :sundr-model-base && \
	mvn clean install -pl :sundr-model-generator && \
	rm -rf builder/src/main/java/io/sundr/model/* && \
	cp -r generator/target/generated-sources/annotations/io/sundr/model/* builder/src/main/java/io/sundr/model/
