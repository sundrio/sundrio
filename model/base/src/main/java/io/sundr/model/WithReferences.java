package io.sundr.model;

import java.util.Collections;
import java.util.Set;

public interface WithReferences {

  default Set<ClassRef> getReferences() {
    return Collections.emptySet();
  }
}
