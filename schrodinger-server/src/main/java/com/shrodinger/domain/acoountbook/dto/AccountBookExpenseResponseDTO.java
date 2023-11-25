package com.shrodinger.domain.acoountbook.dto;

import com.shrodinger.domain.acoountbook.entity.Expense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountBookExpenseResponseDTO {
    private Long expenditure;
    private Long income;
    private Long budget;
    private List<Expense> expense;
}
