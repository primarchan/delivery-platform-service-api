package com.example.storeadmin.domain.sse.controller;

import com.example.storeadmin.domain.authorization.model.UserSession;
import com.example.storeadmin.domain.sse.connection.SseConnectionPool;
import com.example.storeadmin.domain.sse.connection.model.UserSseConnection;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/sse")
@RequiredArgsConstructor
public class SseApiController {

    // private static final Map<String, SseEmitter> userConnection = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;
    private final SseConnectionPool sseConnectionPool;

    @GetMapping(path = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter connect(@Parameter(hidden = true) @AuthenticationPrincipal UserSession userSession) {
        log.info("login user : {}", userSession);

        /*
        SseEmitter emitter = new SseEmitter(1000L * 60);  // ms
        userConnection.put(userSession.getUserId().toString(), emitter);

        var temp = UserSseConnection.connect(userSession.getStoreId().toString(), sseConnectionPool);

        emitter.onTimeout(() -> {
            log.info("On Timeout");
            emitter.complete();
        });  // Client 와 타임아웃 발생 시
        emitter.onCompletion(() -> {
            log.info("On Completion");
            userConnection.remove(userSession.getUserId().toString());
        });  // Client 와 연결 종료 시

        // 최초 연결 시 응답 전송
        SseEmitter.SseEventBuilder event = SseEmitter.event()
                .name("onopen")
                .data("connect");

        try {
            emitter.send(event);
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
         */

        UserSseConnection userSseConnection = UserSseConnection
                .connect(userSession.getStoreId().toString(), sseConnectionPool, objectMapper);

        // Session 에 추가
        sseConnectionPool.addSession(userSseConnection.getUniqueKey(), userSseConnection);

        return userSseConnection.getSseEmitter();
    }

    @GetMapping("/push-event")
    public void pushEvent(@Parameter(hidden = true) @AuthenticationPrincipal UserSession userSession) {
        /*
        SseEmitter emitter = userConnection.get(userSession.getUserId().toString());

        SseEmitter.SseEventBuilder event = SseEmitter.event()
                .data("hello");  // onmessage

        try {
            emitter.send(event);
        } catch (IOException e) {
            emitter.completeWithError(e);
        }
         */

        UserSseConnection userSseConnection = sseConnectionPool.getSession(userSession.getStoreId().toString());

        Optional.ofNullable(userSseConnection).ifPresent(it -> {
            it.sendMessage("Hello World");
        });
    }

}
