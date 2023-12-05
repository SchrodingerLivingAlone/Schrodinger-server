package com.shrodinger.domain.diary.dto;

import com.shrodinger.domain.diary.entity.Diary;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateDiaryRequestDTO {

    @NotNull(message = "내용은 필수입니다.")
    private String content;

    List<MultipartFile> multipartFile;

    public Diary toEntity(Member member) {
        return Diary.builder()
                .member(member)
                .content(content)
                .likeCount(0)
                .commentCount(0)
                .likeCount(0)
                .build();
    }
}
