package com.drinkingbuddies.drinkingbuddies.classes;

import com.drinkingbuddies.drinkingbuddies.classes.player;

import java.util.ArrayList;
import java.util.List;

public class room {
    String roomId;
    List<player> allPlayers;

    public room(String roomId) {
        this.roomId = roomId;
        this.allPlayers = new ArrayList<>();
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<player> getAllPlayers() {
        return allPlayers;
    }

    public void setAllPlayers(List<player> allPlayers) {
        this.allPlayers = allPlayers;
    }
}
