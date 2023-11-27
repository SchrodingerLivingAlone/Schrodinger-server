package com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodComment;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateCommentRequestDTO {
    @NotBlank(message = "내용은 반드시 입력되어야합니다.")
    String comment;
}
