package com.ritamrupayan.expenseTracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ritamrupayan.expenseTracker.dto.ExpenseCreateRequest;
import com.ritamrupayan.expenseTracker.dto.ExpenseResponse;
import com.ritamrupayan.expenseTracker.service.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
	
	private final ExpenseService expenseService;
	
	@PostMapping("/add")
	public ResponseEntity<ExpenseResponse> addExpense(@Valid @RequestBody ExpenseCreateRequest request){
		ExpenseResponse createdExpense = expenseService.addExpense(request);
		
        
		return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
	}

}
