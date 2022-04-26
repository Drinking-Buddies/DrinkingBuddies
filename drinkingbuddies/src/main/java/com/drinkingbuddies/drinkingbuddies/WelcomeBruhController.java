package com.drinkingbuddies.drinkingbuddies;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeBruhController {
	@GetMapping("/welcome")
	public String welcome() {
		return "Hello World bruh";
	}
}
