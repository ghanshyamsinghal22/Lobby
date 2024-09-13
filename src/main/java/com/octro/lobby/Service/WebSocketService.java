package com.octro.lobby.Service;

import com.octro.lobby.Entity.Lobby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendLobbyId(String username, String lobby) {
        System.out.println("send message successfully" );
        String destination = "/queue/" + username;
        messagingTemplate.convertAndSend(destination, lobby);
    }
}