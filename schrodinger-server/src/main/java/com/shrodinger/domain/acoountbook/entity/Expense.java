package com.shrodinger.domain.acoountbook.entity;

import com.shrodinger.domain.transaction.entity.TransactionCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Expense {
    public TransactionCategory transactionCategory;
    public long price;
}
