package com.shrodinger.domain.neighborhood.neighborhoodpost.repository;

import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodHeart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NeighborhoodHeartRepository extends JpaRepository<NeighborhoodHeart, Long> {
    /*
    int countAllByNeighborhoodPostId(Long neighborhoodPostId);
    int countAllByUserId(Long userId);

    List<NeighborhoodHeart> findByUser_Id(long user_id);

     */
    /*
    @Query(value = "SELECT dongnae_sympathy.* FROM dongnae_sympathy WHERE dongnae_sympathy.dongnae_board_id = :dongnae_board_id", nativeQuery = true)
    DongnaeSympathy findByDongnaeBoardId(@Param("dongnae_board_id") DongnaeBoard dongnae_board_id);

    @Query(value = "SELECT dongnae_sympathy.* FROM dongnae_sympathy WHERE dongnae_sympathy.user_id = :user_id", nativeQuery = true)
    List<DongnaeSympathy> findAllByUserId(@Param("user_id") Long user_id);
     */
}
