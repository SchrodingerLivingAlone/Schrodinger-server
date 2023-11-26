package com.shrodinger.domain.neighborhood.neighborhoodpost.repository;


import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface NeighborhoodPostRepository extends JpaRepository<NeighborhoodPost, Long> {

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
