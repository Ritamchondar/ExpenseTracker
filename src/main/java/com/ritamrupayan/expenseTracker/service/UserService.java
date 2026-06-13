package com.ritamrupayan.expenseTracker.service;

import org.springframework.stereotype.Service;

import com.ritamrupayan.expenseTracker.dto.UserCreateRequest;
import com.ritamrupayan.expenseTracker.dto.UserLoginRequest;
import com.ritamrupayan.expenseTracker.dto.UserUpdatePasswordRequest;
import com.ritamrupayan.expenseTracker.model.User;
import com.ritamrupayan.expenseTracker.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(UserCreateRequest request) {
        // 1. Check if the email is already registered
        if (userRepository.existsByUserMail(request.getUserMail())) {
            throw new IllegalArgumentException("A user with this email already exists!");
        }

        // 2. Map the DTO to our Entity using the Builder pattern from Lombok
        User newUser = User.builder()
                .userName(request.getUserName())
                .userMail(request.getUserMail())
                // Note: We are saving plain text for now to get the CRUD working.
                // We will add a PasswordEncoder when we implement Spring Security later.
                .userPassword(request.getUserPassword()) 
                .build();

        // 3. Save and return the created user
        return userRepository.save(newUser);
    }

	public User loginUser(UserLoginRequest request) {
		
		User user = User.builder()
				.userMail(request.getUserMail())
				.userPassword(request.getUserPassword())
				.build();
		
		return userRepository.findByUserMailAndUserPassword(user.getUserMail(), user.getUserPassword());
	}

	public User updatePassword(UserUpdatePasswordRequest request) {
		User user = userRepository.findByUserMail(request.getEmail());
		if (user == null) {
			throw new IllegalArgumentException("User not found with email: " + request.getEmail());
		}
		user.setUserPassword(request.getNewPassword());
		return userRepository.save(user);
	}
}
