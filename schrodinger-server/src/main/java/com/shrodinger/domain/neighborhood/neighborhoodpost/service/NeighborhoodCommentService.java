package com.shrodinger.domain.neighborhood.neighborhoodpost.service;

import com.shrodinger.common.exception.handler.NeighborhoodPostHandler;
import com.shrodinger.common.exception.handler.UserHandler;
import com.shrodinger.common.jwt.SecurityUtil;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodComment.CommentResponseDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodComment.CreateCommentRequestDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodComment;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.repository.NeighborhoodCommentRepository;
import com.shrodinger.domain.neighborhood.neighborhoodpost.repository.NeighborhoodPostRepository;
import com.shrodinger.domain.user.entity.Member;
import com.shrodinger.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<CommentResponseDTO> commentResponseDTOS = neighborhoodCommentRepository.findAllByNeighborhoodPostId(postId)
                .stream().map(CommentResponseDTO::from).collect(
                        Collectors.toList());
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
        neighborhoodCommentRepository.save(comment);
        CommentResponseDTO commentResponseDTO = CommentResponseDTO.from(comment);
        return commentResponseDTO;
    }
    private Member getMemberFromToken() {
        String userEmail = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserHandler(ErrorStatus._UNAUTHORIZED));
        return member;
    }
}
