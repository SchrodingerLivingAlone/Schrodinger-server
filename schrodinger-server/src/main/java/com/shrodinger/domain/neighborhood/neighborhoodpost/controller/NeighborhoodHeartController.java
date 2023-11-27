package com.shrodinger.domain.neighborhood.neighborhoodpost.controller;


import com.shrodinger.common.response.ApiResponse;
import com.shrodinger.common.response.status.SuccessStatus;
import com.shrodinger.domain.neighborhood.neighborhoodpost.service.NeighborhoodHeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class NeighborhoodHeartController {
    private final NeighborhoodHeartService neighborhoodHeartService;

    @CrossOrigin(origins = "*")
    @PostMapping("{boothId}")
    public ApiResponse addLike(@PathVariable("boothId") Long boothId) {
        if (neighborhoodHeartService.addLike(boothId)) {
            return ApiResponse.of(SuccessStatus.CREATE_NEIGHBORHOOD_POST_HEART_SUCCESS, "좋아요 생성 성공");
        } else {
            return ApiResponse.of(SuccessStatus.CANCEL_NEIGHBORHOOD_POST_HEART_SUCCESS, "좋아요 취소 성공");
        }
    }

    @GetMapping("/posts")
    public ApiResponse getLikedPosts(){
        return ApiResponse.of(SuccessStatus.GET_NEIGHBORHOOD_POST_BY_LIKE, neighborhoodHeartService.getLikedPosts());
    }

}
