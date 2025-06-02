package com.pio.studentManagement.service;

import com.pio.studentManagement.entity.Student;
import com.pio.studentManagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // method to save the student data in DB
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    //method the get the list of student from DB.
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }
}
