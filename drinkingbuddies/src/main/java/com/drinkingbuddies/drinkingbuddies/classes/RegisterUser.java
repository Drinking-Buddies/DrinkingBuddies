package com.drinkingbuddies.drinkingbuddies.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterUser extends User{
    String userName;
    String email;
    String password;
    String birthday;
    String phoneNum;
    String emergencyPhoneNum;
    Float weight;
    Float height;
    Map<String, List<Drink>> drinkHis;
    List<String> friends;

    public RegisterUser(String userName,
                        String email,
                        String password,
                        String birthday,
                        String phoneNum,
                        String emergencyPhoneNum,
                        Float weight,
                        Float height){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.phoneNum = phoneNum;
        this.emergencyPhoneNum = emergencyPhoneNum;
        this.weight = weight;
        this.height = height;
        this.drinkHis = new HashMap<String, List<Drink>>();
        this.friends = new ArrayList<String>();
    }

    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getBirthday() {
        return birthday;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public String getEmergencyPhoneNum() {
        return emergencyPhoneNum;
    }
    public Float getWeight() {
        return weight;
    }
    public Float getHeight() {
        return height;
    }
    public Map<String, List<Drink>> getDrinkHis() {
        return drinkHis;
    }
    public List<String> getFriends() {
        return friends;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public void setEmergencyPhoneNum(String emergencyPhoneNum) {
        this.emergencyPhoneNum = emergencyPhoneNum;
    }
    public void setWeight(Float weight) {
        this.weight = weight;
    }
    public void setHeight(Float height) {
        this.height = height;
    }

    //TODO: input friend's id and put it into the list
    public void addFriend(String friendId){
        friends.add(friendId);
    }

    //TODO: input is the #existing# friend's id, simply remove it
    public void removeFriend(String friendId){
        friends.remove(friendId);
    }

    //TODO: no fucking idea right now
    public void createRoome(){

    }
    // TODO: no fucking idea right now
    public void logout(){

    }
}
