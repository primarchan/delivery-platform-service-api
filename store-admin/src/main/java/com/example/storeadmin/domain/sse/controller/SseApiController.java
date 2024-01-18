package com.example.storeadmin.domain.sse.controller;

import com.example.storeadmin.domain.authorization.model.UserSession;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/api/sse")
@RequiredArgsConstructor
public class SseApiController {

    private static final Map<String, SseEmitter> userConnection = new ConcurrentHashMap<>();

    @GetMapping(path = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter connect(@Parameter(hidden = true) @AuthenticationPrincipal UserSession userSession) {

        log.info("login user {}", userSession);

        SseEmitter emitter = new SseEmitter(1000L * 60);  // ms
        userConnection.put(userSession.getUserId().toString(), emitter);

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
    }

    @GetMapping("/push-event")
    public void pushEvent(@Parameter(hidden = true) @AuthenticationPrincipal UserSession userSession) {
        SseEmitter emitter = userConnection.get(userSession.getUserId().toString());

        SseEmitter.SseEventBuilder event = SseEmitter.event()
                .data("hello");  // onmessage

        try {
            emitter.send(event);
        } catch (IOException e) {
            emitter.completeWithError(e);
        }
    }

}
