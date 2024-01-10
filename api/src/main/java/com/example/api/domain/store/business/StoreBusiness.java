package com.example.api.domain.store.business;

import com.example.api.annotation.Business;
import com.example.api.common.error.ErrorCode;
import com.example.api.common.exception.ApiException;
import com.example.api.domain.store.controller.model.request.StoreRegisterRequest;
import com.example.api.domain.store.controller.model.response.StoreResponse;
import com.example.api.domain.store.converter.StoreConverter;
import com.example.api.domain.store.service.StoreService;
import com.example.db.store.StoreEntity;
import com.example.db.store.enums.StoreCategory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class StoreBusiness {

    private final StoreService storeService;

    private final StoreConverter storeConverter;

    public StoreResponse register(StoreRegisterRequest storeRegisterRequest) {
        /*
        StoreEntity storeEntity = storeConverter.toEntity(storeRegisterRequest);
        StoreEntity savedStoreEntity = storeService.register(storeEntity);
        StoreResponse storeResponse = storeConverter.toResponse(savedStoreEntity);

        return storeResponse;
         */

        return Optional.ofNullable(storeRegisterRequest)
                .map(storeConverter::toEntity)
                .map(storeService::register)
                .map(storeConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<StoreResponse> searchByCategory(StoreCategory storeCategory) {
        List<StoreEntity> storeEntities = storeService.searchByCategory(storeCategory);

        return storeEntities.stream()
                .map(storeConverter::toResponse)
                .collect(Collectors.toList());
    }

}
