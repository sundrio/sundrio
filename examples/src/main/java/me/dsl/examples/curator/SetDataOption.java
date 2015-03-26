package me.dsl.examples.curator;

import me.dsl.annotations.Keyword;
import me.dsl.annotations.Transition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
@Keyword
@Transition(any = {SetDataOption.class})
public @interface SetDataOption {
}
