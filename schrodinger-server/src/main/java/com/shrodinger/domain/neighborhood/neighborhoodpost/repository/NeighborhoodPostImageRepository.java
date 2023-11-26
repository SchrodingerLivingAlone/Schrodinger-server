package com.shrodinger.domain.neighborhood.neighborhoodpost.repository;

import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NeighborhoodPostImageRepository extends JpaRepository<NeighborhoodPostImage,Long> {

    @Query(value = "SELECT * FROM neighborhood_post_image WHERE neighborhood_post_id = ?1 ORDER BY created_at ASC LIMIT 1", nativeQuery = true)
    Optional<NeighborhoodPostImage> findFirst(long neighborhoodPostId);

    List<NeighborhoodPost> findAllByNeighborhoodPostId(long NeighborhoodPostId);
    void deleteAllByNeighborhoodPostId(Long NeighborhoodPostId);
}
