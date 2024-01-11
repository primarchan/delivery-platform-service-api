package com.example.db.userorder.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserOrderStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지"),
    ORDER("주문"),
    ACCEPT("접수"),
    COOKING("조리중"),
    DELIVERY("배달중"),
    RECEIVE("완료"),
    ;

    private String description;

}
