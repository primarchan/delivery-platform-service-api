package com.example.db.store.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreCategory {

    CHINESE_FOOD("중식", "중식"),
    WESTERN_FOOD("양식", "양식"),
    KOREAN_FOOD("한식", "한식"),
    JAPANESE_FOOD("일식", "일식"),
    CHICKEN("치킨", "치킨"),
    PIZZA("피자", "피자"),
    BUGGER("버거", "버거"),
    COFFEE_TEA("커피&티", "커피&티")
    ;

    private String display;
    private String description;

}
