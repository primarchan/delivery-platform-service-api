package com.example.api.domain.userorder.controller;

import com.example.api.annotation.UserSession;
import com.example.api.common.api.Api;
import com.example.api.domain.user.model.User;
import com.example.api.domain.userorder.business.UserOrderBusiness;
import com.example.api.domain.userorder.controller.model.request.UserOrderRequest;
import com.example.api.domain.userorder.controller.model.response.UserOrderResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user-order")
@RequiredArgsConstructor
public class UserOrderApiController {

    private final UserOrderBusiness userOrderBusiness;

    /**
     * @apiNote 사용자 주문 API
     */
    @PostMapping("")
    public Api<UserOrderResponse> userOrder(
            @Valid @RequestBody Api<UserOrderRequest> userOrderRequest,
            @Parameter(hidden = true) @UserSession User user
    ) {
        UserOrderResponse userOrderResponse = userOrderBusiness.userOrder(userOrderRequest.getBody(), user);

        return Api.OK(userOrderResponse);
    }

}
