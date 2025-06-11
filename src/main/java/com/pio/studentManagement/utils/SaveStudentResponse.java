package com.pio.studentManagement.utils;

import com.pio.studentManagement.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveStudentResponse {
    private Status status;
    private String message;
}
