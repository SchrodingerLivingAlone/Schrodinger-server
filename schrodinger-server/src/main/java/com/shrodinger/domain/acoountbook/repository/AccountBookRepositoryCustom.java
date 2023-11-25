package com.shrodinger.domain.acoountbook.repository;

import com.shrodinger.domain.acoountbook.entity.Expense;

import java.util.List;

public interface AccountBookRepositoryCustom {
    List<Expense> getExpense(Integer year, Integer month, Long userId);

}
