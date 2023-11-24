package com.shrodinger.domain.transaction.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PayCategory {
    CASH(0, "현금"),
    CHECK_CARD(1, "체크카드"),
    CREDIT_CARD(2, "신용카드"),
    ETC(3, "기타");

    private final Integer value;
    private final String pay;

    @JsonValue
    public String getPay() {
        return this.pay;
    }
}
