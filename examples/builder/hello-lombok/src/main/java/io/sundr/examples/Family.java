package io.sundr.examples;

import java.util.List;

import io.sundr.builder.annotations.Buildable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Buildable
public class Family {

  Person father;

  Person mother;

  List<Person> children;

}
