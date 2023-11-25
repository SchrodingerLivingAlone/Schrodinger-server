package com.shrodinger.domain.acoountbook.repository;

import com.shrodinger.domain.acoountbook.entity.Expense;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class AccountBookRepositoryCustomImpl implements AccountBookRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Expense> getExpense (@Param("year") Integer year, @Param("month") Integer month, @Param("userId") Long userId) {

        return em.createQuery(
                        "select new com.shrodinger.domain.acoountbook.entity.Expense(t.transactionCategory, sum(t.price)) " +
                                "from Transaction t where t.year= :year and t.month= :month and t.accountBook.member.id =:userId and t.type = 0 group by t.transactionCategory " +
                                "order by sum(t.price) desc", Expense.class)
                .setParameter("year", year)
                .setParameter("month", month)
                .setParameter("userId", userId)
                .getResultList();
    }
}
