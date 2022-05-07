package com.drinkingbuddies.drinkingbuddies.classes;

import java.util.ArrayList;
import java.util.List;

public class participant extends player{

    public participant(String playerId, String datePlayed, List<Drink> drinks) {
        this.playerId = playerId;
        this.datePlayed = datePlayed;
        Drinks = new ArrayList<Drink>();
    }

    //TODO: no fucking idea now
    public void joinGame(){

    }

    //TODO: send needed info back to DB.
    public void sendInfoToDB(){

    }
}
