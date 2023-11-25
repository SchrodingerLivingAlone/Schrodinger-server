package com.shrodinger.domain.transaction.dto;

import com.shrodinger.domain.acoountbook.entity.AccountBook;
import com.shrodinger.domain.transaction.entity.PayCategory;
import com.shrodinger.domain.transaction.entity.Transaction;
import com.shrodinger.domain.transaction.entity.TransactionCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO{

    @NotNull(message = "년도는 필수입니다")
    private Integer year;

    @NotNull(message = "월은 필수입니다")
    private Integer month;

    @NotNull(message = "일은 필수입니다")
    private Integer day;

}
