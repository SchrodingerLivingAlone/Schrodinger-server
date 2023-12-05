package com.shrodinger.domain.transaction.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import com.shrodinger.common.exception.handler.TransactionHandler;
import com.shrodinger.common.response.status.ErrorStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionCategory {

    FOODS(0, "식비"),
    CAFE_SNACK(1, "카페/간식"),
    TRANSPORTATION(2, "교통"),
    ALCOHOL_PARTY(3, "술/유흥"),
    EXPENDITURE_ETC(4, "기타(지출)"),
    SALARY(5, "월급"),
    ALLOWANCE(6, "용돈"),
    TRANSFER(7, "이월"),
    WITHDRAW(8, "자산인출"),
    INCOME_ETC(9, "기타(수입)");

    private final Integer value;
    private final String category;

    @JsonValue
    public String getCategory() {
        return this.category;
    }

    public static TransactionCategory valueOf(Integer value) {
        for (TransactionCategory category : TransactionCategory.values()) {
            if (category.getValue().equals(value)) {
                return category;
            }
        }
        throw new TransactionHandler(ErrorStatus.TRANSACTION_INVALID_CATEGORY_ENUM);
    }
}
