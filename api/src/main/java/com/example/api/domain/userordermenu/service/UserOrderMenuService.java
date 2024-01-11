package com.example.api.domain.userordermenu.service;

import com.example.db.userordermenu.UserOrderMenuEntity;
import com.example.db.userordermenu.UserOrderMenuRepository;
import com.example.db.userordermenu.enums.UserOrderMenuStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserOrderMenuService {

    private final UserOrderMenuRepository userOrderMenuRepository;

    public List<UserOrderMenuEntity> getUerOrderMenu(Long userOrderId) {

        return userOrderMenuRepository.findAllByUserOrderIdAndStatus(userOrderId, UserOrderMenuStatus.REGISTERED);
    }

}
