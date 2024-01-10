package com.example.api.domain.store.controller;


import com.example.api.common.api.Api;
import com.example.api.domain.store.business.StoreBusiness;
import com.example.api.domain.store.controller.model.response.StoreResponse;
import com.example.db.store.enums.StoreCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreApiController {

    private final StoreBusiness storeBusiness;

    @GetMapping("/search")
    public Api<List<StoreResponse>> search(@RequestParam(required = false) StoreCategory storeCategory) {
        List<StoreResponse> storeResponse = storeBusiness.searchByCategory(storeCategory);

        return Api.OK(storeResponse);
    }

}
