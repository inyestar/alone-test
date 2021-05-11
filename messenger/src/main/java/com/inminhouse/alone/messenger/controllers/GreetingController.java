package com.inminhouse.alone.messenger.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Map<String, String> greeting(Map<String, String> name) throws InterruptedException {
		
		Thread.sleep(1000);
		return Collections.singletonMap("content", "hello, " + name.get("name"));
	}
}
