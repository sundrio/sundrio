package io.sundr.jakarta.templates;

import jakarta.persistence.Entity;

@Entity
public class Target {

  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
