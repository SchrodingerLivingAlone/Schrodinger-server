package com.shrodinger.domain.transaction.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import com.shrodinger.common.exception.handler.TransactionHandler;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostCategory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum PayCategory {
    CASH(0, "현금"),
    CARD(1, "카드");

    private final Integer value;
    private final String pay;

    public static PayCategory valueOf(Integer value) {
        for (PayCategory category : PayCategory.values()) {
            if (category.getValue().equals(value)) {
                return category;
            }
        }
        throw new TransactionHandler(ErrorStatus.TRANSACTION_INVALID_CATEGORY_ENUM);
    }

    @JsonValue
    public String getPay() {
        return this.pay;
    }
}
