package com.example.api.domain.userordermenu.service;

import com.example.api.common.error.ErrorCode;
import com.example.api.common.exception.ApiException;
import com.example.db.userordermenu.UserOrderMenuEntity;
import com.example.db.userordermenu.UserOrderMenuRepository;
import com.example.db.userordermenu.enums.UserOrderMenuStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserOrderMenuService {

    private final UserOrderMenuRepository userOrderMenuRepository;

    public List<UserOrderMenuEntity> getUerOrderMenu(Long userOrderId) {

        return userOrderMenuRepository.findAllByUserOrderIdAndStatus(userOrderId, UserOrderMenuStatus.REGISTERED);
    }

    public UserOrderMenuEntity order(UserOrderMenuEntity userOrderMenuEntity) {

        return Optional.ofNullable(userOrderMenuEntity)
                .map(entity -> {
                    entity.setStatus(UserOrderMenuStatus.REGISTERED);

                    return userOrderMenuRepository.save(entity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

}
