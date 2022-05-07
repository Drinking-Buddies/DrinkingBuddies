package com.drinkingbuddies.drinkingbuddies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class lobbyController {
    @RequestMapping(value = "/lobby", method = RequestMethod.GET)
    public String lobbyController(){
        return "lobby";
    }
}
