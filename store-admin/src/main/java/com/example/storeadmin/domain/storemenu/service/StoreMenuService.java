package com.example.storeadmin.domain.storemenu.service;

import com.example.db.storemenu.StoreMenuEntity;
import com.example.db.storemenu.StoreMenuRepository;
import com.example.db.storemenu.enums.StoreMenuStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    public StoreMenuEntity getStoreMenuWithThrow(Long id) {
        return storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED)
                .orElseThrow(() -> new RuntimeException("Store menu not found."));
    }

}
