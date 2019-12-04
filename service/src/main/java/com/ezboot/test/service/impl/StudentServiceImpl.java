package com.ezboot.test.service.impl;

import com.ezboot.test.entity.Student;
import com.ezboot.test.repository.StudentRepository;
import com.ezboot.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author David hua
 * @date 2019-08-03 14:37
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student getById(Integer id) {
        Optional<Student> result = studentRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public void update(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }
}
