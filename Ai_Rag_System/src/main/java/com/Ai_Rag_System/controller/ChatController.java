package com.Ai_Rag_System.controller;

import com.Ai_Rag_System.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    ChatService chatService;

    @PostMapping(value = "/ask")
    public  String ask( @RequestBody String userText){
        return chatService.askQuestion(userText);


    }
}
