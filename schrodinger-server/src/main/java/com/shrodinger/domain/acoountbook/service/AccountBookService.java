package com.shrodinger.domain.acoountbook.service;


import com.shrodinger.common.exception.handler.AccountBookHandler;
import com.shrodinger.common.exception.handler.UserHandler;
import com.shrodinger.common.jwt.SecurityUtil;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.domain.acoountbook.dto.*;
import com.shrodinger.domain.acoountbook.entity.AccountBook;
import com.shrodinger.domain.acoountbook.repository.AccountBookRepository;
import com.shrodinger.domain.user.entity.Member;
import com.shrodinger.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountBookService {

    private final AccountBookRepository accountBookRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createAccountBook(CreateAccountBookRequestDTO createAccountBookRequestDTO) {
        String userEmail = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserHandler(ErrorStatus._UNAUTHORIZED));
        if (!accountBookRepository.existsByYearAndMonthAndMember(
                createAccountBookRequestDTO.getYear(),
                createAccountBookRequestDTO.getMonth(), member)) {
            accountBookRepository.save(createAccountBookRequestDTO.toEntity(createAccountBookRequestDTO, member));
        } else {
            throw new AccountBookHandler(ErrorStatus.ACCOUNT_BOOK_ALREADY_EXIST);
        }
    }

    public AccountBookResponseDTO getBudget(AccountBookRequestDTO accountBookRequestDTO) {
        String userEmail = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserHandler(ErrorStatus._UNAUTHORIZED));
        AccountBook accountBook = accountBookRepository.findByYearAndMonthAndMember(accountBookRequestDTO.getYear(),
                accountBookRequestDTO.getMonth(),
                member).orElseThrow(() -> new AccountBookHandler(ErrorStatus.ACCOUNT_BOOK_NOT_EXIST));
        return AccountBookResponseDTO.from(accountBook);

    }

    @Transactional
    public AccountBookResponseDTO updateBudget(UpdateAccountBookRequestDTO updateAccountBookRequestDTO){
        String userEmail = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserHandler(ErrorStatus._UNAUTHORIZED));
        AccountBook accountBook = accountBookRepository.findByYearAndMonthAndMember(updateAccountBookRequestDTO.getYear(),
                updateAccountBookRequestDTO.getMonth(),
                member).orElseThrow(() -> new AccountBookHandler(ErrorStatus.ACCOUNT_BOOK_NOT_EXIST));
        accountBook.updateBudget(updateAccountBookRequestDTO.getBudget());
        return AccountBookResponseDTO.from(accountBook);
    }

    // 가계부 조회 -> 이번달 남은 예산 & 지출, 저축(수입), 카테고리별 지출
    public AccountBookExpenseResponseDTO getAccountBookResponse(AccountBookRequestDTO accountBookRequestDTO) {
        String userEmail = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserHandler(ErrorStatus._UNAUTHORIZED));
        AccountBook accountBook = accountBookRepository.findByYearAndMonthAndMember(accountBookRequestDTO.getYear(),
                accountBookRequestDTO.getMonth(),
                member).orElseThrow(() -> new AccountBookHandler(ErrorStatus.ACCOUNT_BOOK_NOT_EXIST));

        return AccountBookExpenseResponseDTO.builder()
                .income(accountBook.getIncome())
                .expenditure(accountBook.getExpenditure())
                .budget(accountBook.getBudget() + accountBook.getIncome() - accountBook.getExpenditure())
                .expense(accountBookRepository.getExpense(accountBook.getYear(), accountBook.getMonth(), member.getId()))
                .build();
    }

}
