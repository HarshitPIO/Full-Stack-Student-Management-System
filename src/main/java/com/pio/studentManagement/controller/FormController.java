package com.pio.studentManagement.controller;

import com.pio.studentManagement.entity.Student;
import com.pio.studentManagement.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FormController {
    private final StudentService studentService;

    public FormController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/save-form", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveForm(@RequestBody Student student) {
        studentService.saveStudent(student);
        return "Student registered Successfully";
    }

    @GetMapping("/showList")
    public List<Student> showList() {
        return studentService.getAllStudent();
    }
}
