package com.example.storeadmin.domain.storemenu.converter;

import com.example.db.storemenu.StoreMenuEntity;
import com.example.storeadmin.annotation.Converter;
import com.example.storeadmin.domain.storemenu.controller.model.response.StoreMenuResponse;

import java.util.List;
import java.util.stream.Collectors;

@Converter
public class StoreMenuConverter {

    public StoreMenuResponse toResponse(StoreMenuEntity storeMenuEntity) {

        return StoreMenuResponse.builder()
                .id(storeMenuEntity.getId())
                .name(storeMenuEntity.getName())
                .status(storeMenuEntity.getStatus())
                .amount(storeMenuEntity.getAmount())
                .thumbnailUrl(storeMenuEntity.getThumbnailUrl())
                .likeCount(storeMenuEntity.getLikeCount())
                .sequence(storeMenuEntity.getSequence())
                .build();
    }

    public List<StoreMenuResponse> toResponse(List<StoreMenuEntity> storeMenuEntityList) {

        return storeMenuEntityList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
