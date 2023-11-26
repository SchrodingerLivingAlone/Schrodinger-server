package com.shrodinger.domain.neighborhood.neighborhoodpost.controller;

import com.shrodinger.common.response.ApiResponse;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.common.response.status.SuccessStatus;
import com.shrodinger.domain.neighborhood.neighborhood.entity.Neighborhood;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.CreateNeighborhoodPostRequestDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostCategory;
import com.shrodinger.domain.neighborhood.neighborhoodpost.service.NeighborhoodPostService;
import com.shrodinger.domain.transaction.dto.CreateTransactionRequestDTO;
import com.shrodinger.domain.user.dto.UserNeighborhoodResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

import static com.shrodinger.common.util.ValidationUtils.getValidationErrors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/neighborhood")
public class NeighborhoodPostController {

    private final NeighborhoodPostService neighborhoodPostService;
    /*
     * [동네정보] 사용자 위치 정보
     */
    @GetMapping("/location")
    public ApiResponse getLocation() {
        return ApiResponse.onSuccess(neighborhoodPostService.getUserLocation());
    }

    @PostMapping("/posts")
    public ApiResponse createPost(@Validated @RequestBody CreateNeighborhoodPostRequestDTO createNeighborhoodPostRequestDTO ,Errors errors) {
        if(errors.hasErrors()){
            return ApiResponse.onFailure(ErrorStatus.TRANSACTION_ARGUMENT_ERROR.getCode(), ErrorStatus.TRANSACTION_ARGUMENT_ERROR.getMessage(), getValidationErrors(errors));
        }
        return ApiResponse.of(SuccessStatus.CREATE_NEIGHBORHOOD_POST_SUCCESS ,neighborhoodPostService.createNeighborhoodPost(createNeighborhoodPostRequestDTO));
    }

    @GetMapping("/posts")
    public ApiResponse getAllPostsBySortOption(@RequestParam("sortBy") int sort, @RequestParam("category") int category) {
        NeighborhoodPostCategory neighborhoodPostCategory = NeighborhoodPostCategory.valueOf(category);
        return ApiResponse.of(SuccessStatus.GET_NEIGHBORHOOD_POSTS_BY_SORT,neighborhoodPostService.getAllPosts(sort,neighborhoodPostCategory));
    }


}
