package com.shrodinger.domain.neighborhood.neighborhoodpost.controller;

import com.shrodinger.common.response.ApiResponse;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.common.response.status.SuccessStatus;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodPost.CreateNeighborhoodPostRequestDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodPost.UpdateNeighborhoodPostRequestDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPostCategory;
import com.shrodinger.domain.neighborhood.neighborhoodpost.service.NeighborhoodPostService;
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
@RequestMapping("/api/neighborhood/posts")
public class NeighborhoodPostController {

    private final NeighborhoodPostService neighborhoodPostService;

    /*
     * [동네정보] 사용자 위치 정보
     */
    @GetMapping("/location")
    public ApiResponse getLocation() {
        return ApiResponse.onSuccess(neighborhoodPostService.getUserLocation());
    }

    @PostMapping("")
    public ApiResponse createPost(@Validated @RequestPart CreateNeighborhoodPostRequestDTO createjson, @RequestPart("file") List<MultipartFile> multipartFile,Errors errors) {
        if (errors.hasErrors()) {
            return ApiResponse.onFailure(ErrorStatus.NEIGHBORHOOD_POST_ARGUMENT_ERROR.getCode(), ErrorStatus.NEIGHBORHOOD_POST_ARGUMENT_ERROR.getMessage(), getValidationErrors(errors));
        }
        return ApiResponse.of(SuccessStatus.CREATE_NEIGHBORHOOD_POST_SUCCESS, neighborhoodPostService.createNeighborhoodPost(createjson,multipartFile ));
    }

    @GetMapping("")
    public ApiResponse getAllPostsBySortOption(@RequestParam("sortBy") int sort, @RequestParam("category") int category) {
        NeighborhoodPostCategory neighborhoodPostCategory = NeighborhoodPostCategory.valueOf(category);
        return ApiResponse.of(SuccessStatus.GET_NEIGHBORHOOD_POSTS_BY_SORT, neighborhoodPostService.getAllPosts(sort, neighborhoodPostCategory));
    }

    @GetMapping("/{post_id}")
    public ApiResponse getPost(@PathVariable("post_id") int post_id) {
        return ApiResponse.of(SuccessStatus.GET_NEIGHBORHOOD_POST, neighborhoodPostService.getPost(post_id));
    }

    @PutMapping("/{post_id}")
    public ApiResponse updatePost(@PathVariable("post_id") int post_id,
                                  @Validated @RequestBody UpdateNeighborhoodPostRequestDTO updateNeighborhoodPostRequestDTO, Errors errors) {
        if (errors.hasErrors()) {
            return ApiResponse.onFailure(ErrorStatus.TRANSACTION_ARGUMENT_ERROR.getCode(), ErrorStatus.TRANSACTION_ARGUMENT_ERROR.getMessage(), getValidationErrors(errors));
        }
        return ApiResponse.of(SuccessStatus.UPDATE_NEIGHBORHOOD_POST_SUCCESS, neighborhoodPostService.updatePost(post_id, updateNeighborhoodPostRequestDTO));
    }

    @DeleteMapping("/{post_id}")
    public ApiResponse deletePost(@PathVariable("post_id") int post_id) {
        neighborhoodPostService.deletePost(post_id);
        return ApiResponse.of(SuccessStatus.DELETE_NEIGHBORHOOD_POST_SUCCESS, "삭제 성공");
    }

    @GetMapping("/search")
    public ApiResponse getAllPostsByKeyword(@RequestParam("keyword") String keyword) {
        return ApiResponse.of(SuccessStatus.GET_NEIGHBORHOOD_POSTS_BY_SORT, neighborhoodPostService.getPostsByKeyword(keyword));
    }


}
