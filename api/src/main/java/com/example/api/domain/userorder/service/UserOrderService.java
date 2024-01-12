package com.example.api.domain.userorder.service;

import com.example.api.common.error.ErrorCode;
import com.example.api.common.exception.ApiException;
import com.example.db.userorder.UserOrderEntity;
import com.example.db.userorder.UserOrderRepository;
import com.example.db.userorder.enums.UserOrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;

    /**
     * 사용자 주문 단 건 조회 (사용자 주문 상태와 관련없이)
     */
    public UserOrderEntity getUserOrderWithoutStatusWithThrow(Long id, Long userId) {

        return userOrderRepository.findAllByIdAndUserId(id, userId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    /**
     * 사용자 주문 단 건 조회
     */
    public UserOrderEntity getUserOrderWithThrow(Long id, Long userId) {

        return userOrderRepository.findAllByIdAndStatusAndUserId(id, UserOrderStatus.REGISTERED, userId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    /**
     * 사용자 주문 전체 조회
     */
    public List<UserOrderEntity> getUserOrderList(Long userId) {

        return userOrderRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, UserOrderStatus.REGISTERED);
    }

    /**
     * 사용자 주문 상태 기준으로 전체 조회
     */
    public List<UserOrderEntity> getUserOrderList(Long userId, List<UserOrderStatus> statusList) {

        return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId, statusList);
    }

    /**
     * 현재 진행중인 사용자 주문 내역
     */
    public List<UserOrderEntity> currentUserOrderList(Long userId) {
        List<UserOrderStatus> currentUserOrderStatusList = List.of(UserOrderStatus.ORDER, UserOrderStatus.ACCEPT, UserOrderStatus.COOKING, UserOrderStatus.DELIVERY);

        return getUserOrderList(userId, currentUserOrderStatusList);
    }

    /**
     * 과거 완료된 사용자 주문 내역
     */
    public List<UserOrderEntity> historyUserOrderList(Long userId) {
        List<UserOrderStatus> historyUserOrderStatusList = List.of(UserOrderStatus.RECEIVE);

        return getUserOrderList(userId, historyUserOrderStatusList);
    }

    /**
     * 사용자 주문
     */
    public UserOrderEntity order(UserOrderEntity userOrderEntity) {

        return Optional.ofNullable(userOrderEntity)
                .map(entity -> {
                    entity.setStatus(UserOrderStatus.ORDER);
                    entity.setOrderedAt(LocalDateTime.now());

                    return userOrderRepository.save(entity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    /**
     * 사용자 주문 상태 변경
     */
    public UserOrderEntity changeStatus(UserOrderEntity userOrderEntity, UserOrderStatus status) {
        userOrderEntity.setStatus(status);

        return userOrderRepository.save(userOrderEntity);
    }

    /**
     * 사용자 주문 확인 (접수)
     */
    public UserOrderEntity accept(UserOrderEntity userOrderEntity) {
        userOrderEntity.setAcceptedAt(LocalDateTime.now());

        return changeStatus(userOrderEntity, UserOrderStatus.ACCEPT);
    }

    /**
     * 사용자 주문 조리 시작
     */
    public UserOrderEntity cooking(UserOrderEntity userOrderEntity) {
        userOrderEntity.setCookingStartedAt(LocalDateTime.now());

        return changeStatus(userOrderEntity, UserOrderStatus.COOKING);
    }

    /**
     * 사용자 주문 배달 시작
     */
    public UserOrderEntity delivery(UserOrderEntity userOrderEntity) {
        userOrderEntity.setDeliveryStartedAt(LocalDateTime.now());

        return changeStatus(userOrderEntity, UserOrderStatus.DELIVERY);
    }

    /**
     * 사용자 주문 배달 완료
     */
    public UserOrderEntity receive(UserOrderEntity userOrderEntity) {
        userOrderEntity.setReceivedAt(LocalDateTime.now());

        return changeStatus(userOrderEntity, UserOrderStatus.RECEIVE);
    }

}
