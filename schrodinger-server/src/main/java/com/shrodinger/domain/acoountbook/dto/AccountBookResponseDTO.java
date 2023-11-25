package com.shrodinger.domain.acoountbook.dto;

import com.shrodinger.domain.acoountbook.entity.AccountBook;
import com.shrodinger.domain.transaction.dto.TransactionResponseDTO;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountBookResponseDTO {
    //얘산
    private Long budget;
    //지출
    private Long expenditure;

    //수입
    private Long income;

    public static AccountBookResponseDTO from(AccountBook accountBook) {
        return AccountBookResponseDTO.builder()
                .budget(accountBook.getBudget())
                .expenditure(accountBook.getExpenditure())
                .income(accountBook.getIncome())
                .build();
    }
}
