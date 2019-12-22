package com.ezboot.test.service;


import com.ezboot.test.entity.Student;

/**
 * @author David hua
 * @date 2019-08-03 14:36
 */
public interface StudentService {
    void save(Student student);

    Student getById(Integer id);

    void update(Student student);

    void delete(Student student);

    void testTransaction();
}
