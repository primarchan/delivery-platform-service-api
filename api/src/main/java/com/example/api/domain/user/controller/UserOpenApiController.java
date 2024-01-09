package com.example.api.domain.user.controller;

import com.example.api.common.api.Api;
import com.example.api.domain.user.business.UserBusiness;
import com.example.api.domain.user.controller.model.request.UserRegisterRequest;
import com.example.api.domain.user.controller.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/open-api/user")
@RequiredArgsConstructor
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    /**
     * @apiNote 사용자 가입 요청
     */
    @PostMapping("/register")
    public Api<UserResponse> register(@Valid @RequestBody Api<UserRegisterRequest> request) {
        UserResponse response = userBusiness.register(request.getBody());

        return Api.OK(response);
    }

}
