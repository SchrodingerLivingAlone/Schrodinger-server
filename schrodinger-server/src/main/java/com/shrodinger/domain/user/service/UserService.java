package com.shrodinger.domain.user.service;

import com.shrodinger.common.exception.handler.NeighborhoodHandler;
import com.shrodinger.common.exception.handler.UserHandler;
import com.shrodinger.common.jwt.JwtTokenProvider;
import com.shrodinger.common.jwt.SecurityUtil;
import com.shrodinger.common.jwt.TokenInfo;
import com.shrodinger.common.response.ApiResponse;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.common.response.status.SuccessStatus;
import com.shrodinger.common.s3.AwsS3Service;
import com.shrodinger.domain.neighborhood.neighborhood.entity.Neighborhood;
import com.shrodinger.domain.neighborhood.neighborhood.repository.NeighborhoodRepository;
import com.shrodinger.domain.user.dto.UserLoginRequestDTO;
import com.shrodinger.domain.user.dto.UserProfileResponseDTO;
import com.shrodinger.domain.user.dto.UserResponseDTO;
import com.shrodinger.domain.user.dto.UserSignUpRequestDto;
import com.shrodinger.domain.user.entity.Authority;
import com.shrodinger.domain.user.entity.Member;
import com.shrodinger.domain.user.repository.MemberRepository;

import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AwsS3Service awsS3Service;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final NeighborhoodRepository neighborhoodRepository;

    public ApiResponse signUp(UserSignUpRequestDto signUp) {
        if (memberRepository.existsByEmail(signUp.getEmail())) {
            throw new UserHandler(ErrorStatus.EMAIL_ALREADY_EXIST);
        }
        if (!neighborhoodRepository.existsByCityAndGuAndDong(signUp.getCity()
                ,signUp.getGu(), signUp.getDong())){
            throw new NeighborhoodHandler(ErrorStatus.NEIGHBORHOOD_NOT_EXIST);
        }
        Neighborhood neighborhood = neighborhoodRepository.findByCityAndGuAndDong(signUp.getCity(), signUp.getGu(), signUp.getDong());
        if (signUp.getMultipartFiles() == null){
            Member user = Member.builder()
                    .email(signUp.getEmail())
                    .password(passwordEncoder.encode(signUp.getPassword()))
                    .nickname(signUp.getNickname())
                    .profileImage("https://schrodinger-cau.s3.ap-northeast-2.amazonaws.com/istockphoto-1214428300-612x612.jpeg")
                    .roles(Collections.singletonList(Authority.ROLE_USER.name()))
                    .neighborhood(neighborhood)
                    .build();
            memberRepository.save(user);
        }
        else {
            String profileUrl = awsS3Service.uploadImage((List<MultipartFile>) signUp.getMultipartFiles()).get(0);
            Member user = Member.builder()
                    .email(signUp.getEmail())
                    .password(passwordEncoder.encode(signUp.getPassword()))
                    .nickname(signUp.getNickname())
                    .roles(Collections.singletonList(Authority.ROLE_USER.name()))
                    .neighborhood(neighborhood)
                    .profileImage(profileUrl)
                    .build();
            memberRepository.save(user);
        }
        return ApiResponse.of(SuccessStatus._SIGNUP_SUCCESS, "회원가입 성공!");
    }

    public ApiResponse login(UserLoginRequestDTO userLoginRequestDTO) {
        if (memberRepository.findByEmail(userLoginRequestDTO.getEmail()).orElse(null) == null) {
            throw new UserHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }
        try {
            // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
            // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
            UsernamePasswordAuthenticationToken authenticationToken = userLoginRequestDTO.toAuthentication();

            // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
            // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
            Authentication authentication = authenticationManagerBuilder.getObject()
                    .authenticate(authenticationToken);

            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
            Member member = memberRepository.findByEmail(userLoginRequestDTO.getEmail())
                    .orElseThrow();
            return ApiResponse.of(SuccessStatus._LOGIN_SUCCESS, UserResponseDTO.builder().tokenInfo(tokenInfo).nickName(member.getNickname())
                    .build());
        } catch (AuthenticationException e) {
            // Handle authentication failure, e.g., incorrect password
            throw new UserHandler(ErrorStatus.MEMBER_LOGIN_FAILURE);
        }
    }

    /*
    public ResponseEntity<?> reissue(UserRequestDto.Reissue reissue) {
        // 1. Refresh Token 검증
        if (!jwtTokenProvider.validateToken(reissue.getRefreshToken())) {
            return response.fail("Refresh Token 정보가 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(reissue.getAccessToken());

        // 3. Redis 에서 User email 을 기반으로 저장된 Refresh Token 값을 가져옵니다.
        String refreshToken = (String)redisTemplate.opsForValue().get("RT:" + authentication.getName());
        // (추가) 로그아웃되어 Redis 에 RefreshToken 이 존재하지 않는 경우 처리
        if(ObjectUtils.isEmpty(refreshToken)) {
            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }
        if(!refreshToken.equals(reissue.getRefreshToken())) {
            return response.fail("Refresh Token 정보가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 4. 새로운 토큰 생성
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        // 5. RefreshToken Redis 업데이트
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return response.success(tokenInfo, "Token 정보가 갱신되었습니다.", HttpStatus.OK);
    }

     */


    /*
    public ResponseEntity<?> authority() {
        String userEmail = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("No authentication information."));
        // add ROLE_ADMIN
        member.getRoles().add(Authority.ROLE_ADMIN.name());
        memberRepository.save(member);

        return response.success();
    }
     */
    public UserProfileResponseDTO getUserProfile(){
        Member member = getMemberFromToken();
        return UserProfileResponseDTO.from(member);
    }

    private Member getMemberFromToken() {
        String userEmail = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserHandler(ErrorStatus._UNAUTHORIZED));
        return member;
    }
    public Member findByEmail(String email) {
        Member member =
                memberRepository
                        .findByEmail(email)
                        .orElseThrow();
        return member;
    }

}
