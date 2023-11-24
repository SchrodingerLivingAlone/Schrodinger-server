package com.shrodinger.domain.user.repository;

import com.shrodinger.domain.user.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
    Optional<Member>findByNickname(String nickname);

}
