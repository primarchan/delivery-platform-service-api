package com.example.storeadmin.domain.user.controller;

import com.example.storeadmin.common.api.Api;
import com.example.storeadmin.common.error.ErrorCode;
import com.example.storeadmin.common.exception.ApiException;
import com.example.storeadmin.domain.user.business.StoreUserBusiness;
import com.example.storeadmin.domain.user.controller.model.request.StoreUserRegisterRequest;
import com.example.storeadmin.domain.user.controller.model.response.StoreUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/open-api/store-user")
@RequiredArgsConstructor
public class StoreUserOpenApiController {

    private final StoreUserBusiness storeUserBusiness;

    @PostMapping("")
    public Api<StoreUserResponse> register(@Valid @RequestBody StoreUserRegisterRequest storeUserRegisterRequest) {

        StoreUserResponse storeUserResponse = Optional.ofNullable(storeUserRegisterRequest)
                .map(storeUserBusiness::register)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

        return Api.OK(storeUserResponse);
    }

}
