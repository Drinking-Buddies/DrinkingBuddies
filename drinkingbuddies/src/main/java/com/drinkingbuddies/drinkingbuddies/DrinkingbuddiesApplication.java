package com.drinkingbuddies.drinkingbuddies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.drinkingbuddies.drinkingbuddies")
public class DrinkingbuddiesApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DrinkingbuddiesApplication.class, args);
	}

}
