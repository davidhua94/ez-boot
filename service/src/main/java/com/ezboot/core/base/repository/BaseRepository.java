package com.ezboot.core.base.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author David hua
 * @date 2019-11-10 15:43:36
 */
@Repository
public class BaseRepository<T> {

    @Autowired
    private EntityManager entityManager;

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.refresh(entity);
    }

    public <T> T getById(Class<T> clazz, Serializable id) {
        return entityManager.find(clazz, id);
    }

    public void deleteById(Serializable id, Class<T> clazz) {
        T existEntity = getById(clazz, id);
        if (existEntity != null) {
            delete(existEntity);
        }
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }
}
