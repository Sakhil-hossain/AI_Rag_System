package com.Ai_Rag_System.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.Ai_Rag_System.service.DocumentService.SOURCE_KEY;

@Service
public class ChatService {

    @Autowired
    VectorStore vectorStore;    

    @Autowired
    ChatClient chatClient;

    private static final String USER_PROMPT= "context : {context} questions : {questions}";

    static final String SOURCE_KEY ="sourceKey";

    public String askQuestion(String question, String sourceKey) {

        List<Document> chunks = retrive(question, sourceKey);

        if (chunks.isEmpty()) {
            return "No relevant information found.";
        }

        String context = chunks.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n---\n"));

        return chatClient.prompt()
                .user(u -> u.text(USER_PROMPT)
                        .param("context", context)
                        .param("questions", question))
                .call()
                .content();
    }


    public List<Document> retrive(String query, String sourceKey) {

        SearchRequest.Builder searchBuilder = SearchRequest.builder()
                .query(query)
                .topK(4)
                .similarityThreshold(0.4);

        // Apply filtering only when sourceKey is provided
        if (sourceKey != null && !sourceKey.isBlank()) {

            FilterExpressionBuilder builder =
                    new FilterExpressionBuilder();

            searchBuilder.filterExpression(
                    builder.eq(SOURCE_KEY, sourceKey).build()
            );
        }

        return vectorStore.similaritySearch(searchBuilder.build());
    }
}
