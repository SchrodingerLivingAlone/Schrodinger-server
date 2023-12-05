package com.shrodinger.domain.acoountbook.dto;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class CalendarResponseDTO {
    private Integer lastDay;
    private boolean[] transactionArray;
}
