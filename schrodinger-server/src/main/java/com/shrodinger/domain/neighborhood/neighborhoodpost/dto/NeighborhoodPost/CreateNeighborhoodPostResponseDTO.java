package com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodPost;

import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostCategory;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CreateNeighborhoodPostResponseDTO {
    private Long id;

    private String dong;

    private NeighborhoodPostCategory neighborhoodPostCategory;

    private String title;

    private String content;

    private String place;

    private List<String> imageUrls;

    private String createdAt;

    private int view;

    public static CreateNeighborhoodPostResponseDTO from(NeighborhoodPost neighborhoodPost) {
        return CreateNeighborhoodPostResponseDTO.builder()
                .id(neighborhoodPost.getId())
                .dong(neighborhoodPost.getNeighborhood().getDong())
                .neighborhoodPostCategory(neighborhoodPost.getNeighborhoodPostCategory())
                .title(neighborhoodPost.getTitle())
                .place(neighborhoodPost.getPlace())
                .content(neighborhoodPost.getContent())
                .imageUrls(neighborhoodPost.fromImages())
                .createdAt(String.valueOf(neighborhoodPost.getCreatedAt()))
                .view(neighborhoodPost.getView())
                .build();
    }
}
