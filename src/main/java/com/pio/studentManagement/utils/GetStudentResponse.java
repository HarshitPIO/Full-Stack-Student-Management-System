package com.pio.studentManagement.utils;

import com.pio.studentManagement.dto.StudentReceiveDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentResponse {
    private boolean success;
    private String message;
    private List<StudentReceiveDTO> data;
}
