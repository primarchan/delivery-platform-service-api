package com.example.db.storeuser.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreUserStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지"),
    LOCKED("정지")
    ;

    private final String description;


}
