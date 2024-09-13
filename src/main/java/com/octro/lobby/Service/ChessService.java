package com.octro.lobby.Service;

import com.octro.lobby.Entity.ChatMessage;
import com.octro.lobby.Entity.ChessGameState;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChessService {

    public  Map<String, List<ChatMessage>> channelMessages = new ConcurrentHashMap<>();
    public Map<String, ChessGameState> chessObjects = new HashMap<>();

    public void addMessage(ChatMessage message) {
        channelMessages.computeIfAbsent(message.getChannelId(), k -> new ArrayList<>()).add(message);
    }
    public void updateChessState(ChatMessage chatMessage){
        if(Objects.equals(chessObjects.get(chatMessage.getChannelId()).getPlayers().get(chessObjects.get(chatMessage.getChannelId()).getCurrentPlayer()), chatMessage.getSender())){
            chessObjects.get(chatMessage.getChannelId()).applyMove(chatMessage.getContent());
            addMessage(chatMessage);
        }
        return ;
    }
    public List<ChatMessage> getMessages(String channelId) {
        return channelMessages.getOrDefault(channelId, new ArrayList<>());
    }
}
