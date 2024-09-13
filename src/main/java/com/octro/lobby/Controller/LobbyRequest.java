package com.octro.lobby.Controller;

import com.octro.lobby.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LobbyRequest {

    @Autowired
    KafkaProducer kafkaProducer;
    @PostMapping("/RequestLobby/{user}")
    public void addrequesttoqueue(@PathVariable String user){
        kafkaProducer.sendmessage(user);
        return;
    }
}
