package com.shrodinger.domain.transaction.controller;


import com.shrodinger.common.response.ApiResponse;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.common.response.status.SuccessStatus;
import com.shrodinger.domain.transaction.dto.CreateTransactionRequestDTO;
import com.shrodinger.domain.transaction.dto.TransactionRequestDTO;
import com.shrodinger.domain.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import static com.shrodinger.common.util.ValidationUtils.getValidationErrors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ApiResponse createTransaction(@RequestBody @Validated CreateTransactionRequestDTO createTransactionRequestDTO, Errors errors) {
        if (errors.hasErrors()) {
            return ApiResponse.ofFailure(ErrorStatus.TRANSACTION_ARGUMENT_ERROR, getValidationErrors(errors));
        }
        return ApiResponse.of(SuccessStatus.CREATE_TRANSACTION_SUCCESS, transactionService.createTransaction(createTransactionRequestDTO));
    }

    @GetMapping
    public ApiResponse getAllTransactions(
            @RequestParam @NotNull(message = "년도는 필수입니다") Integer year,
            @RequestParam @NotNull(message = "월은 필수입니다") Integer month,
            @RequestParam @NotNull(message = "일은 필수입니다") Integer day,
            Errors errors) {

        if (errors.hasErrors()) {
            return ApiResponse.ofFailure(ErrorStatus.TRANSACTION_ARGUMENT_ERROR, getValidationErrors(errors));
        }
        TransactionRequestDTO transactionRequestDTO = TransactionRequestDTO
                .builder()
                .year(year)
                .month(month)
                .day(day)
                .build();
        return ApiResponse.of(SuccessStatus.GET_TRANSACTIONS_SUCCESS, transactionService.getTransactions(transactionRequestDTO));
    }

}
