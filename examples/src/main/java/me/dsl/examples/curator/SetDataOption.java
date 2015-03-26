package me.dsl.examples.curator;

import me.dsl.annotations.Keyword;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
@Keyword
public @interface SetDataOption {
}
