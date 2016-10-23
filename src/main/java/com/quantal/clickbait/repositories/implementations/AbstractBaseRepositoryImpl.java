package com.quantal.clickbait.repositories.implementations;

import com.quantal.clickbait.exceptions.InvalidDataArgument;
import com.quantal.clickbait.repositories.interfaces.BaseRepository;
import net.jodah.typetools.TypeResolver;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by dman on 18/10/2016.
 */
public abstract class AbstractBaseRepositoryImpl<TEntity, TKey> implements BaseRepository<TEntity, TKey> {

  @PersistenceContext
  private EntityManager em;

  //Class _type =  (Class<TEntity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

  private Class<?>[] typeArguments = TypeResolver.resolveRawArguments(AbstractBaseRepositoryImpl.class, getClass());
  private Class<TEntity> persistentClass = (Class<TEntity>) typeArguments[0];
  private Class<TKey> idClass = (Class<TKey>) typeArguments[1];

  @Override
  @Transactional
  public TEntity create(TEntity createData) {

    if (createData == null)
      throw new InvalidDataArgument("Data object cannot be null");
    em.persist(createData);
    em.flush();
    return createData;
  }

  @Override
  @Transactional
  public  TEntity findSingle(TKey id) {
    return em.find(persistentClass,id);
  }

  @Override
  @Transactional
  public TEntity updateById(TEntity updateData) {
   TEntity result =  em.merge(updateData);

    return result;
  }

  @Override
  @Transactional
  public void deleteById(TKey id) {
   TEntity entity = this.findSingle(id);
   em.remove(entity);
  }

  @Override
  public List<TEntity> findAll() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery cq = cb.createQuery(persistentClass);
    Root rootEntry = cq.from(persistentClass);
    CriteriaQuery all = cq.select(rootEntry);
    TypedQuery allQuery = em.createQuery(all);
    return allQuery.getResultList();
  }
}
