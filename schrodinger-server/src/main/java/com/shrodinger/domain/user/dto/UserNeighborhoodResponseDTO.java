package com.shrodinger.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class UserNeighborhoodResponseDTO {
    @NotNull(message = "동네 정보가 없습니다.")
    private String town;
}
