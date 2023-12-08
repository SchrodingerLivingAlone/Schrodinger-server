package com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodPost;

import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostCategory;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
public class UpdateNeighborhoodPostRequestDTO {
    @NotNull(message = "카테고리는 필수입니다.")
    private Integer category;

    @NotNull(message = "제목은 필수입니다.")
    private String title;

    @NotNull(message = "내응은 필수입니다.")
    private String content;

    private List<String> images;

    private List<MultipartFile> files;

    @NotNull(message = "장소는 필수입니다.")
    private String place;
}
