package com.example.storeadmin.domain.sse.connection;

import com.example.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs;
import com.example.storeadmin.domain.sse.connection.model.UserSseConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SseConnectionPool implements ConnectionPoolIfs<String, UserSseConnection> {

    private static final Map<String, UserSseConnection> connectionPool = new ConcurrentHashMap<>();

    @Override
    public void addSession(String uniqueKey, UserSseConnection userSseConnection) {
        connectionPool.put(uniqueKey, userSseConnection);
    }

    @Override
    public UserSseConnection getSession(String uniqueKey) {
        return connectionPool.get(uniqueKey);
    }

    @Override
    public void onCompletionCallback(UserSseConnection userSseConnection) {
        log.info("Callback connection pool completion : {}", userSseConnection);
        connectionPool.remove(userSseConnection.getUniqueKey());
    }

}
