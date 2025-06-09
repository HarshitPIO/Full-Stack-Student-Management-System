package com.pio.studentManagement.controller;

import com.pio.studentManagement.dto.StudentSendDTO;
import com.pio.studentManagement.service.StudentServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequestMapping("/form")
@RestController
public class FormController {
    Logger logger = LoggerFactory.getLogger(FormController.class);
    private final StudentServiceImpl studentServiceImpl;

    public FormController(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    /**
     * Endpoint to handle the submit form request
     * @param studentSendDTO : Object to store the student data
     * @return : Returns the response of data saved successfully
     */
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> saveForm(@Valid @RequestBody StudentSendDTO studentSendDTO) {
        try {
            logger.info("student data received");
            if (studentServiceImpl.saveStudent(studentSendDTO).getMessage() == "Data saved successfully")
            return ResponseEntity.ok(Collections.singletonMap("message", "Data saved successfully"));
            else {
                return ResponseEntity.ok(Collections.singletonMap("message", "Student with this name already exists"));
            }
        } catch (Exception e) {
            logger.error("Error in storing data {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("Error", "Error in storing data"));
        }
    }

    /**
     * Endpoint to handle view list request
     * @return : Returns the list of students
     */
    @GetMapping("/showList")
    public ResponseEntity<List<?>> showList() {
        try {
            return ResponseEntity.ok(studentServiceImpl.getAllStudent().getData());
        } catch (Exception e) {
            logger.error("Error in fetching list from DB. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
