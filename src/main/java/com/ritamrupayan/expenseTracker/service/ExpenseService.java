package com.ritamrupayan.expenseTracker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

import com.ritamrupayan.expenseTracker.dto.AllExpensesRequest;
import com.ritamrupayan.expenseTracker.dto.ExpenseCreateRequest;
import com.ritamrupayan.expenseTracker.dto.ExpenseDeleteRequest;
import com.ritamrupayan.expenseTracker.dto.ExpenseResponse;
import com.ritamrupayan.expenseTracker.dto.GetExpenseByIdRequest;
import com.ritamrupayan.expenseTracker.dto.GetExpenseResponse;
import com.ritamrupayan.expenseTracker.dto.UpdateExpenseByIdRequest;
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

	public List<GetExpenseResponse> getAllExpenses(@Valid AllExpensesRequest request) {
		
		User user = userRepository.findById(request.getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));
		
		Expense expense = Expense.builder()
				.user(user)
				.build();
		
		 List<GetExpenseResponse> allExpenses = expenseRepository.findByUser_UserId(expense.getUser().getUserId())
				 .stream()
				 .map(e -> GetExpenseResponse.builder()
						 .expenseId(e.getExpenseId())
						 .expenseName(e.getExpenseName())
						 .expenseAmount(e.getExpenseAmount())
						 .expenseType(e.getExpenseType())
						 .expenseDate(e.getExpenseDate())
						 .build())
				 .toList();
		 
		 return allExpenses;
		
	}

	public GetExpenseResponse getExpenseById(@Valid GetExpenseByIdRequest request) {
		Expense expense = Expense.builder()
				.expenseId(request.getExpenseId())
				.build();
		
		GetExpenseResponse specificExpense = expenseRepository.findById(expense.getExpenseId())
				.map(e -> GetExpenseResponse.builder()
						.expenseId(e.getExpenseId())
						.expenseName(e.getExpenseName())
						.expenseAmount(e.getExpenseAmount())
						.expenseType(e.getExpenseType())
						.expenseDate(e.getExpenseDate())
						.build())
				.orElseThrow(() -> new RuntimeException("Expense not found with ID: " + request.getExpenseId()));
		
		return specificExpense;
	}

	public ExpenseResponse updateExpenseById(@Valid UpdateExpenseByIdRequest request) {
		// 1. FETCH: Get the existing record (this already has the User safely attached to it!)
        Expense existingExpense = expenseRepository.findById(request.getExpenseId())
                .orElseThrow(() -> new RuntimeException("Expense not found with ID: " + request.getExpenseId()));

        // 2. MODIFY: Update only the details provided in the request
        existingExpense.setExpenseName(request.getExpenseName());
        existingExpense.setExpenseAmount(request.getExpenseAmount());
        existingExpense.setExpenseType(request.getExpenseType());
        existingExpense.setExpenseDate(request.getExpenseDate());

        // Notice: We NEVER touch existingExpense.setUser(). It stays perfectly intact.

        // 3. SAVE: Hibernate will now update the row without breaking the user_id foreign key
        Expense savedExpense = expenseRepository.save(existingExpense);

        // 4. Return your clean DTO (preventing infinite recursion)
        return ExpenseResponse.builder()
                .expenseId(savedExpense.getExpenseId())
                .build();
    
	}

	public void deleteExpenseById(@Valid ExpenseDeleteRequest request) {
		// TODO Auto-generated method stub
		Expense existingExpense = expenseRepository.findById(request.getExpenseId())
                .orElseThrow(() -> new RuntimeException("Expense not found with ID: " + request.getExpenseId()));

		expenseRepository.delete(existingExpense);
		
	}
	
}
