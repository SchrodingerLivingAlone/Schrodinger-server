package com.shrodinger.domain.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class UserSignUpRequestDto {

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @Pattern(regexp = "^[a-zA-z0-9]{6,20}$", message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    @NotBlank(message = "시(ex. 서울시)는 필수 입력 값입니다.")
    private String city;

    @NotBlank(message = "구(ex.동작구)는 필수 입력 값입니다.")
    private String gu;

    @NotBlank(message = "동(ex.상도동)은 필수 입력 값입니다.")
    private String dong;




}
