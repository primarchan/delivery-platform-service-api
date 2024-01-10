package com.example.api.domain.store.controller;

import com.example.api.common.api.Api;
import com.example.api.domain.store.business.StoreBusiness;
import com.example.api.domain.store.controller.model.request.StoreRegisterRequest;
import com.example.api.domain.store.controller.model.response.StoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/open-api/store")
@RequiredArgsConstructor
public class StoreOpenApiController {

    private final StoreBusiness storeBusiness;

    @PostMapping("/register")
    public Api<StoreResponse> registerStore(@Valid @RequestBody Api<StoreRegisterRequest> storeRegisterRequest) {
       StoreResponse storeResponse = storeBusiness.register(storeRegisterRequest.getBody());

       return Api.OK(storeResponse);
    }

}
