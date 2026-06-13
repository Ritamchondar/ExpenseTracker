package com.ritamrupayan.expenseTracker.dto;


import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseResponse {
    private UUID expenseId;
}