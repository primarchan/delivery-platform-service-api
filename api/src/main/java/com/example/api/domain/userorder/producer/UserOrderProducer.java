package com.example.api.domain.userorder.producer;

import com.example.api.common.rabbitmq.Producer;
import com.example.common.message.model.UserOrderMessage;
import com.example.db.userorder.UserOrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOrderProducer {

    private static final String EXCHANGE = "delivery.exchange";
    private static final String ROUTE_KEY = "delivery.key";

    private final Producer producer;

    public void sendOrder(UserOrderEntity userOrderEntity) {
        sendOrder(userOrderEntity.getId());
    }

    public void sendOrder(Long userOrderId) {
        UserOrderMessage userOrderMessage = UserOrderMessage.builder()
                .userOrderId(userOrderId)
                .build();

        producer.producer(EXCHANGE, ROUTE_KEY, userOrderMessage);
    }

}
