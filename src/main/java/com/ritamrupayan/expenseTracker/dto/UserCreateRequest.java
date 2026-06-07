package com.ritamrupayan.expenseTracker.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateRequest {

    @NotBlank(message = "Name cannot be empty")
    private String userName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String userMail;

    @NotBlank(message = "Password cannot be empty")
    private String userPassword;
}