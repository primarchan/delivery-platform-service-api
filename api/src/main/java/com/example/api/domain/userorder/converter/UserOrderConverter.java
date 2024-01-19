package com.example.api.domain.userorder.converter;

import com.example.api.annotation.Converter;
import com.example.api.domain.user.model.User;
import com.example.api.domain.userorder.controller.model.response.UserOrderResponse;
import com.example.db.storemenu.StoreMenuEntity;
import com.example.db.userorder.UserOrderEntity;

import java.math.BigDecimal;
import java.util.List;

@Converter
public class UserOrderConverter {

    public UserOrderEntity toEntity(
            User user,
            Long storeId,
            List<StoreMenuEntity> storeMenuEntityList
    ) {
        BigDecimal totalAmount = storeMenuEntityList.stream()
                .map(StoreMenuEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return UserOrderEntity.builder()
                .userId(user.getId())
                .storeId(storeId)
                .amount(totalAmount)
                .build();
    }

    public UserOrderResponse toResponse(UserOrderEntity userOrderEntity) {

        return UserOrderResponse.builder()
                .id(userOrderEntity.getId())
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
