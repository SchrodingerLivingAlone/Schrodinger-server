package com.shrodinger.domain.acoountbook.dto;

import com.shrodinger.domain.acoountbook.entity.AccountBook;
import com.shrodinger.domain.user.entity.Member;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class CreateAccountBookRequestDTO {
    //예산
    @Min(value = 0, message = "예산 최소 최소 값은 0입니다.")
    @NotNull
    private Long budget;

    @NotNull
    @Range(min = 2000, max = 2500)
    private Integer year;

    @NotNull
    @Range(min = 1, max = 12, message = "12월까지만 입력해주세요")
    private Integer month;

    public static AccountBook toEntity(CreateAccountBookRequestDTO createAccountBookRequestDTO, Member member) {
        return AccountBook.builder()
                .expenditure(0L)
                .income(0L)
                .budget(createAccountBookRequestDTO.getBudget())
                .year(createAccountBookRequestDTO.getYear())
                .month(createAccountBookRequestDTO.getMonth())
                .member(member)
                .build();
    }

}
