package com.Ai_Rag_System.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    VectorStore vectorStore;

    @Autowired
    ChatClient chatClient;

    private static final String USER_PROMPT= "context : {context} questions : {questions}";

    public String askQuestion(String question){
        List<Document> chunks = retrive(question);
        String context = chunks.stream()
                .map(Document :: getText)
                .collect(Collectors.joining("\n---\n"));


        String content = chatClient.prompt()
                .user(u->u.text(USER_PROMPT)
                        .param("context", context)
                        .param("questions", question))
                .call()
                .content();
        return content;
    }

    public List<Document> retrive(String query){
        SearchRequest searchRequest = SearchRequest.builder()
                .query(query)
                .topK(4)
                .similarityThreshold(0.4)
                .build();

        return vectorStore.similaritySearch(searchRequest);
    }
}
