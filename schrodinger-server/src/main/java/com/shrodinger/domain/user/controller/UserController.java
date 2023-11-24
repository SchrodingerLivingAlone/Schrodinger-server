package com.shrodinger.domain.user.controller;

import com.shrodinger.common.exception.handler.UserHandler;
import com.shrodinger.common.jwt.Helper;
import com.shrodinger.common.jwt.JwtTokenProvider;
import com.shrodinger.common.response.ApiResponse;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.domain.user.dto.UserLoginRequestDTO;
import com.shrodinger.domain.user.dto.UserSignUpRequestDto;
import com.shrodinger.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService usersService;

    @CrossOrigin
    @PostMapping("/sign-up")
    public ApiResponse signUp(@Validated @RequestBody UserSignUpRequestDto signUp,
                              Errors errors) {
        if (errors.hasErrors()) {
            return ApiResponse.onFailure(ErrorStatus.MEMBER_SIGNUP_ERROR.getCode(), ErrorStatus.MEMBER_SIGNUP_ERROR.getMessage(), getValidationErrors(errors));
        }
        return usersService.signUp(signUp);
    }

    @CrossOrigin
    @PostMapping("/login")
    public ApiResponse login(@Validated @RequestBody UserLoginRequestDTO userLoginRequestDTO,
                             Errors errors) {

        if (errors.hasErrors()) {
            ApiResponse.onFailure(ErrorStatus.MEMBER_SIGNUP_ERROR.getCode(), ErrorStatus.MEMBER_SIGNUP_ERROR.getMessage(), getValidationErrors(errors));
        }
        return usersService.login(userLoginRequestDTO);
    }

    private Map<String, String> getValidationErrors(Errors errors) {
        return errors.getAllErrors().stream()
                .collect(Collectors.toMap(
                        error -> ((FieldError) error).getField(),
                        error -> error.getDefaultMessage()
                ));
    }


}
