package com.drinkingbuddies.drinkingbuddies.player;

import com.drinkingbuddies.drinkingbuddies.Drink.Drink;

import java.util.List;

public abstract class player {
     String playerId;
     String datePlayed;
    List<Drink> Drinks;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
    public String getDatePlayed() {
        return datePlayed;
    }

    public void setDatePlayed(String datePlayed) {
        this.datePlayed = datePlayed;
    }
    public List<Drink> getDrinks() {
        return Drinks;
    }
    public void setDrinks(List<Drink> drinks) {
        Drinks = drinks;
    }
}
