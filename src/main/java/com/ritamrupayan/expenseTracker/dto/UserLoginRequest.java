package com.ritamrupayan.expenseTracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequest {
	@Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String userMail;

    @NotBlank(message = "Password cannot be empty")
    private String userPassword;
}
