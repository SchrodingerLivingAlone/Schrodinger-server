package com.shrodinger.domain.scrap.service;

import com.shrodinger.common.exception.handler.NeighborhoodPostHandler;
import com.shrodinger.common.exception.handler.UserHandler;
import com.shrodinger.common.jwt.SecurityUtil;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodPost.NeighborhoodPostResponseDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.repository.NeighborhoodPostRepository;
import com.shrodinger.domain.scrap.dto.CreateScrapRequestDTO;
import com.shrodinger.domain.scrap.entity.Scrap;
import com.shrodinger.domain.scrap.repository.ScrapRepository;
import com.shrodinger.domain.user.entity.Member;
import com.shrodinger.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final NeighborhoodPostRepository neighborhoodPostRepository;
    private final MemberRepository memberRepository;
    private final ScrapRepository scrapRepository;
    public NeighborhoodPostResponseDTO createScrap(Long postId) {
        Member member = getMemberFromToken();
        NeighborhoodPost neighborhoodPost = neighborhoodPostRepository.findById(postId).orElseThrow(
                () -> new NeighborhoodPostHandler(ErrorStatus.NEIGHBORHOOD_POST_NOT_EXIST));
        Scrap scrap = Scrap.builder()
                .neighborhoodPost(neighborhoodPost)
                .member(member)
                .build();
        scrapRepository.save(scrap);
        return NeighborhoodPostResponseDTO.from(neighborhoodPost);
    }

    public List<NeighborhoodPostResponseDTO> getAllScraps(){
        Member member = getMemberFromToken();
        List<Scrap> scraps = scrapRepository.findAllByMember(member);

        List<NeighborhoodPostResponseDTO> neighborhoodPostDTOs = scraps.stream()
                .map(Scrap::getNeighborhoodPost)  // Extract NeighborhoodPost from Scrap
                .map(NeighborhoodPostResponseDTO::from)  // Convert NeighborhoodPost to NeighborhoodPostResponseDTO
                .collect(Collectors.toList());
        return neighborhoodPostDTOs;
    }

    private Member getMemberFromToken() {
        String userEmail = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserHandler(ErrorStatus._UNAUTHORIZED));
        return member;
    }
}
