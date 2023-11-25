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

import static com.shrodinger.common.util.ValidationUtils.getValidationErrors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ApiResponse createTransaction(@RequestBody @Validated CreateTransactionRequestDTO createTransactionRequestDTO, Errors errors){
        if (errors.hasErrors()) {
            return ApiResponse.onFailure(ErrorStatus.TRANSACTION_ARGUMENT_ERROR.getCode(), ErrorStatus.TRANSACTION_ARGUMENT_ERROR.getMessage(), getValidationErrors(errors));
        }
        return ApiResponse.of(SuccessStatus.CREATE_TRANSACTION_SUCCESS, transactionService.createTransaction(createTransactionRequestDTO));
    }

    @GetMapping
    public ApiResponse getAllTransactions(@RequestBody @Validated TransactionRequestDTO transactionRequestDTO, Errors errors) {
        if (errors.hasErrors()) {
            return ApiResponse.onFailure(ErrorStatus.TRANSACTION_ARGUMENT_ERROR.getCode(), ErrorStatus.TRANSACTION_ARGUMENT_ERROR.getMessage(), getValidationErrors(errors));
        }
        return ApiResponse.of(SuccessStatus.GET_TRANSACTIONS_SUCCESS, transactionService.getTransactions(transactionRequestDTO));
    }
}
