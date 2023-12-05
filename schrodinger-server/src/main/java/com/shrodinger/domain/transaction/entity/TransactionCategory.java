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
    MART(2, "편의점/마트"),
    ALCOHOL_PARTY(3, "술/유흥"),
    SHOPPING(4, "쇼핑"),
    HOBBY_LEISURE(5, "취미/여가"),
    HEALTH(6, "건강"),
    LIVING_COMMUNICATION(7, "주거/통신"),
    INSURANCE_FINANCE(8, "보험/금융"),
    BEAUTY(9, "미용"),
    TRANSPORTATION(10, "교통비"),
    TRAVEL_LODGE(11, "여행/숙박"),
    EDUCATION(12, "교육"),
    SAVING_INVESTMENT(13, "저축/투자"),
    EXPENDITURE_ETC(14, "기타(지출)"),

    SALARY(15, "월급"),
    ALLOWANCE(16, "용돈"),
    TRANSFER(17, "이월"),
    WITHDRAW(18, "자산인출"),
    INCOME_ETC(19, "기타(수입)");

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
