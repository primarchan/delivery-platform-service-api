package com.example.api.common.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {

    private final RabbitTemplate rabbitTemplate;

    public void producer(String exchange, String routeKey, Object object) {

        rabbitTemplate.convertAndSend(exchange, routeKey, object);
    }

}
