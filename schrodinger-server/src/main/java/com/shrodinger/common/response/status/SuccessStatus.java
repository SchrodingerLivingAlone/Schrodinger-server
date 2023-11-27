package com.shrodinger.common.response.status;

import com.shrodinger.common.response.BaseCode;
import com.shrodinger.common.response.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 일반적인 응답
    _OK(HttpStatus.OK, "COMMON200", "성공입니다."),

    // 회원가입 응답
    _SIGNUP_SUCCESS(HttpStatus.OK, "SIGNUP200", "회원가입 성공입니다."),
    _LOGIN_SUCCESS(HttpStatus.OK, "LOGIN200", "로그인 성공입니다."),

    IMAG_UPLOAD_SUCCESS(HttpStatus.OK, "IMAGE200", "이미지 업로드 성공입니다."),
    // 멤버 관련 응답

    // ~~~ 관련 응답

    //가계부 관련 응답
    CREATE_ACCOUNT_BOOK_SUCCESS(HttpStatus.CREATED, "ACCOUNT_BOOK_201", "가계부 생성 성공 "),
    GET_ACCOUNT_BOOK_SUCCESS(HttpStatus.OK, "ACCOUNT_BOOK_200", "가계부 정보 조회 성공"),
    GET_ACCOUNT_BOOK_WITH_EXPENSE_SUCCESS(HttpStatus.OK, "ACCOUNT_BOOK_EXPENSE_200", "가계부 지출 정보 조회 성공"),


    CREATE_TRANSACTION_SUCCESS(HttpStatus.CREATED, "TRANSACTION_201", "거래 내역 생성 성공"),
    GET_TRANSACTIONS_SUCCESS(HttpStatus.OK, "TRANSACTION_200", "거래 내역 조회 성공"),

    CREATE_NEIGHBORHOOD_POST_SUCCESS(HttpStatus.CREATED, "NEIGHBORHOOD_POST_201", "동네 게시글 생성 성공"),
    GET_NEIGHBORHOOD_POSTS_BY_SORT(HttpStatus.OK, "NEIGHBORHOOD_200", "동네 게시글들 조회 성공"),
    GET_NEIGHBORHOOD_POST(HttpStatus.OK, "NEIGHBORHOOD_200", "게시글 상세 조회 성공"),
    UPDATE_NEIGHBORHOOD_POST_SUCCESS(HttpStatus.OK, "NEIGHBORHOOD_200", "게시물 수정 성공"),
    DELETE_NEIGHBORHOOD_POST_SUCCESS(HttpStatus.OK, "NEIGHBORHOOD_200", "게시글 삭제 성공"),

    //댓글
    CREAT_NEIGHBORHOOD_POST_COMMENT_SUCCESS(HttpStatus.CREATED, "NEIGHBORHOOD_COMMENT_201", "댓글 생성 성공"),
    GET_NEIGHBORHOOD_COMMENTS_SUCCESS(HttpStatus.OK, "NEIGHBORHOOD_COMMENT_200", "댓글들 조회 성공");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
