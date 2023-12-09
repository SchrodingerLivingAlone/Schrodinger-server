package com.shrodinger.domain.neighborhood.neighborhoodpost.repository;


import com.shrodinger.domain.neighborhood.neighborhood.entity.Neighborhood;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostCategory;
import com.shrodinger.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NeighborhoodPostRepository extends JpaRepository<NeighborhoodPost, Long> {
    List<NeighborhoodPost> findAllByNeighborhoodAndNeighborhoodPostCategoryOrderByCreatedAtDesc(Neighborhood neighborhood, NeighborhoodPostCategory category);
    List<NeighborhoodPost> findAllByNeighborhoodAndNeighborhoodPostCategoryOrderByViewDescCreatedAtDesc(Neighborhood neighborhood, NeighborhoodPostCategory category);

    List<NeighborhoodPost> findAllByNeighborhoodAndNeighborhoodPostCategoryOrderByLikeCountDescCreatedAtDesc(Neighborhood neighborhood, NeighborhoodPostCategory category);

    List<NeighborhoodPost> findAllByNeighborhoodAndCreatedAtAfterOrderByViewDescCreatedAtDesc(Neighborhood neighborhood, LocalDateTime localDateTime);
    Optional<NeighborhoodPost> findById(Long id);
    List<NeighborhoodPost> findAllByTitleContaining(String keyword);

    List<NeighborhoodPost> findAllByContentContaining(String keyword);

    List<NeighborhoodPost> findAllByMember(Member member);

    boolean existsByMemberAndId(Member member , Long id);
    /*
    @Query(value = "SELECT dongnae_board.*, COUNT(dongnae_sympathy.dongnae_board_id) AS likes FROM dongnae_board\n" +
            "LEFT JOIN dongnae_sympathy ON  dongnae_board.dongnae_board_id = dongnae_sympathy.dongnae_board_id\n" +
            "WHERE (dongnae_board.title LIKE %:keyword% OR dongnae_board.content LIKE %:keyword%)\n" +
            "AND dongnae_board.category = :category GROUP BY dongnae_board.dongnae_board_id ", nativeQuery = true)
    List<DongnaeBoard> findByKeyword(@Param("keyword") String keyword, @Param("category") String category, Pageable pageable);

    @Query(value = "select * from dongnae_board ORDER BY created_at DESC;", nativeQuery = true)
    List<DongnaeBoard> findAllOrderByCreatedAt();

    @Query(value = "SELECT dongnae_board.*, COUNT(dongnae_sympathy.dongnae_board_id) AS cnt FROM dongnae_board\n" +
            "LEFT JOIN dongnae_sympathy ON  dongnae_board.dongnae_board_id = dongnae_sympathy.dongnae_board_id\n" +
            "GROUP BY dongnae_board.dongnae_board_id ORDER BY cnt DESC ;", nativeQuery = true)
    List<DongnaeBoard> findAllOrderByLikes();

    @Query(value = "select * from dongnae_board where category = ?1 ORDER BY created_at DESC LIMIT 1 ;", nativeQuery = true)
    List<DongnaeBoard> findTwoByCategoryOrderByCreatedAt(String category);


    public int countAllByUserId(Long userId);

    List<DongnaeBoard> findAllByUserId(Long userId);
     */
}
