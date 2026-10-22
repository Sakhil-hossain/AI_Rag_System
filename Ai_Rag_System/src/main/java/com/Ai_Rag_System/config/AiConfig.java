package com.Ai_Rag_System.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AiConfig {

    @Bean
    public ChatClient getChatClient(ChatClient.Builder builder) {
        return builder.defaultSystem("You are a assistance to company document and answer only from " +
                "the context, if data is not avalilable in the context, just reply: There is no Such  " +
                "informations in this document related to this question, so dont use the outside knowledge. ").build();
    }

    @Bean
    public TokenTextSplitter getTokenSplitter() {
        return TokenTextSplitter.builder()
                .withChunkSize(500)
                .withMinChunkSizeChars(100)
                .withMinChunkLengthToEmbed(10)
                .withMaxNumChunks(10_000)
                .withKeepSeparator(true)
                .build();
    }
}
