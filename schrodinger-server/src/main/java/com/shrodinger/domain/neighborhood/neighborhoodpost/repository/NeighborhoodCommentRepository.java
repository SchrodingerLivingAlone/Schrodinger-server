package com.shrodinger.domain.neighborhood.neighborhoodpost.repository;

import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodComment;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeighborhoodCommentRepository extends JpaRepository<NeighborhoodComment, Long> {

    List<NeighborhoodComment> findAllByNeighborhoodPostId(Long id);

    /*public int countAllByNeighborhoodPostId(Long NeighborhoodPostId);
    public int countAllByUserId(Long userId);

     */

    /*
    @Query(value = "select c from DongnaeComment c join fetch c.dongnaeBoard d " +
            "where c.user.id = :userId order by c.createdAt desc")
            List<DongnaeComment> getCommentByUserIdAndBoard(@Param("userId") Long userId);
    @Query("SELECT u FROM User u WHERE u.id = :user_id")
    User findByUserId(@Param("user_id") Long user_id);

    @Query("SELECT db FROM DongnaeBoard db WHERE db.id = :dongnae_board_id")
    DongnaeBoard findByDongnaeBoardId(@Param("dongnae_board_id") Long dongnae_board_id);

    @Query(value = "SELECT dongnae_comment.*, COUNT(dongnae_comment_like.dongnae_comment_id) AS cnt FROM dongnae_comment\n" +
            "LEFT JOIN dongnae_comment_like ON dongnae_comment.dongnae_comment_id = dongnae_comment_like.dongnae_comment_id\n" +
            "WHERE dongnae_comment.dongnae_board_id = :dongnae_board_id\n" +
            "GROUP BY dongnae_comment.dongnae_comment_id ORDER BY cnt DESC;", nativeQuery = true)
    List<DongnaeComment> findAllByBoardId(@Param("dongnae_board_id") DongnaeBoard dongnae_board_id);
    */
}
