package com.example.api.domain.storemenu.service;

import com.example.api.common.error.ErrorCode;
import com.example.api.common.exception.ApiException;
import com.example.db.storemenu.StoreMenuEntity;
import com.example.db.storemenu.StoreMenuRepository;
import com.example.db.storemenu.enums.StoreMenuStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    public StoreMenuEntity getStoreMenuWithThrow(Long id){
        Optional<StoreMenuEntity> entity = storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED);

        return entity.orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<StoreMenuEntity> getStoreMenuByStoreId(Long storeId){

        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(storeId, StoreMenuStatus.REGISTERED);
    }


    public StoreMenuEntity register(StoreMenuEntity storeMenuEntity){

        return Optional.ofNullable(storeMenuEntity)
                .map(entity ->{
                    entity.setStatus(StoreMenuStatus.REGISTERED);

                    return storeMenuRepository.save(entity);
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }

}
