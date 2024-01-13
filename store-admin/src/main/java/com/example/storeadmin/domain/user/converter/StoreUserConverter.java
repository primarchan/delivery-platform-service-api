package com.example.storeadmin.domain.user.converter;

import com.example.db.store.StoreEntity;
import com.example.db.storeuser.StoreUserEntity;
import com.example.storeadmin.annotation.Converter;
import com.example.storeadmin.domain.authorization.model.UserSession;
import com.example.storeadmin.domain.user.controller.model.request.StoreUserRegisterRequest;
import com.example.storeadmin.domain.user.controller.model.response.StoreUserResponse;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class StoreUserConverter {

    public StoreUserEntity toEntity(StoreUserRegisterRequest storeUserRegisterRequest, StoreEntity storeEntity) {

        return StoreUserEntity.builder()
                .email(storeUserRegisterRequest.getEmail())
                .password(storeUserRegisterRequest.getPassword())
                .role(storeUserRegisterRequest.getRole())
                .storeId(storeEntity.getId())
                .build();

    }

    public StoreUserResponse toResponse(StoreUserEntity storeUserEntity, StoreEntity storeEntity) {

        return StoreUserResponse.builder()
                .user(
                        StoreUserResponse.UserResponse.builder()
                                .id(storeUserEntity.getId())
                                .email(storeUserEntity.getEmail())
                                .status(storeUserEntity.getStatus())
                                .role(storeUserEntity.getRole())
                                .registeredAt(storeUserEntity.getRegisteredAt())
                                .unregisteredAt(storeUserEntity.getUnregisteredAt())
                                .lastLoginAt(storeUserEntity.getLastLoginAt())
                                .build()
                )
                .store(
                        StoreUserResponse.StoreResponse.builder()
                                .id(storeEntity.getId())
                                .name(storeEntity.getName())
                                .build()
                )
                .build();
    }

    public StoreUserResponse toResponse(UserSession userSession) {

        return StoreUserResponse.builder()
                .user(
                        StoreUserResponse.UserResponse.builder()
                                .id(userSession.getUserId())
                                .email(userSession.getEmail())
                                .status(userSession.getStatus())
                                .role(userSession.getRole())
                                .registeredAt(userSession.getRegisteredAt())
                                .unregisteredAt(userSession.getUnregisteredAt())
                                .lastLoginAt(userSession.getLastLoginAt())
                                .build()
                )
                .store(
                        StoreUserResponse.StoreResponse.builder()
                                .id(userSession.getStoreId())
                                .name(userSession.getStoreName())
                                .build()
                )
                .build();
    }

}
