package com.pio.studentManagement.service;

import com.pio.studentManagement.dto.StudentReceiveDTO;
import com.pio.studentManagement.dto.StudentSendDTO;
import com.pio.studentManagement.entity.Student;
import com.pio.studentManagement.repository.StudentRepository;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * This method is used for saving the student data in the database.
     * @param studentSendDTO : This is my DTO used for receiving data from the frontend.
     */
    @Override
    public String saveStudent(StudentSendDTO studentSendDTO) {
        Student student = new Student();
        try {
            // This method is used for copying DTO data to entity
            BeanUtils.copyProperties(student, studentSendDTO);
            studentRepository.save(student);
            logger.info("Data saved successfully");
            return "Data saved successfully";
        } catch (Exception e) {
            logger.error("Error in saving data: {}", e.getMessage());
            return "Error in saving data";
        }
    }


    /**
     * This method is used to fetch the list of student data from the DB.
     * It is copying the field data of student object to studentReceiveDTO object.
     * @return : It is returning the list of students that are saved in the DB.
     */
    public List<StudentReceiveDTO> getAllStudent() {
        logger.info("Method of getting list invoked");
        List<Student> students = studentRepository.findAll();
        List<StudentReceiveDTO> receiveDTOS = new ArrayList<>();
        try {
            for (Student student : students) {
                StudentReceiveDTO dto = new StudentReceiveDTO();
                BeanUtils.copyProperties(dto, student);
                receiveDTOS.add(dto);
            }
        } catch (Exception e) {
            logger.error("Error in getting list: {}", e.getMessage());
            StudentReceiveDTO errorDTO = new StudentReceiveDTO();
            errorDTO.setErrorMessage("Failed to retrieve student data.");
            receiveDTOS.add(errorDTO);
        }
        return receiveDTOS;
    }
}
