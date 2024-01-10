package com.example.api.domain.user.controller;

import com.example.api.annotation.UserSession;
import com.example.api.common.api.Api;
import com.example.api.domain.user.business.UserBusiness;
import com.example.api.domain.user.controller.model.response.UserResponse;
import com.example.api.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserBusiness userBusiness;

    /**
     * 요청 파라미터에 @UserSession 어노테이션과 User.class 인 경우,
     * WebConfig 에 등록한 UserSessionResolver 가 동작
     */
    @GetMapping("/me")
    public Api<UserResponse> me(@UserSession User user) {
        UserResponse userResponse = userBusiness.me(user.getId());

        return Api.OK(userResponse);
    }

}
