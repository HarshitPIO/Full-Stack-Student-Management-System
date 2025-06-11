package com.pio.studentManagement.controller;

import com.pio.studentManagement.dto.StudentSendDTO;
import com.pio.studentManagement.service.StudentServiceImpl;
import com.pio.studentManagement.utils.SaveStudentResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
     *
     * @param dto : Object to store the student data
     * @return : Returns the response of data saved successfully
     */
    @PostMapping(value = "/save")
    public ResponseEntity<?> saveForm(@Valid @RequestBody StudentSendDTO dto) {
        logger.info("Data of {} received", dto.getName());
        SaveStudentResponse result = studentServiceImpl.saveStudent(dto);
        return switch (result.getStatus()) {
            case SUCCESS -> ResponseEntity.ok(Map.of("message", result.getMessage(), "name", dto.getName()));
            case ALREADY_EXISTS ->
                    ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", result.getMessage()));
            default ->
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Unexpected Error Occurred"));
        };
    }

    /**
     * Endpoint to handle view list request
     *
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
