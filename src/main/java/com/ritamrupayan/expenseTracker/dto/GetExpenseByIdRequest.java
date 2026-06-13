package com.ritamrupayan.expenseTracker.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class GetExpenseByIdRequest {
	private UUID expenseId;
}
