package com.example.api.domain.user.business;

import com.example.api.annotation.Business;
import com.example.api.common.error.ErrorCode;
import com.example.api.common.exception.ApiException;
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
    public UserResponse register(UserRegisterRequest request) {
        /*
        UserEntity entity = userConverter.toEntity(request);
        UserEntity savedEntity = userService.register(entity);
        UserResponse response = userConverter.toResponse(savedEntity);
         */

        return Optional.ofNullable(request)
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest is NULL"));
    }

}
