package com.shrodinger.domain.user.controller;

import com.shrodinger.common.jwt.Helper;
import com.shrodinger.common.jwt.JwtTokenProvider;
import com.shrodinger.domain.user.dto.Response;
import com.shrodinger.domain.user.dto.UserLoginRequestDTO;
import com.shrodinger.domain.user.dto.UserSignUpRequestDto;
import com.shrodinger.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService usersService;
    private final Response response;


    @CrossOrigin
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Validated @RequestBody UserSignUpRequestDto signUp,
            Errors errors) {
        /*if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
         */
        return usersService.signUp(signUp);
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody UserLoginRequestDTO userLoginRequestDTO,
            Errors errors) {
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return usersService.login(userLoginRequestDTO);
    }
}
