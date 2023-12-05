package com.shrodinger.domain.diary.controller;

import com.shrodinger.common.response.ApiResponse;
import com.shrodinger.common.response.status.SuccessStatus;
import com.shrodinger.domain.diary.service.DiaryHeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary/likes")
public class DiaryHeartController {
    private final DiaryHeartService diaryHeartService;
    @PostMapping("{diaryId}")
    public ApiResponse addLike(@PathVariable("diaryId") Long diaryId) {
        if (diaryHeartService.addLike(diaryId)){
            return ApiResponse.of(SuccessStatus.CREATE_NEIGHBORHOOD_POST_HEART_SUCCESS, "좋아요 생성 성공");
        } else{
            return ApiResponse.of(SuccessStatus.CANCEL_NEIGHBORHOOD_POST_HEART_SUCCESS, "좋아요 취소 성공");
        }
    }
}
