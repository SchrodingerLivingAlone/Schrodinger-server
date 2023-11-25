package com.shrodinger.domain.transaction.dto;

import com.shrodinger.domain.transaction.entity.Transaction;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class TransactionResponsesDTO {
    private List<TransactionResponseDTO> transactions;

    public TransactionResponsesDTO(List<TransactionResponseDTO> transactions) {
        this.transactions = transactions;
    }

    public static TransactionResponsesDTO of(List<Transaction> transactionList) {
        List<TransactionResponseDTO> transactionResponses = transactionList
                .stream()
                .map(TransactionResponseDTO::from)
                .collect(Collectors.toList());
        return new TransactionResponsesDTO(transactionResponses);
    }
}
