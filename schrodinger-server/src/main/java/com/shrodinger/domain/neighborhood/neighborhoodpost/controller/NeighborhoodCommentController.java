package com.shrodinger.domain.neighborhood.neighborhoodpost.controller;

import com.shrodinger.common.response.ApiResponse;
import com.shrodinger.common.response.status.SuccessStatus;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodComment.CommentResponseDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodComment.CreateCommentRequestDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodComment;
import com.shrodinger.domain.neighborhood.neighborhoodpost.service.NeighborhoodCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.shrodinger.common.jwt.SecurityUtil.getCurrentUserEmail;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class NeighborhoodCommentController {
    private final NeighborhoodCommentService neighborhoodCommentService;
    @GetMapping("/{postId}")
    public ApiResponse getPostComments(@PathVariable Long postId) {
        List<CommentResponseDTO> commentResponseDTOS = neighborhoodCommentService.getAllCommentsByPostId(
                postId);
        return ApiResponse.of(SuccessStatus.GET_NEIGHBORHOOD_COMMENTS_SUCCESS,commentResponseDTOS);
    }

    @CrossOrigin
    @PostMapping("/{postId}")
    public ApiResponse createComments(@PathVariable Long postId,
                                      @RequestBody CreateCommentRequestDTO createCommentRequestDto
    ) {
        CommentResponseDTO commentResponseDTO = neighborhoodCommentService.createComment(createCommentRequestDto, postId);
        return ApiResponse.of(SuccessStatus.CREAT_NEIGHBORHOOD_POST_COMMENT_SUCCESS, commentResponseDTO);
    }
    @GetMapping("/posts")
    public ApiResponse getCommentedPosts(){
        return ApiResponse.of(SuccessStatus.GET_NEIGHBORHOOD_POST_BY_COMMENT, neighborhoodCommentService.getCommentedPosts());
    }
}
