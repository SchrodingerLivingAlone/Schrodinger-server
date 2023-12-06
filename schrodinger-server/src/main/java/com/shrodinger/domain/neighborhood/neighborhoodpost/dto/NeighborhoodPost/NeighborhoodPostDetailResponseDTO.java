package com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodPost;

import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodComment.CommentResponseDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

import static com.shrodinger.common.util.TimeUtils.TimeFormat;


@Getter
@Setter
@Builder
public class NeighborhoodPostDetailResponseDTO {
    private Long id;
    private String profileImage;
    private String nickname;
    private String place;

    private String dong;

    private NeighborhoodPostCategory neighborhoodPostCategory;

    private String title;

    private String content;

    private List<String> imageUrls;

    private String createdAt;
    private String calculatedTime;
    private boolean isScrapped;
    private boolean isLiked;

    private int view;

    private int likeCount;
    private int commentCount;

    private List<CommentResponseDTO> comments;

    public static NeighborhoodPostDetailResponseDTO from(NeighborhoodPost neighborhoodPost) {
        return NeighborhoodPostDetailResponseDTO.builder()
                .id(neighborhoodPost.getId())
                .nickname(neighborhoodPost.getMember().getNickname())
                .profileImage(neighborhoodPost.getMember().getProfileImage())
                .place(neighborhoodPost.getPlace())
                .dong(neighborhoodPost.getNeighborhood().getDong())
                .neighborhoodPostCategory(neighborhoodPost.getNeighborhoodPostCategory())
                .title(neighborhoodPost.getTitle())
                .content(neighborhoodPost.getContent())
                .imageUrls(neighborhoodPost.fromImages())
                .createdAt(String.valueOf(neighborhoodPost.getCreatedAt()))
                .calculatedTime(TimeFormat(neighborhoodPost.getCreatedAt()))
                .view(neighborhoodPost.getView())
                .likeCount(neighborhoodPost.getLikeCount())
                .commentCount(neighborhoodPost.getCommentCount())
                .comments(neighborhoodPost.getNeighborhoodComments().stream().map(CommentResponseDTO::from).collect(Collectors.toList()))
                .build();
    }

}
