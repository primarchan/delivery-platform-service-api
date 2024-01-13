package com.example.storeadmin.domain.user.business;

import com.example.db.store.StoreEntity;
import com.example.db.store.StoreRepository;
import com.example.db.store.enums.StoreStatus;
import com.example.db.storeuser.StoreUserEntity;
import com.example.storeadmin.annotation.Business;
import com.example.storeadmin.common.error.ErrorCode;
import com.example.storeadmin.common.exception.ApiException;
import com.example.storeadmin.domain.user.controller.model.request.StoreUserRegisterRequest;
import com.example.storeadmin.domain.user.controller.model.response.StoreUserResponse;
import com.example.storeadmin.domain.user.converter.StoreUserConverter;
import com.example.storeadmin.domain.user.service.StoreUserService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class StoreUserBusiness {

    private final StoreUserService storeUserService;
    private final StoreUserConverter storeUserConverter;
    private final StoreRepository storeRepository;  // TODO: Store Service 로 이관 예정

    public StoreUserResponse register(StoreUserRegisterRequest storeUserRegisterRequest) {
        StoreEntity storeEntity = storeRepository.findFirstByNameAndStatusOrderByIdDesc(storeUserRegisterRequest.getStoreName(), StoreStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

        StoreUserEntity storeUserEntity = storeUserConverter.toEntity(storeUserRegisterRequest, storeEntity);
        StoreUserEntity registeredStoreUserEntity = storeUserService.register(storeUserEntity);
        StoreUserResponse storeUserResponse = storeUserConverter.toResponse(registeredStoreUserEntity, storeEntity);

        return storeUserResponse;
    }

}
