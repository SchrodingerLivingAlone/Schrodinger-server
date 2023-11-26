package com.shrodinger.domain.neighborhood.neighborhoodpost.entity;

import com.shrodinger.common.entity.BaseTimeEntity;
import com.shrodinger.domain.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Entity
public class NeighborhoodComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "neighborhood_comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "neighborhood_post_id", nullable = false)
    private NeighborhoodPost neighborhoodPost;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_comment_id")
    private NeighborhoodComment parentComment;  //대댓글일 경우, 부모 댓글 id를 가진다.

    @OneToMany(mappedBy = "parentComment")
    private List<NeighborhoodComment> childComment;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false)
    private String content;

    /*
    public void modifyComment(DongnaeCommentDto dongnaeCommentDto) {
        this.content = dongnaeCommentDto.getContent();
    }
     */
}
