package com.example.storeadmin.domain.userorder.business;

import com.example.common.message.model.UserOrderMessage;
import com.example.db.userorder.UserOrderEntity;
import com.example.db.userordermenu.UserOrderMenuEntity;
import com.example.storeadmin.annotation.Business;
import com.example.storeadmin.domain.sse.connection.SseConnectionPool;
import com.example.storeadmin.domain.sse.connection.model.UserSseConnection;
import com.example.storeadmin.domain.storemenu.controller.model.response.StoreMenuResponse;
import com.example.storeadmin.domain.storemenu.converter.StoreMenuConverter;
import com.example.storeadmin.domain.storemenu.service.StoreMenuService;
import com.example.storeadmin.domain.userorder.controller.model.response.UserOrderDetailResponse;
import com.example.storeadmin.domain.userorder.controller.model.response.UserOrderResponse;
import com.example.storeadmin.domain.userorder.converter.UserOrderConverter;
import com.example.storeadmin.domain.userorder.service.UserOrderService;
import com.example.storeadmin.domain.userordermenu.service.UserOrderMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final StoreMenuService storeMenuService;
    private final SseConnectionPool sseConnectionPool;
    private final UserOrderConverter userOrderConverter;
    private final StoreMenuConverter storeMenuConverter;
    private final UserOrderMenuService userOrderMenuService;

    /**
     * 주문 -> 주문 내역 찾기 -> 가맹점 찾기 -> 연결된 세션 찾기 -> 메세지 Push
     */
    public void pushUserOrder(UserOrderMessage userOrderMessage) {
        UserOrderEntity userOrderEntity = userOrderService.getUserOrder(userOrderMessage.getUserOrderId())
                .orElseThrow(() -> new RuntimeException("사용자 주문내역이 존재하지 않습니다."));


        List<UserOrderMenuEntity> userOrderMenuEntityList = userOrderMenuService.getUserOrderMenuList(userOrderEntity.getId());

        List<StoreMenuResponse> storeMenuResponseList = userOrderMenuEntityList.stream()
                .map(userOrderMenuEntity -> storeMenuService.getStoreMenuWithThrow(userOrderMenuEntity.getStoreMenuId()))
                .map(storeMenuConverter::toResponse)
                .toList();

        UserOrderResponse userOrderResponse = userOrderConverter.toResponse(userOrderEntity);

        UserOrderDetailResponse userOrderDetailResponse = UserOrderDetailResponse.builder()
                .storeMenuResponseList(storeMenuResponseList)
                .userOrderResponse(userOrderResponse)
                .build();

        UserSseConnection userSseConnection = sseConnectionPool.getSession(userOrderEntity.getStoreId().toString());

        // 사용자에게 메세지 Push
        userSseConnection.sendMessage(userOrderDetailResponse);
    }

}
