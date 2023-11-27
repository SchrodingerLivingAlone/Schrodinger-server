package com.shrodinger.domain.neighborhood.neighborhoodpost.service;

import com.shrodinger.common.exception.handler.NeighborhoodPostHandler;
import com.shrodinger.common.exception.handler.UserHandler;
import com.shrodinger.common.jwt.SecurityUtil;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodHeart;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.repository.NeighborhoodHeartRepository;
import com.shrodinger.domain.neighborhood.neighborhoodpost.repository.NeighborhoodPostRepository;
import com.shrodinger.domain.user.entity.Member;
import com.shrodinger.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NeighborhoodHeartService {

    private final NeighborhoodHeartRepository neighborhoodHeartRepository;
    private final NeighborhoodPostRepository neighborhoodPostRepository;
    private final MemberRepository memberRepository;

    public boolean addLike(Long postId) {

        Member member = getMemberFromToken();
        NeighborhoodPost post = neighborhoodPostRepository.findById(postId).orElseThrow(
                () -> new NeighborhoodPostHandler(ErrorStatus.NEIGHBORHOOD_POST_NOT_EXIST));
        if (!neighborhoodHeartRepository.existsByMemberAndNeighborhoodPost(member, post)) {
            post.setLikeCount(post.getLikeCount() + 1);
            neighborhoodHeartRepository.save(new NeighborhoodHeart(member, post));
            return true;
        } else {
            post.setLikeCount(post.getLikeCount() - 1);
            neighborhoodHeartRepository.deleteByMemberAndNeighborhoodPost(member, post);
            return false;
        }

    }

    private Member getMemberFromToken() {
        String userEmail = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserHandler(ErrorStatus._UNAUTHORIZED));
        return member;
    }

}
