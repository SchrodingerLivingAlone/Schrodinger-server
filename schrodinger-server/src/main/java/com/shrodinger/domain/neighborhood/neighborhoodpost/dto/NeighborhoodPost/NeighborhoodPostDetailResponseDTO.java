package com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodPost;

import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostCategory;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static com.shrodinger.common.util.TimeUtils.TimeFormat;


@Getter
@Builder
public class NeighborhoodPostDetailResponseDTO {
    private Long id;

    private String dong;

    private NeighborhoodPostCategory neighborhoodPostCategory;

    private String title;

    private String content;

    private List<String> imageUrls;

    private String createdAt;
    private String calculatedTime;


    private int view;

    private int likeCount;
    private int commentCount;

    public static NeighborhoodPostDetailResponseDTO from(NeighborhoodPost neighborhoodPost) {
        return NeighborhoodPostDetailResponseDTO.builder()
                .id(neighborhoodPost.getId())
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
                .build();
    }

}
