package com.shrodinger.domain.transaction.service;

import com.shrodinger.common.exception.handler.AccountBookHandler;
import com.shrodinger.common.exception.handler.UserHandler;
import com.shrodinger.common.jwt.SecurityUtil;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.domain.acoountbook.entity.AccountBook;
import com.shrodinger.domain.acoountbook.repository.AccountBookRepository;
import com.shrodinger.domain.transaction.dto.CreateTransactionRequestDTO;
import com.shrodinger.domain.transaction.dto.TransactionRequestDTO;
import com.shrodinger.domain.transaction.dto.TransactionResponseDTO;
import com.shrodinger.domain.transaction.dto.TransactionResponsesDTO;
import com.shrodinger.domain.transaction.entity.Transaction;
import com.shrodinger.domain.transaction.repository.TransactionRepository;
import com.shrodinger.domain.user.entity.Member;
import com.shrodinger.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static com.shrodinger.common.jwt.SecurityUtil.getCurrentUserEmail;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountBookRepository accountBookRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public TransactionResponseDTO createTransaction(CreateTransactionRequestDTO createTransactionRequestDTO) {
        Member member = getMemberFromToken();
        AccountBook accountBook = findAccountBook(createTransactionRequestDTO.getYear(), createTransactionRequestDTO.getMonth(), member);
        Transaction transaction = createTransactionRequestDTO.toEntity(accountBook);
        transactionRepository.save(transaction);
        if (createTransactionRequestDTO.getType() == 1) {
            accountBookRepository.updateAccountBookIncome(accountBook.getId(), createTransactionRequestDTO.getPrice());
        } else {
            accountBookRepository.updateAccountBookExpenditure(accountBook.getId(), createTransactionRequestDTO.getPrice());
        }
        return TransactionResponseDTO.from(transaction);
    }

    public TransactionResponsesDTO getTransactions(TransactionRequestDTO transactionRequestDTO) {
        Member member = getMemberFromToken();
        AccountBook accountBook = findAccountBook(transactionRequestDTO.getYear(), transactionRequestDTO.getMonth(), member);
        List<Transaction> transactionList = transactionRepository.findAllByYearAndMonthAndDayAndAccountBook(transactionRequestDTO.getYear(),
                transactionRequestDTO.getMonth(),
                transactionRequestDTO.getDay(),
                accountBook);
        return TransactionResponsesDTO.of(transactionList);
    }

    private Member getMemberFromToken() {
        String userEmail = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserHandler(ErrorStatus._UNAUTHORIZED));
        return member;
    }

    private AccountBook findAccountBook(Integer year, Integer month, Member member) {
        AccountBook accountBook = accountBookRepository.findByYearAndMonthAndMember(year,
                month, member).orElseThrow(() -> new AccountBookHandler(ErrorStatus.ACCOUNT_BOOK_NOT_EXIST));
        return accountBook;
    }

}
