package com.ritamrupayan.expenseTracker.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateExpenseByIdRequest {
	private UUID expenseId;
	private String expenseName;
	private BigDecimal expenseAmount;
	private String expenseType;
	private Date expenseDate;
}
