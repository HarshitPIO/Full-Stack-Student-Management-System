package com.pio.studentManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "unique_contact", columnNames = "studentContact"), @UniqueConstraint(name = "unique_email", columnNames = "studentEmail")})
public class Student {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    static final int minLength = 3;
    static final int maxLength = 10;
    @NotEmpty(message = "is required")
    @Size(min = minLength, max = maxLength, message = "Student name must be in between {min} and {max} character")
    private String studentName;


    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Enter valid contact number")
    @Column(unique = true)
    private String studentContact;
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Enter valid email")
    @Column(unique = true)
    private String studentEmail;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain one lowercase, one uppercase, one number, one special character and of min. length 8")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentContact() {
        return studentContact;
    }

    public void setStudentContact(String studentContact) {
        this.studentContact = studentContact;
    }

    @Override
    public String toString() {
        return "Student{" + "studentId=" + studentId + ", studentName='" + studentName + '\'' + ", studentEmail='" + studentEmail + '\'' + ", studentContact='" + studentContact + '\'' + ", password='" + password + '\'' + '}';
    }
}
