package me.dsl.examples;

import me.dsl.annotations.Dsl;
import me.dsl.annotations.EntryPoint;
import me.dsl.annotations.Keyword;
import me.dsl.annotations.Terminal;
import me.dsl.annotations.Transition;

@Dsl
public interface MyDsl {

    @EntryPoint
    @Keyword
    @Transition(any = MyGroup.class)
    void from(String start);

    @MyGroup
    void withSomething();

    @MyGroup
    @Transition(to = {"andAfterSomethingElse"})
    void orSomethingElse();

    @Keyword
    void andAfterSomethingElse();

    @MyGroup
    void orMaybeSomethingDifferent();

    @Terminal
    @Keyword
    String getData();
}
