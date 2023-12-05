package com.shrodinger.domain.diary.dto;

import com.shrodinger.domain.diary.entity.Diary;
import com.shrodinger.domain.diary.entity.DiaryComment;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodComment.CommentResponseDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static com.shrodinger.common.util.TimeUtils.TimeFormat;

@Getter
@Builder
public class DiaryResponseDTO {
    private Long id;

    private String content;

    private List<String> imageUrls;
    private String profileImage;
    private String nickname;
    private Long userId;

    private String createdAt;
    private String calculatedTime;

    private int likeCount;
    private int commentCount;

    private List<DiaryCommentResponseDTO> comments;

    public static DiaryResponseDTO from(Diary diary){
        return DiaryResponseDTO.builder()
                .id(diary.getId())
                .profileImage(diary.getMember().getProfileImage())
                .nickname(diary.getMember().getNickname())
                .userId(diary.getMember().getId())
                .content(diary.getContent())
                .imageUrls(diary.fromImages())
                .createdAt(diary.getCreatedAt().toString())
                .calculatedTime(TimeFormat(diary.getCreatedAt()))
                .likeCount(diary.getLikeCount())
                .commentCount(diary.getCommentCount())
                .comments(diary.getDiaryComments().stream().map(DiaryCommentResponseDTO::from).collect(Collectors.toList()))
                .build();
    }
}
