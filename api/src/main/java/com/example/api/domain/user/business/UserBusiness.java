package com.example.api.domain.user.business;

import com.example.api.annotation.Business;
import com.example.api.common.error.ErrorCode;
import com.example.api.common.exception.ApiException;
import com.example.api.domain.token.business.TokenBusiness;
import com.example.api.domain.token.controller.model.response.TokenResponse;
import com.example.api.domain.user.controller.model.request.UserLoginRequest;
import com.example.api.domain.user.controller.model.request.UserRegisterRequest;
import com.example.api.domain.user.controller.model.response.UserResponse;
import com.example.api.domain.user.converter.UserConverter;
import com.example.api.domain.user.service.UserService;
import com.example.db.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;
import java.util.Optional;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    /**
     * 사용자에 대한 가입 처리 로직
     */
    public UserResponse register(UserRegisterRequest userRegisterRequest) {
        /*
        UserEntity userEntity = userConverter.toEntity(userRegisterRequest);
        UserEntity savedUserEntity = userService.register(userEntity);
        UserResponse userResponse = userConverter.toResponse(savedUserEntity);

        return userResponse;
         */


        return Optional.ofNullable(userRegisterRequest)
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest is NULL"));
    }

    /**
     * 사용자 로그인 대한 확인 및 처리 로직
     */
    public TokenResponse login(UserLoginRequest userLoginRequest) {
        UserEntity userEntity = userService.login(userLoginRequest.getEmail(), userLoginRequest.getPassword());

        // JWT 토큰 생성
        TokenResponse tokenResponse = tokenBusiness.issueToken(userEntity);

        return tokenResponse;
    }

    public UserResponse me(Long userId) {
        UserEntity userEntity = userService.getUserWithThrow(userId);
        UserResponse userResponse = userConverter.toResponse(userEntity);

        return userResponse;
    }
}
