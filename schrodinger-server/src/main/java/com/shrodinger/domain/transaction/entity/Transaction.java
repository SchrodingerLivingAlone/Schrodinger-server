package com.shrodinger.domain.transaction.entity;

import com.shrodinger.common.entity.BaseTimeEntity;
import com.shrodinger.domain.acoountbook.entity.AccountBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Entity
public class Transaction extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_book_id", nullable = false)
    private AccountBook accountBook;

    @Column(nullable = false)
    private Integer type;   // 0이면 지출, 1이면 수입

    @Column(nullable = false)
    private Long price;

    @Enumerated(value = EnumType.STRING)
    private PayCategory payCategory;  //현금, 체크카드 등

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private TransactionCategory transactionCategory;  //식비, 교육 등

    @Column(columnDefinition = "MEDIUMTEXT")
    private String categoryMemo;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private Integer day;

}
