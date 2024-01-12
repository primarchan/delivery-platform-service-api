package com.example.api.domain.userorder.business;

import com.example.api.annotation.Business;
import com.example.api.domain.store.converter.StoreConverter;
import com.example.api.domain.store.service.StoreService;
import com.example.api.domain.storemenu.converter.StoreMenuConverter;
import com.example.api.domain.storemenu.service.StoreMenuService;
import com.example.api.domain.user.model.User;
import com.example.api.domain.userorder.controller.model.request.UserOrderRequest;
import com.example.api.domain.userorder.controller.model.response.UserOrderDetailResponse;
import com.example.api.domain.userorder.controller.model.response.UserOrderResponse;
import com.example.api.domain.userorder.converter.UserOrderConverter;
import com.example.api.domain.userorder.service.UserOrderService;
import com.example.api.domain.userordermenu.converter.UserOrderMenuConverter;
import com.example.api.domain.userordermenu.service.UserOrderMenuService;
import com.example.db.store.StoreEntity;
import com.example.db.storemenu.StoreMenuEntity;
import com.example.db.userorder.UserOrderEntity;
import com.example.db.userordermenu.UserOrderMenuEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final StoreService storeService;
    private final StoreConverter storeConverter;
    private final StoreMenuService storeMenuService;
    private final UserOrderService userOrderService;
    private final StoreMenuConverter storeMenuConverter;
    private final UserOrderConverter userOrderConverter;
    private final UserOrderMenuService userOrderMenuService;
    private final UserOrderMenuConverter userOrderMenuConverter;


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

    public List<UserOrderDetailResponse> getCurrentUserOrder(User user) {
        List<UserOrderEntity> userOrderEntityList =  userOrderService.currentUserOrderList(user.getId());

        // 주문 1건씩 처리
        List<UserOrderDetailResponse> userOrderDetailResponseList = userOrderEntityList.stream()
                .map(userOrderEntity -> {
                    // 사용자 주문 메뉴
                    List<UserOrderMenuEntity> userOrderMenuEntityList = userOrderMenuService.getUerOrderMenu(userOrderEntity.getId());
                    List<StoreMenuEntity> storeMenuEntityList = userOrderMenuEntityList.stream()
                    .map(userOrderMenuEntity -> storeMenuService.getStoreMenuWithThrow(userOrderMenuEntity.getStoreMenuId()))
                    .collect(Collectors.toList());

            // TODO: 리팩토링 필요
            // 사용자가 주문한 스토어
            StoreEntity storeEntity = storeService.getStoreWithThrow(storeMenuEntityList.stream()
                    .findFirst()
                    .get()
                    .getStoreId());

            return UserOrderDetailResponse.builder()
                    .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                    .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                    .storeResponse(storeConverter.toResponse(storeEntity))
                    .build();
        }).collect(Collectors.toList());

        return userOrderDetailResponseList;
    }

    public List<UserOrderDetailResponse> getHistoryUserOrder(User user) {
        List<UserOrderEntity> userOrderEntityList =  userOrderService.historyUserOrderList(user.getId());

        // 주문 1건씩 처리
        List<UserOrderDetailResponse> userOrderDetailResponseList = userOrderEntityList.stream()
                .map(userOrderEntity -> {
                    // 사용자가 주문한 메뉴
                    List<UserOrderMenuEntity> userOrderMenuEntityList = userOrderMenuService.getUerOrderMenu(userOrderEntity.getId());
                    List<StoreMenuEntity> storeMenuEntityList = userOrderMenuEntityList.stream()
                            .map(userOrderMenuEntity -> storeMenuService.getStoreMenuWithThrow(userOrderMenuEntity.getStoreMenuId()))
                            .collect(Collectors.toList());

                    // TODO: 리팩토링 필요
                    // 사용자가 주문한 스토어
                    StoreEntity storeEntity = storeService.getStoreWithThrow(storeMenuEntityList.stream()
                            .findFirst()
                            .get()
                            .getStoreId());

                    return UserOrderDetailResponse.builder()
                            .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                            .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                            .storeResponse(storeConverter.toResponse(storeEntity))
                            .build();
                }).collect(Collectors.toList());

        return userOrderDetailResponseList;
    }

    public UserOrderDetailResponse read(User user, Long orderId) {
        // UserOrderEntity userOrderEntity = userOrderService.getUserOrderWithThrow(orderId, user.getId());
        UserOrderEntity userOrderEntity = userOrderService.getUserOrderWithoutStatusWithThrow(orderId, user.getId());

        // 사용자가 주문한 메뉴
        List<UserOrderMenuEntity> userOrderMenuEntityList = userOrderMenuService.getUerOrderMenu(userOrderEntity.getId());
        List<StoreMenuEntity> storeMenuEntityList = userOrderMenuEntityList.stream()
                .map(userOrderMenuEntity -> storeMenuService.getStoreMenuWithThrow(userOrderMenuEntity.getStoreMenuId()))
                .collect(Collectors.toList());

        // TODO: 리팩토링 필요
        // 사용자가 주문한 스토어
        StoreEntity storeEntity = storeService.getStoreWithThrow(storeMenuEntityList.stream()
                .findFirst()
                .get()
                .getStoreId());

        return UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                .storeResponse(storeConverter.toResponse(storeEntity))
                .build();
    }

}
