package com.example.api.domain.user.converter;

import com.example.api.annotation.Converter;
import com.example.api.common.error.ErrorCode;
import com.example.api.common.exception.ApiException;
import com.example.api.domain.user.controller.model.request.UserRegisterRequest;
import com.example.api.domain.user.controller.model.response.UserResponse;
import com.example.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Converter
@RequiredArgsConstructor
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest request) {

        return Optional.ofNullable(request)
                .map(it -> UserEntity.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .address(request.getAddress())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest is NULL"));
    }

    public UserResponse toResponse(UserEntity userEntity) {

        return Optional.ofNullable(userEntity)
                .map(it -> UserResponse.builder()
                        .id(userEntity.getId())
                        .name(userEntity.getName())
                        .email(userEntity.getEmail())
                        .address(userEntity.getAddress())
                        .registeredAt(userEntity.getRegisteredAt())
                        .unregisteredAt(userEntity.getUnregisteredAt())
                        .lastLoginAt(userEntity.getLastLoginAt())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity is NULL"));
    }
}
