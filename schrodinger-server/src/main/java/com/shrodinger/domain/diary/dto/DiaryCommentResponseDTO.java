package com.shrodinger.domain.diary.dto;

import com.shrodinger.domain.diary.entity.DiaryComment;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DiaryCommentResponseDTO {
    Long userId;
    Long commentId;
    String nickname;
    String comment;
    String profile_image;

    public static DiaryCommentResponseDTO from(DiaryComment diaryComment) {
        return DiaryCommentResponseDTO.builder()
                .userId(diaryComment.getMember().getId())
                .commentId(diaryComment.getId())
                .nickname(diaryComment.getMember().getNickname())
                .comment(diaryComment.getContent())
                .profile_image(diaryComment.getMember().getProfileImage())
                .build();
    }

}
