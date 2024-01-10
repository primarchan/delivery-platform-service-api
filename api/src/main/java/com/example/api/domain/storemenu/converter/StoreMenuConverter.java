package com.example.api.domain.storemenu.converter;

import com.example.api.annotation.Converter;
import com.example.api.common.error.ErrorCode;
import com.example.api.common.exception.ApiException;
import com.example.api.domain.storemenu.controller.model.request.StoreMenuRegisterRequest;
import com.example.api.domain.storemenu.controller.model.response.StoreMenuResponse;
import com.example.db.storemenu.StoreMenuEntity;

import java.util.Optional;

@Converter
public class StoreMenuConverter {

    public StoreMenuEntity toEntity(StoreMenuRegisterRequest storeMenuRegisterRequest){

        return Optional.ofNullable(storeMenuRegisterRequest)
                .map(request -> StoreMenuEntity.builder()
                        .storeId(storeMenuRegisterRequest.getStoreId())
                        .name(storeMenuRegisterRequest.getName())
                        .amount(storeMenuRegisterRequest.getAmount())
                        .thumbnailUrl(storeMenuRegisterRequest.getThumbnailUrl())
                        .build())
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }


    public StoreMenuResponse toResponse(StoreMenuEntity storeMenuEntity){

        return Optional.ofNullable(storeMenuEntity)
                .map(it -> StoreMenuResponse.builder()
                        .id(storeMenuEntity.getId())
                        .name(storeMenuEntity.getName())
                        .storeId(storeMenuEntity.getStoreId())
                        .amount(storeMenuEntity.getAmount())
                        .status(storeMenuEntity.getStatus())
                        .thumbnailUrl(storeMenuEntity.getThumbnailUrl())
                        .likeCount(storeMenuEntity.getLikeCount())
                        .sequence(storeMenuEntity.getSequence())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

}
