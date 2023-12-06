package com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodPost;

import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostCategory;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.shrodinger.common.util.TimeUtils.TimeFormat;

@Getter
@Builder
public class NeighborhoodPostResponseDTO {
    private Long id;
    private String profileImage;
    private String nickname;
    private String place;

    private String dong;

    private NeighborhoodPostCategory neighborhoodPostCategory;

    private String title;

    private String content;

    private String imageUrl;

    private String createdAt;

    private String calculatedTime;


    private int view;
    private int likeCount;
    private int commentCount;


    public static NeighborhoodPostResponseDTO from(NeighborhoodPost neighborhoodPost) {
        return NeighborhoodPostResponseDTO.builder()
                .id(neighborhoodPost.getId())
                .nickname(neighborhoodPost.getMember().getNickname())
                .profileImage(neighborhoodPost.getMember().getProfileImage())
                .place(neighborhoodPost.getPlace())
                .dong(neighborhoodPost.getNeighborhood().getDong())
                .neighborhoodPostCategory(neighborhoodPost.getNeighborhoodPostCategory())
                .title(neighborhoodPost.getTitle())
                .content(neighborhoodPost.getContent())
                .imageUrl(neighborhoodPost.getNeighborhoodPostImages().get(0).getImageUrl())
                .createdAt(String.valueOf(neighborhoodPost.getCreatedAt()))
                .calculatedTime(TimeFormat(neighborhoodPost.getCreatedAt()))
                .view(neighborhoodPost.getView())
                .likeCount(neighborhoodPost.getLikeCount())
                .commentCount(neighborhoodPost.getCommentCount())
                .build();
    }

}
