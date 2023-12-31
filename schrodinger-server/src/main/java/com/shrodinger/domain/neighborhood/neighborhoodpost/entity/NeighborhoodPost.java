package com.shrodinger.domain.neighborhood.neighborhoodpost.entity;

import com.shrodinger.common.entity.BaseTimeEntity;
import com.shrodinger.domain.neighborhood.neighborhood.entity.Neighborhood;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodPost.UpdateNeighborhoodPostRequestDTO;
import com.shrodinger.domain.scrap.entity.Scrap;
import com.shrodinger.domain.user.entity.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Entity
public class NeighborhoodPost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "neighborhood_post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "neighborhood_id", nullable = false)
    private Neighborhood neighborhood;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private NeighborhoodPostCategory neighborhoodPostCategory;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String content;

    @Column(name = "view", nullable = false)
    @ColumnDefault("0")
    private Integer view;


    @Column(name = "like_count", nullable = false)
    @ColumnDefault("0")
    private Integer likeCount;


    @Column(name = "comment_count", nullable = false)
    @ColumnDefault("0")
    private Integer commentCount;

    @OneToMany(mappedBy = "neighborhoodPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NeighborhoodPostImage> neighborhoodPostImages;

    @OneToMany(mappedBy = "neighborhoodPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NeighborhoodComment> neighborhoodComments;

    @OneToMany(mappedBy = "neighborhoodPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NeighborhoodHeart> neighborhoodHearts;

    @OneToMany(mappedBy = "neighborhoodPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Scrap> scraps;
    private String place;   // 사용자 장소 공유시 장소 이름(ex. "00키친")


    public void updateBoard(UpdateNeighborhoodPostRequestDTO updateNeighborhoodPostRequestDTO) {
        this.neighborhoodPostCategory = NeighborhoodPostCategory.valueOf(updateNeighborhoodPostRequestDTO.getCategory());
        this.title = updateNeighborhoodPostRequestDTO.getTitle();
        this.content = updateNeighborhoodPostRequestDTO.getContent();
        this.place = updateNeighborhoodPostRequestDTO.getPlace();
        List<String> updatedImageUrls = updateNeighborhoodPostRequestDTO.getImages();

        for (int i = 0; i < Math.min(neighborhoodPostImages.size(), updatedImageUrls.size()); i++) {
            neighborhoodPostImages.get(i).setImageUrl(updatedImageUrls.get(i));
        }
        for (int i = neighborhoodPostImages.size(); i < updatedImageUrls.size(); i++) {
            neighborhoodPostImages.add(NeighborhoodPostImage.builder().neighborhoodPost(this).imageUrl(updatedImageUrls.get(i)).build());
        }

    }

    public void updateView() {
        this.view += 1;
    }

    public void upCommentCount() {
        this.commentCount += 1;
    }

    public void deleteCommentCount() {
        this.commentCount -= 1;
    }

    public void updateImages(List<NeighborhoodPostImage> neighborhoodPostImages) {
        this.neighborhoodPostImages = neighborhoodPostImages;
    }

    public List<String> fromImages() {
        return neighborhoodPostImages.stream().map(NeighborhoodPostImage::getImageUrl).collect(Collectors.toList());
    }
}
