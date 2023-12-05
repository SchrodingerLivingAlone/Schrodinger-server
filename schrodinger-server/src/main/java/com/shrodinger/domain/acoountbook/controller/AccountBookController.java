package com.shrodinger.domain.acoountbook.controller;

import com.shrodinger.common.response.ApiResponse;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.common.response.status.SuccessStatus;
import com.shrodinger.domain.acoountbook.dto.AccountBookRequestDTO;
import com.shrodinger.domain.acoountbook.dto.CalendarRequestDTO;
import com.shrodinger.domain.acoountbook.dto.CreateAccountBookRequestDTO;
import com.shrodinger.domain.acoountbook.dto.UpdateAccountBookRequestDTO;
import com.shrodinger.domain.acoountbook.service.AccountBookService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import static com.shrodinger.common.util.ValidationUtils.getValidationErrors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/accountBooks")
public class AccountBookController {
    private final AccountBookService accountBookService;

    @PostMapping("/budget")
    public ApiResponse createBudget(@Validated @RequestBody CreateAccountBookRequestDTO createAccountBookRequestDTO, Errors errors) {
        if (errors.hasErrors()) {
            return ApiResponse.ofFailure(ErrorStatus.ACCOUNT_BOOK_ARGUMENT_ERROR, getValidationErrors(errors));
        }
        accountBookService.createAccountBook(createAccountBookRequestDTO);
        return ApiResponse.of(SuccessStatus.CREATE_ACCOUNT_BOOK_SUCCESS, createAccountBookRequestDTO);
    }

    @GetMapping("/budget")
    public ApiResponse getBudget(@Validated @RequestBody AccountBookRequestDTO accountBookRequestDTO, Errors errors) {
        if (errors.hasErrors()) {
            return ApiResponse.ofFailure(ErrorStatus.ACCOUNT_BOOK_ARGUMENT_ERROR, getValidationErrors(errors));
        }
        return ApiResponse.of(SuccessStatus.GET_ACCOUNT_BOOK_SUCCESS, accountBookService.getBudget(accountBookRequestDTO));
    }

    @PutMapping("/budget")
    public ApiResponse updateBudget(@Validated @RequestBody UpdateAccountBookRequestDTO updateAccountBookRequestDTO, Errors errors) {
        if (errors.hasErrors()) {
            return ApiResponse.ofFailure(ErrorStatus.ACCOUNT_BOOK_ARGUMENT_ERROR, getValidationErrors(errors));
        }
        return ApiResponse.of(SuccessStatus.GET_ACCOUNT_BOOK_SUCCESS, accountBookService.updateBudget(updateAccountBookRequestDTO));
    }


    @GetMapping("/all")
    public ApiResponse getAccountBookWithExpense(@Validated @RequestBody AccountBookRequestDTO accountBookRequestDTO , Errors errors) {
        if (errors.hasErrors()) {
            return ApiResponse.ofFailure(ErrorStatus.ACCOUNT_BOOK_ARGUMENT_ERROR, getValidationErrors(errors));
        }
        return ApiResponse.of(SuccessStatus.GET_ACCOUNT_BOOK_WITH_EXPENSE_SUCCESS, accountBookService.getAccountBookResponse(accountBookRequestDTO));
    }

    @GetMapping("/calendar")
    public ApiResponse getCalendar(@Validated @RequestBody CalendarRequestDTO calendarRequestDTO, Errors errors){
        if (errors.hasErrors()) {
            return ApiResponse.ofFailure(ErrorStatus.ACCOUNT_BOOK_ARGUMENT_ERROR,getValidationErrors(errors));
        }
        return ApiResponse.of(SuccessStatus.GET_ACCOUNT_BOOK_CALENDAR_SUCCESS, accountBookService.getAccountBookCalendar(calendarRequestDTO));
    }

}
