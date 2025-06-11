package com.pio.studentManagement.service;

import com.pio.studentManagement.dto.StudentSendDTO;
import com.pio.studentManagement.utils.GetStudentResponse;
import com.pio.studentManagement.utils.SaveStudentResponse;

import java.util.List;

public interface StudentService {
    public SaveStudentResponse saveStudent(StudentSendDTO studentSendDTO);

    public GetStudentResponse getAllStudent();
}
