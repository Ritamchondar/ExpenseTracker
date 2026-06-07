package com.ritamrupayan.expenseTracker.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "expense_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "expense_id", updatable = false, nullable = false)
    private UUID expenseId;

    // The foreign key linking back to the user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "expense_type", length = 100)
    private String expenseType;

    @Column(name = "expense_amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal expenseAmount;

    // Let the database handle the default timestamp creation
    @Column(name = "expense_timestamp", insertable = false, updatable = false)
    private OffsetDateTime expenseTimestamp;

    @Column(name = "expense_operation_total_balance", precision = 19, scale = 4)
    private BigDecimal totalBalance;
}