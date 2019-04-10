package io.sundr.it;

import java.util.List;
import io.sundr.builder.annotations.Buildable;

@Buildable
public class Default {

    private final List<String> list;

    public Default(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return this.list;
    }
}