package com.example.api.domain.userordermenu.converter;

import com.example.api.annotation.Converter;
import com.example.db.storemenu.StoreMenuEntity;
import com.example.db.userorder.UserOrderEntity;
import com.example.db.userordermenu.UserOrderMenuEntity;

@Converter
public class UserOrderMenuConverter {

    public UserOrderMenuEntity toEntity(UserOrderEntity userOrderEntity, StoreMenuEntity storeMenuEntity) {

        return UserOrderMenuEntity.builder()
                .userOrderId(userOrderEntity.getId())
                .storeMenuId(storeMenuEntity.getId())
                .build();
    }

}
