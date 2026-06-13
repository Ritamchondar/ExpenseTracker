package com.ritamrupayan.expenseTracker.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;


import lombok.Data;


@Data
public class ExpenseCreateRequest {
	private UUID userId;
	private String expenseName;
	private BigDecimal expenseAmount;
	private String expenseType;
	private Date expenseDate;
}
