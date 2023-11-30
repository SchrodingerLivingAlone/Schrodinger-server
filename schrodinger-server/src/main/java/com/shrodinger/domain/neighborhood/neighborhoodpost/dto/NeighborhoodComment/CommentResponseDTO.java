package com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodComment;

import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodComment;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentResponseDTO {
    String nickname;
    String comment;
    String profile_image;

    public static CommentResponseDTO from(NeighborhoodComment comment) {
        return CommentResponseDTO.builder()
                .nickname(comment.getMember().getNickname())
                .comment(comment.getContent())
                .profile_image(comment.getMember().getProfileImage())
                .build();
    }
}
