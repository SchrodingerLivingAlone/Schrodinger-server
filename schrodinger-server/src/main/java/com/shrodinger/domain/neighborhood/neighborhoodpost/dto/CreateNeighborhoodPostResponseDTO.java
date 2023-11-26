package com.shrodinger.domain.neighborhood.neighborhoodpost.dto;

import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostCategory;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostImage;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class CreateNeighborhoodPostResponseDTO {
    private Long id;

    private String dong;

    private NeighborhoodPostCategory neighborhoodPostCategory;

    private String title;

    private String content;

    private List<String> imageUrls;

    private String createdAt;

    private int view;

    public static CreateNeighborhoodPostResponseDTO from(NeighborhoodPost neighborhoodPost) {
        return CreateNeighborhoodPostResponseDTO.builder()
                .id(neighborhoodPost.getId())
                .dong(neighborhoodPost.getNeighborhood().getDong())
                .neighborhoodPostCategory(neighborhoodPost.getNeighborhoodPostCategory())
                .title(neighborhoodPost.getTitle())
                .content(neighborhoodPost.getContent())
                .imageUrls(neighborhoodPost.fromImages())
                .createdAt(String.valueOf(neighborhoodPost.getCreatedAt()))
                .view(neighborhoodPost.getView())
                .build();
    }
}
