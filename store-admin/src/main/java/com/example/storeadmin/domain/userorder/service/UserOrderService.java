package com.example.storeadmin.domain.userorder.service;

import com.example.db.userorder.UserOrderEntity;
import com.example.db.userorder.UserOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;

    public Optional<UserOrderEntity> getUserOrder(Long id) {
        return userOrderRepository.findById(id);
    }

}
