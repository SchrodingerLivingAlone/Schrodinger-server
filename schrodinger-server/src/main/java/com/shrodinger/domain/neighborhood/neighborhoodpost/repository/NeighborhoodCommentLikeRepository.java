package com.shrodinger.domain.neighborhood.neighborhoodpost.repository;

import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NeighborhoodCommentLikeRepository extends JpaRepository<NeighborhoodComment, Long> {
    /*
    @Query(value = "SELECT dongnae_comment_like.* FROM dongnae_comment_like WHERE dongnae_comment_like.dongnae_comment_id = :comment_id", nativeQuery = true)
    DongnaeCommentLike findByCommentId(@Param("comment_id") DongnaeComment comment_id);

    int countAllByDongnaeCommentId(Long dongnae_comment_id);

     */
}
