package com.pio.studentManagement.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentReceiveDTO {
    private int id;

    @NotEmpty(message = "is required")
    private String name;

    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Enter valid contact number")
    @Column(unique = true)
    private String contact;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Enter valid email")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$#!%*?&]).{8,}$", message = "Password must contain one lowercase, one uppercase, one number, one special character and of min. length 8")
    private String errorMessage;


    @Override
    public String toString() {
        return "StudentReceiveDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
