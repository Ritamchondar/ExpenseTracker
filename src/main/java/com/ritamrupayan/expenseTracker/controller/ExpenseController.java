package com.ritamrupayan.expenseTracker.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ritamrupayan.expenseTracker.dto.AllExpensesRequest;
import com.ritamrupayan.expenseTracker.dto.ExpenseCreateRequest;
import com.ritamrupayan.expenseTracker.dto.ExpenseDeleteRequest;
import com.ritamrupayan.expenseTracker.dto.ExpenseResponse;
import com.ritamrupayan.expenseTracker.dto.GetExpenseByIdRequest;
import com.ritamrupayan.expenseTracker.dto.GetExpenseResponse;
import com.ritamrupayan.expenseTracker.dto.UpdateExpenseByIdRequest;
import com.ritamrupayan.expenseTracker.service.ExpenseService;



@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
	
	private final ExpenseService expenseService;
	
	@PostMapping("/addExpense")
	public ResponseEntity<ExpenseResponse> addExpense(@Valid @RequestBody ExpenseCreateRequest request){
		ExpenseResponse createdExpense = expenseService.addExpense(request);
		return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
	}
	
	@PostMapping("/getAllExpenses")
	public ResponseEntity<List<GetExpenseResponse>> getAllExpenses(@Valid @RequestBody AllExpensesRequest request){
		 List<GetExpenseResponse> allExpenses = expenseService.getAllExpenses(request);
		 return new ResponseEntity<>(allExpenses, HttpStatus.OK);	
	}
	
	@PostMapping("/getExpensById")
	public ResponseEntity<GetExpenseResponse> getExpenseById(@Valid @RequestBody GetExpenseByIdRequest request){
		 GetExpenseResponse specificExpense = expenseService.getExpenseById(request);
		 return new ResponseEntity<>(specificExpense, HttpStatus.OK);
	}
	
	@PostMapping("/updateExpenseById")
	public ResponseEntity<ExpenseResponse> updateExpenseById(@Valid @RequestBody UpdateExpenseByIdRequest request){
		ExpenseResponse updatedExpense = expenseService.updateExpenseById(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteExpenseById")
	public ResponseEntity<Void> deleteExpenseById(@Valid @RequestBody ExpenseDeleteRequest request){
		
		expenseService.deleteExpenseById(request);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
