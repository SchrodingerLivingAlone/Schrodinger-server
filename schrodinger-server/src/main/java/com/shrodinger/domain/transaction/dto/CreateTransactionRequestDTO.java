package com.shrodinger.domain.transaction.dto;

import com.shrodinger.common.exception.handler.TransactionHandler;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.domain.acoountbook.entity.AccountBook;
import com.shrodinger.domain.transaction.entity.PayCategory;
import com.shrodinger.domain.transaction.entity.Transaction;
import com.shrodinger.domain.transaction.entity.TransactionCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequestDTO{

    @NotNull(message = "년도는 필수입니다")
    @Range(min = 2000, max = 2500)
    private Integer year;

    @NotNull(message = "월은 필수입니다")
    @Range(min = 1, max = 12, message = "12월까지만 입력해주세요")
    private Integer month;
    @Range(min = 1, max = 31 , message = "1~31까지만 입력해주세요")
    @NotNull(message = "일은 필수입니다")
    private Integer day;

    @NotNull(message = "지출 또는 수입을 선택해주세요")
    private Integer type;

    @NotNull(message = "금액은 필수입니다")
    private Long price;

    @NotNull(message = "카테고리는 필수입니다")
    private Integer transactionCategory;

    private Integer payCategory;

    private String categoryMemo;

    public Transaction toEntity(AccountBook accountBook){
        if (type == 1){
            if (transactionCategory <=4){
                throw new TransactionHandler(ErrorStatus.TRANSACTION_TYPE_NOT_MATCH);
            }
        }
        else if(type == 0){
            if(transactionCategory >4){
                throw new TransactionHandler(ErrorStatus.TRANSACTION_TYPE_NOT_MATCH);
            }
        }
        return Transaction.builder()
                .type(type) // 0이면 지출, 1이면 수입
                .transactionCategory(TransactionCategory.valueOf(transactionCategory))
                .year(year)
                .month(month)
                .day(day)
                .price(price)
                .payCategory(PayCategory.valueOf(payCategory))
                .categoryMemo(categoryMemo)
                .accountBook(accountBook)
                .build();
    }
}
