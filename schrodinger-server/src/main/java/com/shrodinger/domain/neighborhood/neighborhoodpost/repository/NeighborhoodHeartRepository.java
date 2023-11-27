package com.shrodinger.domain.neighborhood.neighborhoodpost.repository;

import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodHeart;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NeighborhoodHeartRepository extends JpaRepository<NeighborhoodHeart, Long> {
    boolean existsByMemberAndNeighborhoodPost(Member member, NeighborhoodPost neighborhoodPost);
    void deleteByMemberAndNeighborhoodPost(Member member, NeighborhoodPost neighborhoodPost);
}
