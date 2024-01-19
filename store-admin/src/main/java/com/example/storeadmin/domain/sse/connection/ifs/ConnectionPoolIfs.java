package com.example.storeadmin.domain.sse.connection.ifs;

import com.example.storeadmin.domain.sse.connection.model.UserSseConnection;

public interface ConnectionPoolIfs<T, R> {

    void addSession(T uniqueKey, R session);

    R getSession(T uniqueKey);

    void onCompletionCallback(R session);

}
