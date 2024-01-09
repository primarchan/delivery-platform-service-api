package com.example.api.domain.user.business;

import com.example.api.annotation.Business;
import com.example.api.common.error.ErrorCode;
import com.example.api.common.exception.ApiException;
import com.example.api.domain.user.controller.model.request.UserLoginRequest;
import com.example.api.domain.user.controller.model.request.UserRegisterRequest;
import com.example.api.domain.user.controller.model.response.UserResponse;
import com.example.api.domain.user.converter.UserConverter;
import com.example.api.domain.user.service.UserService;
import com.example.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;

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
    public UserResponse login(UserLoginRequest userLoginRequest) {
        UserEntity userEntity = userService.login(userLoginRequest.getEmail(), userLoginRequest.getPassword());

        // 사용자가 존재하지 않으면 예외 Throw

        // TODO: JWT 토큰 생성 로직으로 변경

        return userConverter.toResponse(userEntity);
    }
}
