package com.shrodinger.domain.neighborhood.neighborhoodpost.service;

import com.shrodinger.common.exception.handler.NeighborhoodHandler;
import com.shrodinger.common.exception.handler.NeighborhoodPostHandler;
import com.shrodinger.common.exception.handler.UserHandler;
import com.shrodinger.common.jwt.SecurityUtil;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.domain.neighborhood.neighborhood.repository.NeighborhoodRepository;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.*;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostCategory;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostImage;
import com.shrodinger.domain.neighborhood.neighborhoodpost.repository.*;
import com.shrodinger.domain.user.dto.UserNeighborhoodResponseDTO;
import com.shrodinger.domain.user.entity.Member;
import com.shrodinger.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Transactional
    public CreateNeighborhoodPostResponseDTO createNeighborhoodPost(CreateNeighborhoodPostRequestDTO createNeighborhoodPostRequestDTO) {
        Member member = getMemberFromToken();
        NeighborhoodPost neighborhoodPost = createNeighborhoodPostRequestDTO.toEntity(member);
        neighborhoodPostRepository.save(neighborhoodPost);

        List<NeighborhoodPostImage> neighborhoodPostImages = new ArrayList<>();
        for (String imageUrl : createNeighborhoodPostRequestDTO.getImages()) {
            NeighborhoodPostImage neighborhoodPostImage = NeighborhoodPostImage.builder()
                    .neighborhoodPost(neighborhoodPost)
                    .imageUrl(imageUrl)
                    .build();
            neighborhoodPostImageRepository.save(neighborhoodPostImage);
            neighborhoodPostImages.add(neighborhoodPostImage);
        }

        // 이미지 저장 후 다시 업데이트
        neighborhoodPost.setNeighborhoodPostImages(neighborhoodPostImages);
        neighborhoodPostRepository.save(neighborhoodPost);

        return CreateNeighborhoodPostResponseDTO.from(neighborhoodPost);
    }


    public List<NeighborhoodPostResponseDTO> getAllPosts(int sort, NeighborhoodPostCategory category) {
        Member member = getMemberFromToken();
        if (sort == 0) {
             return neighborhoodPostRepository.findAllByNeighborhoodAndNeighborhoodPostCategoryOrderByCreatedAt(member.getNeighborhood(),category).stream()
                     .map(NeighborhoodPostResponseDTO::from)
                     .collect(Collectors.toList());
        } else {
            return neighborhoodPostRepository.findAllByNeighborhoodOrderByView(member.getNeighborhood()).stream()
                    .map(NeighborhoodPostResponseDTO::from)
                    .collect(Collectors.toList());
        }
    }

    public NeighborhoodPostResponseDTO updatePost(long post_id, UpdateNeighborhoodPostRequestDTO updateNeighborhoodPostRequestDTO) {
        Member member = getMemberFromToken();
        validateMember(member,post_id);
        NeighborhoodPost post = neighborhoodPostRepository.findById(post_id).orElseThrow(
                () -> new NeighborhoodPostHandler(ErrorStatus.NEIGHBORHOOD_POST_NOT_EXIST));
        post.updateBoard(updateNeighborhoodPostRequestDTO);
        neighborhoodPostRepository.save(post);
        return NeighborhoodPostResponseDTO.from(post);
    }

    public void deletePost(long post_id) {
        Member member = getMemberFromToken();
        validateMember(member,post_id);
        NeighborhoodPost post = neighborhoodPostRepository.findById(post_id).orElseThrow(
                () -> new NeighborhoodPostHandler(ErrorStatus.NEIGHBORHOOD_POST_NOT_EXIST));
        neighborhoodPostRepository.delete(post);
    }
    public NeighborhoodPostDetailResponseDTO getPost (long post_id){
        NeighborhoodPost post = neighborhoodPostRepository.findById(post_id).orElseThrow(
                () -> new NeighborhoodPostHandler(ErrorStatus.NEIGHBORHOOD_POST_NOT_EXIST));
        post.updateView();
        neighborhoodPostRepository.save(post);
        return NeighborhoodPostDetailResponseDTO.from(post);
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

    public void validateMember(Member member, Long post_id){
        if (!neighborhoodPostRepository.existsByMemberAndId(member, post_id)) {
            throw new NeighborhoodPostHandler(ErrorStatus.NEIGHBORHOOD_POST_OWNER_ERROR);
        }
    }


}
