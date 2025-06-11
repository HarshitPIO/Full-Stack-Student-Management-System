package com.pio.studentManagement.service;

import com.pio.studentManagement.dto.StudentReceiveDTO;
import com.pio.studentManagement.dto.StudentSendDTO;
import com.pio.studentManagement.entity.Student;
import com.pio.studentManagement.enums.Status;
import com.pio.studentManagement.repository.StudentRepository;
import com.pio.studentManagement.utils.GetStudentResponse;
import com.pio.studentManagement.utils.SaveStudentResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * This method is used for saving the student data in the database.
     *
     * @param studentSendDTO : This is my DTO used for receiving data from the frontend.
     */
    @Override
    public SaveStudentResponse saveStudent(StudentSendDTO studentSendDTO) {
        Student student = new Student();
        SaveStudentResponse response = new SaveStudentResponse();
        try {
            // This method is used for copying DTO data to entity
            BeanUtils.copyProperties(student, studentSendDTO);
            studentRepository.save(student);
            logger.info("Data saved successfully");
            response.setStatus(Status.SUCCESS);
            response.setMessage("Data saved successfully");
            return response;
        } catch (Exception e) {
            logger.error("Error in saving data: {}", e.getMessage());
            response.setStatus(Status.ALREADY_EXISTS);
            response.setMessage("Data already exist");
            return response;
        }
    }


    /**
     * This method is used to fetch the list of student data from the DB.
     * It is copying the field data of student object to studentReceiveDTO object.
     *
     * @return : It is returning the list of students that are saved in the DB.
     */
    public GetStudentResponse getAllStudent() {
        logger.info("Method of getting list invoked");
        GetStudentResponse response = new GetStudentResponse();
        List<StudentReceiveDTO> dtos = new ArrayList<>();
        try {
            dtos = studentRepository.findAll().stream().map(student -> {
                StudentReceiveDTO dto = new StudentReceiveDTO();
                try {
                    BeanUtils.copyProperties(dto, student);
                } catch (Exception e) {
                    logger.error("Error in copying data : {}", e.getMessage());
                }
                return dto;
            }).collect(Collectors.toList());
            response.setSuccess(true);
            response.setMessage("Data fetched successfully");
            response.setData(dtos);
        } catch (Exception e) {
            logger.error("Error in getting list: ", e);
            response.setSuccess(false);
            response.setMessage("Failed to retrieve the data");
        }
        return response;
    }
}
