package com.shrodinger.domain.transaction.repository;

import com.shrodinger.domain.acoountbook.entity.AccountBook;
import com.shrodinger.domain.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByYearAndMonthAndDayAndAccountBook(Integer year, Integer month, Integer day, AccountBook accountBook);
}
