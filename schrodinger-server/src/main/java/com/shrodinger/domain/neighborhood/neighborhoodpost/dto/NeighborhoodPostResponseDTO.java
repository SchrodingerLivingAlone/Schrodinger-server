package com.shrodinger.domain.neighborhood.neighborhoodpost.dto;

import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostCategory;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class NeighborhoodPostResponseDTO {
    private Long id;

    private String dong;

    private NeighborhoodPostCategory neighborhoodPostCategory;

    private String title;

    private String content;

    private String imageUrl;

    private String createdAt;

    private int view;

    private int commentCount;

    private int likes;

    /*
    private static NeighborhoodPostsResponseDTO from(NeighborhoodPost neighborhoodPost , String oneImage) {
        return NeighborhoodPostResponseDTO.builder()
                .id(neighborhoodPost.getId())
                .dong(neighborhoodPost.getNeighborhood().getDong())
                .neighborhoodPostCategory(neighborhoodPost.getNeighborhoodPostCategory())
                .title(neighborhoodPost.getTitle())
                .imageUrl(oneImage)
                .createdAt(String.valueOf(neighborhoodPost.getCreatedAt()))
                .view(neighborhoodPost.getView())
    }

     */
}
