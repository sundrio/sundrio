package io.sundr.it;

import java.util.List;
import java.util.Set;
import io.sundr.builder.annotations.Buildable;

@Buildable
public class Default {

    private final List<String> list;
    private final Set<String> set;

  public Default(List<String> list, Set<String> set) {
        this.list = list;
        this.set = set;
    }

    public List<String> getList() {
        return this.list;
    }

    public Set<String> getSet() {
      return set;
    }
}
