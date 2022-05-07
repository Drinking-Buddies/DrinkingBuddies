package com.drinkingbuddies.drinkingbuddies.classes;

import java.util.ArrayList;
import java.util.List;

public class host extends player{
    public host(String playerId, String datePlayed, List<Drink> drinks) {
        this.playerId = playerId;
        this.datePlayed = datePlayed;
        Drinks = new ArrayList<Drink>();
    }

    //TODO: move the player out of the table
    public void kickPlayer(String Id){

    }

}
