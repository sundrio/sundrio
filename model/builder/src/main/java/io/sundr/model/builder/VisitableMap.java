package io.sundr.model.builder;

import java.lang.Iterable;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class VisitableMap extends HashMap<String, List<Visitable>> implements Iterable<Visitable> {

  @Override
  public List<Visitable> get(Object key) {
    if (!containsKey(key)) {
      put(String.valueOf(key), new ArrayList());
    }
    return super.get(key);
  }

  public List<Visitable> aggregate() {
    ArrayList<Visitable> all = new ArrayList();
    for (Collection<Visitable> list : this.values()) {
      all.addAll(list);
    }
    return all;
  }

  @Override
  public Iterator<Visitable> iterator() {
    return aggregate().iterator();
  }

  @Override
  public void forEach(Consumer<? super Visitable> action) {
    aggregate().forEach(action);
  }

  @Override
  public Spliterator<Visitable> spliterator() {
    return aggregate().spliterator();
  }

}
