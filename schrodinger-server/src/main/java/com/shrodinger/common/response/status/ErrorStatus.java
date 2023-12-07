package com.shrodinger.common.response.status;

import com.shrodinger.common.response.BaseErrorCode;
import com.shrodinger.common.response.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    _UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "COMMON405", "지원하지 않는 미디어 파일입니다."),
    IMAGE_IS_NULL(HttpStatus.BAD_REQUEST, "COMMON406", "이미지 파일은 null이면 안됩니다."),

    // 멤버 관련 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    MEMBER_LOGIN_FAILURE(HttpStatus.BAD_REQUEST, "MEMBER4003", "아이디 혹은 비밀번호를 잘못 입력하였습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),


    MEMBER_SIGNUP_ERROR(HttpStatus.BAD_REQUEST, "SIGNUP4001", "회원가입 유효성 검사 실패"),
    EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "SIGNUP4002", "이미 존재하는 이메일입니다."),
    NEIGHBORHOOD_NOT_EXIST(HttpStatus.BAD_REQUEST, "SIGNUP4003", "동네가 존재하지 않습니다."),

    //가계부 관련 에러
    ACCOUNT_BOOK_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "ACCOUNT_BOOK4001", "이미 가계부가 존재합니다."),
    ACCOUNT_BOOK_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, "ACCOUNT_BOOK4002", "가계부 생성 유효성 검사 실패"),
    ACCOUNT_BOOK_NOT_EXIST(HttpStatus.NOT_FOUND, "ACCOUNT_BOOK4003", "가계부가 존재하지 않습니다."),
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),

    //거래 관련 에러

    TRANSACTION_TYPE_NOT_MATCH(HttpStatus.BAD_REQUEST, "TRANSACTION_4001", "잘못된 트랜잭션 타입 입력입니다. 수입과 지출을 잘 맞춰주세요!"),
    TRANSACTION_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, "TRANSACTION_4002", "트랜잭션 관련 json 요청 유효성 검사 실패"),
    TRANSACTION_INVALID_CATEGORY_ENUM(HttpStatus.BAD_REQUEST, "TRANSACTION 4003", "잘못된 카테고리 enum 숫자 입력입니다."),

    //게시글 관련 에러
    NEIGHBORHOOD_POST_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, "NEIGHBORHOOD_POST_4002", "게시물 생성 관련 json 요청 유효성 검사 실패"),
    NEIGHBORHOOD_POST_NOT_EXIST(HttpStatus.NOT_FOUND, "NEIGHBORHOOD_POST_4041", "해당 게시글이 존재하지 않습니다."),
    NEIGHBORHOOD_POST_OWNER_ERROR(HttpStatus.BAD_REQUEST, "NEIGHBORHOOD_POST_4001", "해당 유저가 글의 주인이 아닙니다."),

    NEIGHBORHOOD_COMMENT_NOT_EXIST(HttpStatus.NOT_FOUND, "NEIGHBORHOOD_COMMENT_404", "해당 댓글이 존재하지 않습니다."),
    SCRAP_ERROR(HttpStatus.BAD_REQUEST, "SCRAP_POST_ARGUMENT_ERROR","스크랩 대상 게시글이 존재하지 않습니다."),

    DIARY_NOT_EXIST(HttpStatus.BAD_REQUEST, "DIARY_4041", "다이어리가 존재하지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
