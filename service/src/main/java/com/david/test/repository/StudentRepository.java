package com.david.test.repository;

import com.david.test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author David hua
 * @date 2019-08-03 14:29
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
