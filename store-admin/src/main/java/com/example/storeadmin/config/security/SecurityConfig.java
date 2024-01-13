package com.example.storeadmin.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final List<String> SWAGGER_UI = List.of(
            "/swagger-ui/index.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

    private final List<String> OPEN_API = List.of(
            "/open-api/**"
    );

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests(it -> it.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()  // 모든 Resource 에 대한 요청 접근 허용
                        .mvcMatchers(SWAGGER_UI.toArray(new String[0])).permitAll()  // Swagger-UI 관련 요청 접근 허용
                        .mvcMatchers(OPEN_API.toArray(new String[0])).permitAll()  // /open-api 경로의 모든 요청 접근 허용
                        .anyRequest().authenticated())  // 그 외 모든 접근 인증 절차 필요
                .formLogin(Customizer.withDefaults());  // Default Form 로그인 사용

        return httpSecurity.build();
    }

}
