package com.example.api.domain.user.service;

import com.example.api.common.error.ErrorCode;
import com.example.api.common.error.UserErrorCode;
import com.example.api.common.exception.ApiException;
import com.example.db.user.UserEntity;
import com.example.db.user.UserRepository;
import com.example.db.user.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * User 도메인 로직을 처리하는 서비스
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserEntity register(UserEntity userEntity) {

        return Optional.ofNullable(userEntity)
                .map(it -> {
                    userEntity.setStatus(UserStatus.REGISTERED);
                    userEntity.setRegisteredAt(LocalDateTime.now());
                    return userRepository.save(userEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity is NULL"));
    }

    public UserEntity login(String email, String password) {
        UserEntity userEntity = getUserWithThrow(email, password);

        return userEntity;
    }

    public UserEntity getUserWithThrow(String email, String password) {

        return userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(email, password, UserStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(UserErrorCode.UER_NOT_FOUND));
    }

    public UserEntity getUserWithThrow(Long userId) {

        return userRepository.findFirstByIdAndStatusOrderByIdDesc(userId, UserStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(UserErrorCode.UER_NOT_FOUND));
    }

}
