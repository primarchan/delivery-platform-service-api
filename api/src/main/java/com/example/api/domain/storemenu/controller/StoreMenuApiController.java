package com.example.api.domain.storemenu.controller;

import com.example.api.common.api.Api;
import com.example.api.domain.storemenu.business.StoreMenuBusiness;
import com.example.api.domain.storemenu.controller.model.response.StoreMenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store-menu")
public class StoreMenuApiController {

    private final StoreMenuBusiness storeMenuBusiness;

    @GetMapping("/search")
    public Api<List<StoreMenuResponse>> search(@RequestParam Long storeId){
        List<StoreMenuResponse> storeMenuResponse = storeMenuBusiness.searchByStoreId(storeId);

        return Api.OK(storeMenuResponse);
    }
}
