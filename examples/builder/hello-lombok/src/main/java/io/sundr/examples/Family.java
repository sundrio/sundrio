package io.sundr.examples;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.List;

import io.sundr.builder.annotations.Buildable;

@Getter
@AllArgsConstructor
@Buildable
public class Family {

  Person father;
  
  Person mother;
 
  List<Person> children;

}
