package com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodComment;

import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodComment;
import com.shrodinger.domain.user.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentResponseDTO {
    Long id;
    String nickname;
    String comment;
    String profile_image;
    boolean isOwner;

    public static CommentResponseDTO from(NeighborhoodComment comment, Member currentUser) {
        boolean isOwner = comment.getMember().equals(currentUser);
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .nickname(comment.getMember().getNickname())
                .comment(comment.getContent())
                .profile_image(comment.getMember().getProfileImage())
                .isOwner(isOwner)
                .build();
    }
}
