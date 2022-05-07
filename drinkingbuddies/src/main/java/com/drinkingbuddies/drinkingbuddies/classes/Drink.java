package com.drinkingbuddies.drinkingbuddies.classes;

public class Drink {
    String name;
    Float BAC;

    public Drink(String name, Float BAC) {
        this.name = name;
        this.BAC = BAC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getBAC() {
        return BAC;
    }

    public void setBAC(Float BAC) {
        this.BAC = BAC;
    }
}
