package com.ritamrupayan.expenseTracker.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ritamrupayan.expenseTracker.model.Expense;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
	
	// Finds all expenses linked to a specific user's UUID
    List<Expense> findByUser_UserId(UUID userId);
    
    // Optional: Find expenses by type for a specific user (e.g., all "Food" expenses)
    List<Expense> findByUser_UserIdAndExpenseType(UUID userId, String expenseType);

}
