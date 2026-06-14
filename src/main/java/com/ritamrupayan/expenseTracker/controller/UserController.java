package com.ritamrupayan.expenseTracker.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ritamrupayan.expenseTracker.dto.UserCreateRequest;
import com.ritamrupayan.expenseTracker.dto.UserLoginRequest;
import com.ritamrupayan.expenseTracker.dto.UserUpdatePasswordRequest;
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
    
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody UserLoginRequest request){
        
        User loggedUser = userService.loginUser(request);
        
        // Check if the user was actually found
        if (loggedUser == null) {
            // Return 401 Unauthorized instead of 200 OK
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
        // If successful, return the user data with 200 OK
        return new ResponseEntity<>(loggedUser, HttpStatus.OK);
    }
    
    @PostMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestBody UserUpdatePasswordRequest request) {
		User updatePassword = userService.updatePassword(request);
		return ResponseEntity.ok("Password updated successfully")	;
	}
}
