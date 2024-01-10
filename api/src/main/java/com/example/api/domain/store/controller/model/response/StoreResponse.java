package com.example.api.domain.store.controller.model.response;

import com.example.db.store.enums.StoreCategory;
import com.example.db.store.enums.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {

    private Long id;
    private String name;
    private String address;
    private StoreStatus status;
    private StoreCategory category;
    private double star;
    private String thumbnailUrl;
    private BigDecimal minimumAmount;
    private BigDecimal minimumDeliveryAmount;
    private String phoneNumber;

}
