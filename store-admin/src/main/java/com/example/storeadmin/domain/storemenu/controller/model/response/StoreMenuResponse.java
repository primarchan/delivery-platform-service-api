package com.example.storeadmin.domain.storemenu.controller.model.response;

import com.example.db.storemenu.enums.StoreMenuStatus;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreMenuResponse {

    private Long id;
    private String name;
    private BigDecimal amount;
    private StoreMenuStatus status;
    private String thumbnailUrl;
    private int likeCount;
    private int sequence;
}
