package com.ezboot.api.controller.test;

import com.ezboot.test.entity.Student;
import com.ezboot.test.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author David hua
 * @date 2019-08-03 16:29
 */
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/")
    public void save(Student student) {
        studentService.save(student);
    }

    @ApiOperation(value = "根据ID查询学生对象")
    @GetMapping("/{id}")
    public Student getById(@PathVariable Integer id) {
        return studentService.getById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, Student student) {
        Student existResult = studentService.getById(id);
        BeanUtils.copyProperties(student, existResult);
        studentService.update(existResult);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Student student = studentService.getById(id);
        studentService.delete(student);
    }
}
