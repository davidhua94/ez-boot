package com.ezboot.core.base.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * @author David hua
 * @date 2019-11-10 15:43:36
 */
@Repository
public class BaseRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional(rollbackFor = Exception.class)
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(T entity) {
        entityManager.merge(entity);
    }

    public <T> T getById(Class<T> clazz, Serializable id) {
        return entityManager.find(clazz, id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Serializable id, Class<T> clazz) {
        T existEntity = getById(clazz, id);
        if (existEntity != null) {
            delete(existEntity);
        }
    }
    private void delete(T entity) {
        entityManager.remove(entity);
    }
}
