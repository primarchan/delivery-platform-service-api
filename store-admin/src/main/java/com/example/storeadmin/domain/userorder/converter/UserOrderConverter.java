package com.example.storeadmin.domain.userorder.converter;

import com.example.db.userorder.UserOrderEntity;
import com.example.storeadmin.annotation.Converter;
import com.example.storeadmin.domain.userorder.controller.model.response.UserOrderResponse;

@Converter
public class UserOrderConverter {

    public UserOrderResponse toResponse(UserOrderEntity userOrderEntity) {

        return UserOrderResponse.builder()
                .id(userOrderEntity.getId())
                .storeId(userOrderEntity.getStoreId())
                .userId(userOrderEntity.getUserId())
                .status(userOrderEntity.getStatus())
                .amount(userOrderEntity.getAmount())
                .orderedAt(userOrderEntity.getOrderedAt())
                .acceptedAt(userOrderEntity.getAcceptedAt())
                .cookingStartedAt(userOrderEntity.getCookingStartedAt())
                .deliveryStartedAt(userOrderEntity.getDeliveryStartedAt())
                .receivedAt(userOrderEntity.getReceivedAt())
                .build();
    }

}
