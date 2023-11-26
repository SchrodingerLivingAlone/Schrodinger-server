package com.shrodinger.domain.neighborhood.neighborhoodpost.entity;

import com.shrodinger.common.entity.BaseTimeEntity;
import com.shrodinger.domain.neighborhood.neighborhood.entity.Neighborhood;
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
    @Column(name = "neighborhood_board_id")
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
    private Integer view = 0;

    @OneToMany(mappedBy = "neighborhoodPost")
    private List<NeighborhoodPostImage> neighborhoodPostImages;

    private String place;   // 사용자 장소 공유시 장소 이름(ex. "00키친")

    /*
    public void updateBoard(DongnaeBoardDto.Request request) {
        this.category = DongnaeBoardCategory.valueOf(request.getCategory());
        this.title = request.getTitle();
        this.content = request.getContent();
        this.place = request.getPlace();
        this.placeLocation = request.getPlaceLocation();
    }
     */

    public void updateView() {
        this.view +=1;
    }

    public void updateImages(List<NeighborhoodPostImage> neighborhoodPostImages) {
        this.neighborhoodPostImages = neighborhoodPostImages;
    }

    public List<String> fromImages() {
        return neighborhoodPostImages.stream()
                .map(NeighborhoodPostImage::getImageUrl)
                .collect(Collectors.toList());
    }
}
