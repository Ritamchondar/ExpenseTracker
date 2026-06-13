package com.ritamrupayan.expenseTracker.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetExpenseResponse {
	private UUID expenseId;
	private String expenseName;
	private BigDecimal expenseAmount;
	private String expenseType;
	private Date expenseDate;
}
