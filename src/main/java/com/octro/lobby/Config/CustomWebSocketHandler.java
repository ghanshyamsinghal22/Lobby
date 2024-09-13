package com.octro.lobby.Config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomWebSocketHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> userSessions = new HashMap<>();

    public static void addUserSession(String username, WebSocketSession session) {
        userSessions.put(username, session);
    }

    public static WebSocketSession getUserSession(String username) {
        return userSessions.get(username);
    }

    public static void removeUserSession(String username) {
        userSessions.remove(username);
    }
}
