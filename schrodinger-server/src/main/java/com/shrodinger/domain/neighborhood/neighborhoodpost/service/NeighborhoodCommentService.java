package com.shrodinger.domain.neighborhood.neighborhoodpost.service;

import com.shrodinger.common.exception.handler.NeighborhoodPostHandler;
import com.shrodinger.common.exception.handler.UserHandler;
import com.shrodinger.common.jwt.SecurityUtil;
import com.shrodinger.common.response.ApiResponse;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.common.response.status.SuccessStatus;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodComment.CommentResponseDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodComment.CreateCommentRequestDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodPost.NeighborhoodPostResponseDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodComment;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodHeart;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.repository.NeighborhoodCommentRepository;
import com.shrodinger.domain.neighborhood.neighborhoodpost.repository.NeighborhoodPostRepository;
import com.shrodinger.domain.user.entity.Member;
import com.shrodinger.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NeighborhoodCommentService {

    private final NeighborhoodCommentRepository neighborhoodCommentRepository;
    private final NeighborhoodPostRepository neighborhoodPostRepository;
    private final MemberRepository memberRepository;


    @Transactional(readOnly = true)
    public List<CommentResponseDTO> getAllCommentsByPostId(Long postId) {
        Member currentUser = getMemberFromToken();
        List<CommentResponseDTO> commentResponseDTOS = neighborhoodCommentRepository.findAllByNeighborhoodPostId(postId)
                .stream()
                .map(comment -> CommentResponseDTO.from(comment, currentUser))
                .collect(Collectors.toList());

        return commentResponseDTOS;
    }

    public CommentResponseDTO createComment(CreateCommentRequestDTO createClubRequestDTO,
                                            Long postId) {
        Member member = getMemberFromToken();
        NeighborhoodPost post = neighborhoodPostRepository.findById(postId).orElseThrow(
                () -> new NeighborhoodPostHandler(ErrorStatus.NEIGHBORHOOD_POST_NOT_EXIST));
        NeighborhoodComment comment = NeighborhoodComment.builder()
                .content(createClubRequestDTO.getComment())
                .isDeleted(false)
                .neighborhoodPost(post)
                .member(member)
                .build();
        post.upCommentCount();
        neighborhoodPostRepository.save(post);
        neighborhoodCommentRepository.save(comment);
        CommentResponseDTO commentResponseDTO = CommentResponseDTO.from(comment,member);
        return commentResponseDTO;
    }

    public List<NeighborhoodPostResponseDTO> getCommentedPosts(){
        Member member = getMemberFromToken();
        List<NeighborhoodComment> neighborhoodComments = neighborhoodCommentRepository.findAllByMember(member);
        return neighborhoodComments.stream()
                .map(NeighborhoodComment :: getNeighborhoodPost)
                .map(NeighborhoodPostResponseDTO::from)
                .collect(Collectors.toList());
    }
    private Member getMemberFromToken() {
        String userEmail = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserHandler(ErrorStatus._UNAUTHORIZED));
        return member;
    }
}
