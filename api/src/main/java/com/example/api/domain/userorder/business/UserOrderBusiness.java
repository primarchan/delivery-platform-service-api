package com.example.api.domain.userorder.business;

import com.example.api.annotation.Business;
import com.example.api.domain.storemenu.service.StoreMenuService;
import com.example.api.domain.user.model.User;
import com.example.api.domain.userorder.controller.model.request.UserOrderRequest;
import com.example.api.domain.userorder.controller.model.response.UserOrderResponse;
import com.example.api.domain.userorder.converter.UserOrderConverter;
import com.example.api.domain.userorder.service.UserOrderService;
import com.example.api.domain.userordermenu.converter.UserOrderMenuConverter;
import com.example.api.domain.userordermenu.service.UserOrderMenuService;
import com.example.db.storemenu.StoreMenuEntity;
import com.example.db.userorder.UserOrderEntity;
import com.example.db.userordermenu.UserOrderMenuEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final StoreMenuService storeMenuService;
    private final UserOrderConverter userOrderConverter;
    private final UserOrderMenuConverter userOrderMenuConverter;
    private final UserOrderMenuService userOrderMenuService;


    public UserOrderResponse userOrder(UserOrderRequest body, User user) {
        List<StoreMenuEntity> storeMenuEntityList = body.getStoreMenuIdList().stream()
                .map(storeMenuService::getStoreMenuWithThrow)
                .collect(Collectors.toList());

        UserOrderEntity userOrderEntity = userOrderConverter.toEntity(user, storeMenuEntityList);

        UserOrderEntity savedUserOrderEntity  = userOrderService.order(userOrderEntity);

        List<UserOrderMenuEntity> userOrderMenuEntityList = storeMenuEntityList.stream()
                .map(storeMenuEntity -> userOrderMenuConverter.toEntity(savedUserOrderEntity, storeMenuEntity))
                .toList();

        userOrderMenuEntityList.forEach(userOrderMenuService::order);

        return userOrderConverter.toResponse(savedUserOrderEntity);
    }
}
