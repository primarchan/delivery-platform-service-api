package com.example.storeadmin.domain.authorization;

import com.example.db.storeuser.StoreUserEntity;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<StoreUserEntity> storeUserEntity = storeUserService.getRegisteredUser(username);

        return storeUserEntity
                .map(entity -> User.builder()
                        .username(entity.getEmail())
                        .password(entity.getPassword())
                        .roles(entity.getRole().toString())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
