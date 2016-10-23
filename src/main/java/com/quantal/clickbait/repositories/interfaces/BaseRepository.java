package com.quantal.clickbait.repositories.interfaces;

import java.util.List;

/**
 * Created by dman on 18/10/2016.
 */
public interface BaseRepository<TEntity, TKey> {
  TEntity create(TEntity createData);
  TEntity findSingle(TKey id);
  List<TEntity> findAll();
  TEntity updateById(TEntity updateData);
  void deleteById(TKey id);
}
