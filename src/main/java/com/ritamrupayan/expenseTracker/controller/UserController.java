package com.ritamrupayan.expenseTracker.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ritamrupayan.expenseTracker.dto.UserCreateRequest;
import com.ritamrupayan.expenseTracker.model.User;
import com.ritamrupayan.expenseTracker.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // The @Valid annotation triggers the checks in our DTO
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserCreateRequest request) {
        User createdUser = userService.createUser(request);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
