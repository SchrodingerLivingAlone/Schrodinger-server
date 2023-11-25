package com.shrodinger.domain.transaction.dto;

import com.shrodinger.domain.transaction.entity.Transaction;
import com.shrodinger.domain.transaction.entity.TransactionCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO{

    private Integer type;
    private TransactionCategory transactionCategory;
    private Integer year;
    private Integer month;
    private Integer day;
    private Long price;
    private Integer payCategory;
    private String memo;

    public static TransactionResponseDTO from(Transaction transaction) {
        return TransactionResponseDTO.builder()
                .type(transaction.getType())
                .transactionCategory(transaction.getTransactionCategory())
                .year(transaction.getYear())
                .month(transaction.getMonth())
                .day(transaction.getDay())
                .price(transaction.getPrice())
                .payCategory(transaction.getPayCategory().getValue())
                .memo(transaction.getCategoryMemo())
                .build();
    }

}
