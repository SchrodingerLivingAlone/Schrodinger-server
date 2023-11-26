package com.shrodinger.domain.neighborhood.neighborhoodpost.service;

import com.shrodinger.common.exception.handler.UserHandler;
import com.shrodinger.common.jwt.SecurityUtil;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.domain.neighborhood.neighborhood.repository.NeighborhoodRepository;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.CreateNeighborhoodPostRequestDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.CreateNeighborhoodPostResponseDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostImage;
import com.shrodinger.domain.neighborhood.neighborhoodpost.repository.*;
import com.shrodinger.domain.user.dto.UserNeighborhoodResponseDTO;
import com.shrodinger.domain.user.entity.Member;
import com.shrodinger.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NeighborhoodPostService {
    private final NeighborhoodPostRepository neighborhoodPostRepository;
    private final NeighborhoodPostImageRepository neighborhoodPostImageRepository;
    private final NeighborhoodCommentRepository neighborhoodCommentRepository;
    private final NeighborhoodHeartRepository neighborhoodHeartRepository;
    private final MemberRepository memberRepository;
    private final NeighborhoodRepository neighborhoodRepository;

    public UserNeighborhoodResponseDTO getUserLocation() {
        return UserNeighborhoodResponseDTO.builder().town(getMemberFromToken().getNeighborhood().getDong()).build();
    }
    public CreateNeighborhoodPostResponseDTO createNeighborhoodPost(CreateNeighborhoodPostRequestDTO createNeighborhoodPostRequestDTO) {
        Member member = getMemberFromToken();
        NeighborhoodPost neighborhoodPost = createNeighborhoodPostRequestDTO.toEntity(member);
        neighborhoodPost.updateImages(convertToNeighborhoodPostImageList(createNeighborhoodPostRequestDTO.getImages(),neighborhoodPost));
        neighborhoodPostRepository.save(neighborhoodPost);
        return CreateNeighborhoodPostResponseDTO.from(neighborhoodPost);
    }


    /*
    public void createNeighborhoodPost(CreateNeighborhoodPostRequestDTO createNeighborhoodPostRequestDTO) {
        Member member = getMemberFromToken();
        NeighborhoodPost = createNeighborhoodPostRequestDTO.
        neighborhoodPostRepository.save(creareq.toEntity(user,/*user.getDongnae()dongnae));
    }
     */


    /*
    public ContentsDto searchByKeyword(String keyword, int category, Pageable pageable) {
        String categoryName = DongnaeBoardCategory.valueOf(category).name();

        List<DongnaeBoard> dongnaeBoardList = dongnaeBoardRepository.findByKeyword(keyword, categoryName, pageable);

        if (dongnaeBoardList.isEmpty()) {
            throw new CustomException(ErrorCode.NO_CONTENT_FOUND);
        }

        return new ContentsDto(getListResponses(dongnaeBoardList));
    }

    */
    private Member getMemberFromToken() {
        String userEmail = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserHandler(ErrorStatus._UNAUTHORIZED));
        return member;
    }
    public List<NeighborhoodPostImage> convertToNeighborhoodPostImageList(List<String> images , NeighborhoodPost neighborhoodPost) {
        List<NeighborhoodPostImage> neighborhoodPostImages = new ArrayList<>();
        for (String imageUrl : images) {
            // NeighborhoodPostImage 객체 생성
            NeighborhoodPostImage neighborhoodPostImage = NeighborhoodPostImage.builder()
                    .neighborhoodPost(neighborhoodPost)
                    .imageUrl(imageUrl)
                    .build();
            neighborhoodPostImages.add(neighborhoodPostImage);
        }
        return neighborhoodPostImages;
    }


}
