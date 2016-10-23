package com.quantal.clickbait.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by dman on 23/10/2016.
 */
@MappedSuperclass
public abstract class BaseNumericKeyEntity<TKey extends Number> {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private TKey id;

  public TKey getId() {
    return id;
  }

  public void setId(TKey id) {
    this.id = id;
  }
}
