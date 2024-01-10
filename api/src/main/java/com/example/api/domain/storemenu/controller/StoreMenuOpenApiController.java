package com.example.api.domain.storemenu.controller;

import com.example.api.common.api.Api;
import com.example.api.domain.storemenu.business.StoreMenuBusiness;
import com.example.api.domain.storemenu.controller.model.request.StoreMenuRegisterRequest;
import com.example.api.domain.storemenu.controller.model.response.StoreMenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/store-menu")
public class StoreMenuOpenApiController {

    private final StoreMenuBusiness storeMenuBusiness;

    @PostMapping("/register")
    public Api<StoreMenuResponse> register(@Valid @RequestBody Api<StoreMenuRegisterRequest> storeMenuRegisterRequest){
        StoreMenuResponse storeMenuResponse = storeMenuBusiness.register(storeMenuRegisterRequest.getBody());

        return Api.OK(storeMenuResponse);
    }

}
