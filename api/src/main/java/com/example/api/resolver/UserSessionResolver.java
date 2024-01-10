package com.example.api.resolver;

import com.example.api.annotation.UserSession;
import com.example.api.domain.user.model.User;
import com.example.api.domain.user.service.UserService;
import com.example.db.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserSessionResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 지원하는 파라미터 체크, 어노테이션 체크

        // 1. 어노테이션 체크
        boolean annotation = parameter.hasParameterAnnotation(UserSession.class);

        // 2. 파라미터 타입 체크
        boolean parameterType = parameter.getParameterType().equals(User.class);

        return (annotation && parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // supportsParameter 메서드에서 true 반환 시 실행되는 메서드

        // request context holder 에서 찾아오기
        RequestAttributes requestContext = RequestContextHolder.getRequestAttributes();
        Long userId = Long.parseLong(requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST).toString());

        UserEntity userEntity = userService.getUserWithThrow(userId);

        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .status(userEntity.getStatus())
                .password(userEntity.getPassword())
                .address(userEntity.getAddress())
                .registeredAt(userEntity.getRegisteredAt())
                .unregisteredAt(userEntity.getUnregisteredAt())
                .lastLoginAt(userEntity.getLastLoginAt())
                .build();
    }

}
