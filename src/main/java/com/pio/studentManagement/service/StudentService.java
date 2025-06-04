package com.pio.studentManagement.service;

import com.pio.studentManagement.dto.StudentSendDTO;

import java.util.List;

public interface StudentService {
    public String saveStudent(StudentSendDTO studentSendDTO);

    public List<?> getAllStudent();
}
