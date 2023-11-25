package com.shrodinger.domain.acoountbook.entity;

import com.shrodinger.common.entity.BaseTimeEntity;
import com.shrodinger.domain.transaction.entity.Transaction;
import com.shrodinger.domain.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Entity
public class AccountBook extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "account_book_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "accountBook", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();
    //예산
    @Range(min = 0)
    private Long budget;

    //지출
    //@Column(nullable = false)
    private Long expenditure;

    //수입
    //@Column(nullable = false)
    private Long income;

    @Column(nullable = false)
    @Range(min = 2000, max = 2500)
    private Integer year;

    @Column(nullable = false)
    @Range(min = 1, max = 12)
    private Integer month;



    public void updateBudget(Long budget) {
        this.budget = budget;
    }
}
