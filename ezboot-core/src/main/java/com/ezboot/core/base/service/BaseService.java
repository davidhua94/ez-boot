package com.ezboot.core.base.service;

/**
 * @author David hua
 * @date 2019-11-10 15:41:19
 *
 */
public interface BaseService<T> {
    void save(T entity);

    void update(T entity);

    <T> T getById(Integer id, Class<T> clazz);

    void delete(Integer id, Class<T> clazz);
}
