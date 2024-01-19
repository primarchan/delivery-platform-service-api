package com.example.storeadmin.domain.userorder.business;

import com.example.common.message.model.UserOrderMessage;
import com.example.db.userorder.UserOrderEntity;
import com.example.storeadmin.annotation.Business;
import com.example.storeadmin.domain.sse.connection.SseConnectionPool;
import com.example.storeadmin.domain.sse.connection.model.UserSseConnection;
import com.example.storeadmin.domain.userorder.service.UserOrderService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final SseConnectionPool sseConnectionPool;

    /**
     * 주문 -> 주문 내역 찾기 -> 가맹점 찾기 -> 연결된 세션 찾기 -> 메세지 Push
     */
    public void pushUserOrder(UserOrderMessage userOrderMessage) {
        UserOrderEntity userOrderEntity = userOrderService.getUserOrder(userOrderMessage.getUserOrderId())
                .orElseThrow(() -> new RuntimeException("사용자 주문내역이 존재하지 않습니다."));



        UserSseConnection userSseConnection = sseConnectionPool.getSession(userOrderEntity.getStoreId().toString());

        // 사용자에게 메세지 Push
        userSseConnection.sendMessage(null);
    }

}
