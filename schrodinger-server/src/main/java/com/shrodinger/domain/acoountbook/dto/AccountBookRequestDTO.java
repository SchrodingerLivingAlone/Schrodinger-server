package com.shrodinger.domain.acoountbook.dto;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class AccountBookRequestDTO {

    @NotNull
    @Range(min = 2000, max = 2500)
    Integer year;

    @NotNull
    @Range(min = 1, max = 12, message = "1월부터 12월까지만 입력해주세요")
    Integer month;
}
