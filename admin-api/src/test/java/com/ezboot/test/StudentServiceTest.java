package com.ezboot.test;

import com.ezboot.AdminApiApplicationTests;
import com.ezboot.test.service.StudentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author David hua
 * @date 2019-12-22 20:22:50
 */
public class StudentServiceTest extends AdminApiApplicationTests {
    @Autowired
    private StudentService studentService;

    @Test
    public void testSave() {
        studentService.testTransaction();
    }
}
