package com.shrodinger.domain.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UpdateLocationRequestDTO {
    @NotBlank(message = "시(ex. 서울시)는 필수 입력 값입니다.")
    private String city;

    @NotBlank(message = "구(ex.동작구)는 필수 입력 값입니다.")
    private String gu;

    @NotBlank(message = "동(ex.상도동)은 필수 입력 값입니다.")
    private String dong;
}
