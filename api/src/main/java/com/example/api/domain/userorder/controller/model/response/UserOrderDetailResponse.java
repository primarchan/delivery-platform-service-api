package com.example.api.domain.userorder.controller.model.response;

import com.example.api.domain.store.controller.model.response.StoreResponse;
import com.example.api.domain.storemenu.controller.model.response.StoreMenuResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDetailResponse {

    private UserOrderResponse userOrderResponse;
    private StoreResponse storeResponse;
    private List<StoreMenuResponse> storeMenuResponseList;

}
