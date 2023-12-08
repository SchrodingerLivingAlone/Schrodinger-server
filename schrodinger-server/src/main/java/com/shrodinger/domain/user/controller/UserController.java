package com.shrodinger.domain.user.controller;

import com.shrodinger.common.jwt.JwtTokenProvider;
import com.shrodinger.common.response.ApiResponse;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.common.response.status.SuccessStatus;
import com.shrodinger.domain.neighborhood.neighborhoodpost.service.NeighborhoodPostService;
import com.shrodinger.domain.user.dto.UpdateLocationRequestDTO;
import com.shrodinger.domain.user.dto.UserLoginRequestDTO;
import com.shrodinger.domain.user.dto.UserSignUpRequestDto;
import com.shrodinger.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService usersService;
    private final NeighborhoodPostService neighborhoodPostService;

    @PostMapping("/sign-up")
    public ApiResponse signUp(@Valid @ModelAttribute UserSignUpRequestDto userSignUpRequestDto,
                              Errors errors) {
        if (errors.hasErrors()) {
            return ApiResponse.ofFailure(ErrorStatus.MEMBER_SIGNUP_ERROR, getValidationErrorList(errors));
        }
        return usersService.signUp(userSignUpRequestDto);
    }

    @CrossOrigin
    @PostMapping("/login")
    public ApiResponse login(@Validated @RequestBody UserLoginRequestDTO userLoginRequestDTO,
                             Errors errors) {
        if (errors.hasErrors()) {
            return ApiResponse.ofFailure(ErrorStatus.MEMBER_SIGNUP_ERROR, getValidationErrorList(errors));
        }
        return usersService.login(userLoginRequestDTO);
    }

    @GetMapping("/profile")
    public ApiResponse getUserProfile() {
        return ApiResponse.of(SuccessStatus.GET_PROFILE_SUCCESS, usersService.getUserProfile());
    }

    @GetMapping("/posts")
    public ApiResponse getWrittenPosts(){
        return ApiResponse.of(SuccessStatus.GET_NEIGHBORHOOD_MY_WRITTEN_POSTS, neighborhoodPostService.getWrittenPosts());
    }

    @PutMapping("/location")
    public ApiResponse updateLocation(@Validated @RequestBody UpdateLocationRequestDTO updateLocationDTO, Errors errors) {
        if (errors.hasErrors()){
            return ApiResponse.ofFailure(ErrorStatus.LOCATION_ARGUMENT_ERROR, getValidationErrorList(errors));
        }
        return ApiResponse.of(SuccessStatus.LOCATION_UPDATE_SUCCESS, usersService.updateLocation(updateLocationDTO));
    }


    private Map<String, String> getValidationErrors(Errors errors) {
        return errors.getAllErrors().stream()
                .collect(Collectors.toMap(
                        error -> ((FieldError) error).getField(),
                        error -> error.getDefaultMessage()
                ));
    }
    private List<String> getValidationErrorList(Errors errors) {
        return errors.getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
    }



}
