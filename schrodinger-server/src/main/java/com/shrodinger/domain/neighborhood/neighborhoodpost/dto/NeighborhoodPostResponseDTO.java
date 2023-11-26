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


    public static NeighborhoodPostResponseDTO from(NeighborhoodPost neighborhoodPost) {
        return NeighborhoodPostResponseDTO.builder()
                .id(neighborhoodPost.getId())
                .dong(neighborhoodPost.getNeighborhood().getDong())
                .neighborhoodPostCategory(neighborhoodPost.getNeighborhoodPostCategory())
                .title(neighborhoodPost.getTitle())
                .content(neighborhoodPost.getContent())
                .imageUrl(neighborhoodPost.getNeighborhoodPostImages().get(0).getImageUrl())
                .createdAt(String.valueOf(neighborhoodPost.getCreatedAt()))
                .view(neighborhoodPost.getView())
                .build();
    }
}
