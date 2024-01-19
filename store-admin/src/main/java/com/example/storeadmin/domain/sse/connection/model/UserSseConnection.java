package com.example.storeadmin.domain.sse.connection.model;

import com.example.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Getter
@ToString
@EqualsAndHashCode
public class UserSseConnection {

    private final String uniqueKey;
    private final SseEmitter sseEmitter;
    private final ObjectMapper objectMapper;
    private final ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs;

    private UserSseConnection(
            String uniqueKey,
            ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs,
            ObjectMapper objectMapper
    ) {
        // key 초기화
        this.uniqueKey = uniqueKey;

        // SSE 초기화
        this.sseEmitter = new SseEmitter(60 * 1000L);

        // Object mapper 초기화
        this.objectMapper = objectMapper;

        // Callback 초기화
        this.connectionPoolIfs = connectionPoolIfs;

        // On Completion
        this.sseEmitter.onCompletion(() -> {
            // Connection Pool 초기화
            this.connectionPoolIfs.onCompletionCallback(this);
        });

        // On Timeout
        this.sseEmitter.onTimeout(this.sseEmitter::complete);

        // onopen 메시지
        sendMessage("onopen", "connect");
    }

    public static UserSseConnection connect(
            String uniqueKey,
            ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs,
            ObjectMapper objectMapper
    ) {

        return new UserSseConnection(uniqueKey, connectionPoolIfs, objectMapper);
    }

    public void sendMessage(String eventName, Object data) {
        try {
            String jsonData = this.objectMapper.writeValueAsString(data);

            SseEmitter.SseEventBuilder event = SseEmitter.event()
                    .name(eventName)
                    .data(jsonData);

            this.sseEmitter.send(event);

        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }

    public void sendMessage(Object data) {
        try {
            String jsonData = this.objectMapper.writeValueAsString(data);
            SseEmitter.SseEventBuilder event = SseEmitter.event()
                    .data(jsonData);
            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }

}
