package com.shrodinger.domain.diary.controller;

import com.shrodinger.common.response.ApiResponse;
import com.shrodinger.common.response.status.SuccessStatus;
import com.shrodinger.domain.diary.dto.DiaryCommentResponseDTO;
import com.shrodinger.domain.diary.service.DiaryCommentService;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodComment.CommentResponseDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodComment.CreateCommentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary/comments")
public class DiaryCommentController {
    private final DiaryCommentService diaryCommentService;

    @GetMapping("/{diaryId}")
    public ApiResponse getDiaryComments(@PathVariable Long diaryId) {
        List<DiaryCommentResponseDTO> commentResponseDTOS = diaryCommentService.getAllCommentsByDiaryId(
                diaryId);
        return ApiResponse.of(SuccessStatus.GET_NEIGHBORHOOD_COMMENTS_SUCCESS, commentResponseDTOS);
    }

    @PostMapping("/{diaryId}")
    public ApiResponse createDiaryComments(@PathVariable Long diaryId,
                                      @RequestBody CreateCommentRequestDTO createCommentRequestDto
    ) {
        DiaryCommentResponseDTO diaryCommentResponseDTO = diaryCommentService.createComment(createCommentRequestDto, diaryId);
        return ApiResponse.of(SuccessStatus.CREAT_NEIGHBORHOOD_POST_COMMENT_SUCCESS, diaryCommentResponseDTO);
    }
}
