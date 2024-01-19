package com.example.storeadmin.domain.userorder.controller.model.response;

import com.example.storeadmin.domain.storemenu.controller.model.response.StoreMenuResponse;
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

   private List<StoreMenuResponse> storeMenuResponseList;

}
