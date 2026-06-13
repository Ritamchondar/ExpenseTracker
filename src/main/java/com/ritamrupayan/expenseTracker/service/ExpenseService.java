package com.ritamrupayan.expenseTracker.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

import com.ritamrupayan.expenseTracker.dto.ExpenseCreateRequest;
import com.ritamrupayan.expenseTracker.dto.ExpenseResponse;
import com.ritamrupayan.expenseTracker.model.Expense;
import com.ritamrupayan.expenseTracker.model.User;
import com.ritamrupayan.expenseTracker.repository.ExpenseRepository;
import com.ritamrupayan.expenseTracker.repository.UserRepository;

import jakarta.validation.Valid;



@Service
@RequiredArgsConstructor
@Validated
public class ExpenseService {
	private final ExpenseRepository expenseRepository;
	private final UserRepository userRepository; // Add this
	
	public ExpenseResponse addExpense(@Valid ExpenseCreateRequest request) {
		
		User user = userRepository.findById(request.getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));
		
		Expense expense = Expense.builder()
				.user(user)
				.expenseName(request.getExpenseName())
				.expenseAmount(request.getExpenseAmount())
				.expenseType(request.getExpenseType())
				.expenseDate(request.getExpenseDate())
				.build();
		
		
		Expense savedExpense = expenseRepository.save(expense);
        
        // 4. Map the saved entity to a Response DTO to prevent infinite recursion
         ExpenseResponse output = ExpenseResponse.builder()
                .expenseId(savedExpense.getExpenseId())
                .build();	
        
        return output;
        }

}
