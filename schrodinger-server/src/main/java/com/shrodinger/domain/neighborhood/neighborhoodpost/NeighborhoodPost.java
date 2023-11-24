package com.shrodinger.domain.neighborhood.neighborhoodpost;

import com.shrodinger.common.entity.BaseTimeEntity;
import com.shrodinger.domain.neighborhood.entity.Neighborhood;
import com.shrodinger.domain.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Entity
public class NeighborhoodPost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "dongnae_board_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "neighborhood_id", nullable = false)
    private Neighborhood neighborhood;

    @Enumerated(value = STRING)
    @Column(nullable = false)
    private NeighborhoodBoardCategory category;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String content;

    @Column(name = "view", nullable = false)
    @ColumnDefault("0")
    private Integer view = 0;


    private String place;   // 사용자 장소 공유시 장소 이름(ex. "00키친")
    private String placeLocation;  // 장소의 정확한 위치

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
}
