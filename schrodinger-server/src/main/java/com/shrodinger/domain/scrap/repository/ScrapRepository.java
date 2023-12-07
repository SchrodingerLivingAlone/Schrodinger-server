package com.shrodinger.domain.scrap.repository;

import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.scrap.entity.Scrap;
import com.shrodinger.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    List<Scrap> findAllByMember(Member member);

    boolean existsByMemberAndNeighborhoodPost(Member member, NeighborhoodPost neighborhoodPost);

    @Transactional
    void deleteByMemberAndNeighborhoodPost(Member member, NeighborhoodPost neighborhoodPost);
}
