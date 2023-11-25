package com.shrodinger.domain.acoountbook.repository;

import com.shrodinger.domain.acoountbook.entity.AccountBook;
import com.shrodinger.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountBookRepository extends JpaRepository<AccountBook, Long>, AccountBookRepositoryCustom {

    boolean existsByYearAndMonthAndMember(Integer year, Integer month, Member member);

    Optional<AccountBook> findByYearAndMonthAndMember(Integer year, Integer month, Member member);

    @Modifying
    @Query(value = "update AccountBook ab "
            + "set ab.expenditure = ab.expenditure + :expenditure "
            + "where ab.id = :accountBookId")
    void updateAccountBookExpenditure(@Param("accountBookId")Long accountBookId, @Param("expenditure")Long expenditure);

    @Modifying
    @Query(value = "update AccountBook ab "
            + "set ab.income = ab.income + :income "
            + "where ab.id = :accountBookId")
    void updateAccountBookIncome(@Param("accountBookId")Long accountBookId, @Param("income")Long income);


}
