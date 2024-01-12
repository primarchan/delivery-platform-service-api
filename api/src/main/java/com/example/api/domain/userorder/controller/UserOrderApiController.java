package com.example.api.domain.userorder.controller;

import com.example.api.annotation.UserSession;
import com.example.api.common.api.Api;
import com.example.api.domain.user.model.User;
import com.example.api.domain.userorder.business.UserOrderBusiness;
import com.example.api.domain.userorder.controller.model.request.UserOrderRequest;
import com.example.api.domain.userorder.controller.model.response.UserOrderDetailResponse;
import com.example.api.domain.userorder.controller.model.response.UserOrderResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    /**
     * 현재 진행 중인 주문 건 조회
     */
    @GetMapping("/current")
    public Api<List<UserOrderDetailResponse>> currentUserOrder(@Parameter(hidden = true) @UserSession User user) {
        List<UserOrderDetailResponse> userOrderDetailResponseList = userOrderBusiness.getCurrentUserOrder(user);

        return Api.OK(userOrderDetailResponseList);
    }

    /**
     * 과거 완료된 주문 건 조회
     */
    @GetMapping("/history")
    public Api<List<UserOrderDetailResponse>> historyUserOrder(@Parameter(hidden = true) @UserSession User user) {
        List<UserOrderDetailResponse> userOrderDetailResponseList = userOrderBusiness.getHistoryUserOrder(user);

        return Api.OK(userOrderDetailResponseList);
    }

    /**
     * 주문 1건에 대한 내역
     */
    @GetMapping("id/{orderId}")
    public Api<UserOrderDetailResponse> read(
            @Parameter(hidden = true) @UserSession User user,
            @PathVariable Long orderId
    ) {
        UserOrderDetailResponse userOrderDetailResponse = userOrderBusiness.read(user, orderId);

        return Api.OK(userOrderDetailResponse);
    }

}
