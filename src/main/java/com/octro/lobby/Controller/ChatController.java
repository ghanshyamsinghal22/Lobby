package com.octro.lobby.Controller;

import com.octro.lobby.Entity.ChatMessage;
import com.octro.lobby.Service.ChessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private ChessService chessService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/sendMessage")
    public void sendMessage(ChatMessage message) {
        System.out.println(message.toString());
        if(chessService.chessObjects.get(message.getChannelId())!=null){
            chessService.updateChessState(message);
        messagingTemplate.convertAndSend("/topic/" + message.getChannelId(), chessService.chessObjects.get(message.getChannelId()));
        }
    }
}
