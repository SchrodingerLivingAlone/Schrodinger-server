package com.shrodinger.domain.diary.controller;

import com.shrodinger.common.response.ApiResponse;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.common.response.status.SuccessStatus;
import com.shrodinger.domain.diary.dto.CreateDiaryRequestDTO;
import com.shrodinger.domain.diary.service.DiaryService;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodPost.CreateNeighborhoodPostRequestDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.shrodinger.common.util.ValidationUtils.getValidationErrors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping("")
    public ApiResponse createDiary(@Validated @ModelAttribute CreateDiaryRequestDTO creatediary, Errors errors) {
        if (errors.hasErrors()) {
            return ApiResponse.ofFailure(ErrorStatus.NEIGHBORHOOD_POST_ARGUMENT_ERROR, getValidationErrors(errors));
        }
        return ApiResponse.of(SuccessStatus.CREATE_DIARY_SUCCESS, diaryService.createDiary(creatediary));
    }

    @GetMapping("")
    public ApiResponse getAllDiary() {
        return ApiResponse.of(SuccessStatus.GET_NEIGHBORHOOD_POSTS_BY_SORT, diaryService.getAllDiary());
    }
}
