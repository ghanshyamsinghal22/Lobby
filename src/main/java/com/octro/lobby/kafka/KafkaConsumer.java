package com.octro.lobby.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octro.lobby.Entity.ChessGameState;
import com.octro.lobby.Entity.Lobby;
import com.octro.lobby.Service.ChessService;
import com.octro.lobby.Service.WebSocketService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class KafkaConsumer {

    private final Queue<String> waitingQueue = new LinkedList<>();

    @Autowired
    private ChessService chessService;

    private Long lobbynum= 0L;

    @Autowired
    private WebSocketService webSocketService;
    public KafkaConsumer() {
    }

    @KafkaListener(
            topics = {"waitingqueue"},
            groupId = "lobby"
    )
    public void consume(ConsumerRecord<String, String> record) throws JsonProcessingException {
        String user = record.value();
        System.out.println("Received message: " + user);

        synchronized (waitingQueue) {
            waitingQueue.add(user);
            if (waitingQueue.size() >= 2) {
                String user1 = waitingQueue.poll();
                String user2 = waitingQueue.poll();
                processUsers(user1,user2);
                lobbynum++;
                String lobbyname=getLobbyName(lobbynum);
                System.out.println(lobbyname);
                chessService.chessObjects.put(lobbyname,new ChessGameState(user1,user2));
                chessService.channelMessages.put(lobbyname,new ArrayList<>());

                // create a chatroom for users to interact and send them message that they are assigned a lobby with LoddyId
                webSocketService.sendLobbyId(user1, lobbyname);
                webSocketService.sendLobbyId(user2, lobbyname);
            }
        }
    }
    private void processUsers(String user1, String user2) {
        System.out.println("Processing users: " + user1 + " and " + user2);
    }

    public String getLobbyName(Long columnNumber) {
        StringBuilder lobbyName;
        Long remainder;
        for(lobbyName = new StringBuilder(); columnNumber > 0; columnNumber = (columnNumber - remainder) / 26) {
            remainder = (columnNumber - 1) % 26;
            lobbyName.insert(0, (char)(remainder + 65));
        }
        return lobbyName.toString();
    }
}
