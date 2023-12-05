package com.shrodinger.domain.diary.dto;

import com.shrodinger.domain.diary.entity.Diary;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodPost.CreateNeighborhoodPostResponseDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CreateDiaryResponseDTO {
    private Long id;
    private String content;
    private List<String> imageUrls;
    private String createdAt;

    public static CreateDiaryResponseDTO from(Diary diary) {
        return CreateDiaryResponseDTO.builder()
                .id(diary.getId())
                .content(diary.getContent())
                .imageUrls(diary.fromImages())
                .createdAt(String.valueOf(diary.getCreatedAt()))
                .build();
    }

}
