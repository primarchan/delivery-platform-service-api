package com.example.api.domain.storemenu.business;

import com.example.api.annotation.Business;
import com.example.api.domain.storemenu.controller.model.request.StoreMenuRegisterRequest;
import com.example.api.domain.storemenu.controller.model.response.StoreMenuResponse;
import com.example.api.domain.storemenu.converter.StoreMenuConverter;
import com.example.api.domain.storemenu.service.StoreMenuService;
import com.example.db.storemenu.StoreMenuEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class StoreMenuBusiness {

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;


    public StoreMenuResponse register(StoreMenuRegisterRequest request){
        StoreMenuEntity storeMenuEntity = storeMenuConverter.toEntity(request);
        StoreMenuEntity savedStoreMenuEntity = storeMenuService.register(storeMenuEntity);
        StoreMenuResponse storeMenuResponse = storeMenuConverter.toResponse(savedStoreMenuEntity);

        return storeMenuResponse;
    }

    public List<StoreMenuResponse> searchByStoreId(Long storeId){
        List<StoreMenuEntity> storeMenuEntities = storeMenuService.getStoreMenuByStoreId(storeId);

        return storeMenuEntities.stream()
                .map(storeMenuConverter::toResponse)
                .collect(Collectors.toList());
    }

}
