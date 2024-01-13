package com.example.db.storeuser.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreUserRole {

    MASTER("최고 관리자"),
    ADMIN("관리자"),
    USER("일반 사용자"),
    ;

    private final String description;

}
