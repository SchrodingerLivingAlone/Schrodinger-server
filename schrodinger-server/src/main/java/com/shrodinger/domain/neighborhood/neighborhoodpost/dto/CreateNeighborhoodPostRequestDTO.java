package com.shrodinger.domain.neighborhood.neighborhoodpost.dto;

import com.shrodinger.domain.neighborhood.neighborhood.entity.Neighborhood;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostCategory;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostImage;
import com.shrodinger.domain.transaction.entity.TransactionCategory;
import com.shrodinger.domain.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateNeighborhoodPostRequestDTO {

    @NotNull(message = "카테고리는 필수입니다.")
    private NeighborhoodPostCategory neighborhoodPostCategory;

    @NotNull(message = "제목은 필수입니다.")
    private String title;

    @NotNull(message = "내응은 필수입니다.")
    private String content;

    private List<String> images;

    @NotNull(message = "장소는 필수입니다.")
    private String place;


    public NeighborhoodPost toEntity(Member member, List<NeighborhoodPostImage> neighborhoodPostImages) {

        return NeighborhoodPost.builder()
                .member(member)
                .neighborhood(member.getNeighborhood())
                .neighborhoodPostCategory(neighborhoodPostCategory)
                .title(title)
                .content(content)
                .place(place)
                .neighborhoodPostImages(neighborhoodPostImages)
                .view(0)
                .build();
    }
}
