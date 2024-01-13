package com.example.storeadmin.domain.authorization;

import com.example.db.store.StoreEntity;
import com.example.db.store.StoreRepository;
import com.example.db.store.enums.StoreStatus;
import com.example.db.storeuser.StoreUserEntity;
import com.example.storeadmin.domain.authorization.model.UserSession;
import com.example.storeadmin.domain.user.service.StoreUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<StoreUserEntity> storeUserEntity = storeUserService.getRegisteredUser(username);
        Optional<StoreEntity> storeEntity = storeRepository.findFirstByIdAndStatusOrderByIdDesc(storeUserEntity.get().getStoreId(), StoreStatus.REGISTERED);

        return storeUserEntity
                .map(entity -> UserSession.builder()
                        .userId(entity.getId())
                        .email(entity.getEmail())
                        .password(entity.getPassword())
                        .status(entity.getStatus())
                        .role(entity.getRole())
                        .registeredAt(entity.getRegisteredAt())
                        .unregisteredAt(entity.getUnregisteredAt())
                        .lastLoginAt(entity.getLastLoginAt())
                        .storeId(storeEntity.get().getId())
                        .storeName(storeEntity.get().getName())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
