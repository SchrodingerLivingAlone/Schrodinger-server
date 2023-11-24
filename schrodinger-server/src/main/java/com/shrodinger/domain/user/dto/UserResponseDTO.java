package com.shrodinger.domain.user.dto;


import com.shrodinger.common.jwt.TokenInfo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponseDTO {

    TokenInfo tokenInfo;
    String nickName;
}
