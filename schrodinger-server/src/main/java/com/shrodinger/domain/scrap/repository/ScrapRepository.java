package com.shrodinger.domain.scrap.repository;

import com.shrodinger.domain.scrap.entity.Scrap;
import com.shrodinger.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    List<Scrap> findAllByMember(Member member);
}
