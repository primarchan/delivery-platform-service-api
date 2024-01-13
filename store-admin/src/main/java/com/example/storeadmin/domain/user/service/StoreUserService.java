package com.example.storeadmin.domain.user.service;

import com.example.db.storeuser.StoreUserEntity;
import com.example.db.storeuser.StoreUserRepository;
import com.example.db.storeuser.enums.StoreUserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreUserService {

    private final StoreUserRepository storeUserRepository;
    private final PasswordEncoder passwordEncoder;

    public StoreUserEntity register(StoreUserEntity storeUserEntity) {
        storeUserEntity.setStatus(StoreUserStatus.REGISTERED);
        storeUserEntity.setPassword(passwordEncoder.encode(storeUserEntity.getPassword()));
        storeUserEntity.setRegisteredAt(LocalDateTime.now());

        return storeUserRepository.save(storeUserEntity);
    }

    public Optional<StoreUserEntity> getRegisteredUser(String email) {

        return storeUserRepository.findFirstByEmailAndStatusOrderByIdDesc(email, StoreUserStatus.REGISTERED);
    }

}
