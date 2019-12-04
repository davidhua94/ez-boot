package com.ezboot.core.base.service.impl;

import com.ezboot.core.base.repository.BaseRepository;
import com.ezboot.core.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author David hua
 * @date 2019-11-10 15:42:35
 */
@Component
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseRepository baseRepository;

    @Override
    public void save(T entity) {
        baseRepository.save(entity);
    }

    @Override
    public void update(T entity) {
        baseRepository.update(entity);
    }

    @Override
    public <T> T getById(Integer id, Class<T> clazz) {
        return (T) baseRepository.getById(clazz, id);
    }

    @Override
    public void delete(Integer id, Class<T> clazz) {
        baseRepository.deleteById(id, clazz);
    }
}
