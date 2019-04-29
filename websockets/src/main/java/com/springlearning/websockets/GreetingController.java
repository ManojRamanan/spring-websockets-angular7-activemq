package com.springlearning.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    private final SimpMessagingTemplate template;

    private static final String SENDING_URL = "/topic/greetings";


    @Autowired
    public GreetingController(SimpMessagingTemplate template) {
        this.template = template;
    }



    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @GetMapping("/sendMessage")
    public String sendMessage(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        template.convertAndSend(SENDING_URL, buildNextMessage(message));
        return "success";
    }

    private Greeting buildNextMessage(HelloMessage message) {
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
