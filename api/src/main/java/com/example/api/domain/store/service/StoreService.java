package com.example.api.domain.store.service;

import com.example.api.common.error.ErrorCode;
import com.example.api.common.exception.ApiException;
import com.example.db.store.StoreEntity;
import com.example.db.store.StoreRepository;
import com.example.db.store.enums.StoreCategory;
import com.example.db.store.enums.StoreStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    /**
     * 유효한 가맹점 조회
     */
    public StoreEntity getStoreWithThrow(Long storeId) {

        return storeRepository.findFirstByIdAndStatusOrderByIdDesc(storeId, StoreStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    /**
     * 가맹점 등록
     */
    @Transactional
    public StoreEntity register(StoreEntity storeEntity) {

        return Optional.ofNullable(storeEntity)
                .map(entity -> {
                    entity.setStar(0.0);
                    entity.setStatus(StoreStatus.REGISTERED);

                    return storeRepository.save(entity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }


    /**
     * 카테고리 기준으로 가맹점 조회
     */
    public List<StoreEntity> searchByCategory(StoreCategory storeCategory) {

        return storeRepository.findAllByStatusAndCategoryOrderByStarDesc(StoreStatus.REGISTERED, storeCategory);
    }

    /**
     * 전체 가맹점 조회
     */
    public List<StoreEntity> searchAll() {

        return storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED);
    }



}
