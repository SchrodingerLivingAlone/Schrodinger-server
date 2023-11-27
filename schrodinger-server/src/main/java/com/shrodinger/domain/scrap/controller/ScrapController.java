package com.shrodinger.domain.scrap.controller;

import com.shrodinger.common.response.ApiResponse;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.common.response.status.SuccessStatus;
import com.shrodinger.domain.scrap.dto.CreateScrapRequestDTO;
import com.shrodinger.domain.scrap.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.shrodinger.common.util.ValidationUtils.getValidationErrors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/scraps")
public class ScrapController {
    private final ScrapService scrapService;
    @PostMapping("{postId}")
    public ApiResponse createScrap(@PathVariable Long postId){
        return ApiResponse.of(SuccessStatus.CREATE_SCRAP_SUCCESS,scrapService.createScrap(postId));
    }

    @GetMapping("")
    public ApiResponse getScraps(){
        return ApiResponse.of(SuccessStatus.GET_SCRAPS_SUCCESS, scrapService.getAllScraps());
    }


}
