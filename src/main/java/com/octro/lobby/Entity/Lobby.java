package com.octro.lobby.Entity;

import lombok.Data;
import java.util.List;

@Data
public class Lobby {
    private String lobbyId;
    public Lobby(String lobbyId) {
        this.lobbyId = lobbyId;
    }
}
