package com.Ai_Rag_System.service;

import com.Ai_Rag_System.dto.IngestionResponse;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;

import java.util.List;

public class DocumentService {

    TokenTextSplitter textSplitter;
    static final String SOURCE_KEY = "sourceKey";
    VectorStore vectorStore;



    public IngestionResponse ingestText(String source, String content) {
        List<Document> documentList = List.of(new Document(content));
        return store(source, documentList);
    }



    public IngestionResponse store( String source, List<Document> documentList) {
        List<Document> chunks = textSplitter.apply(documentList);
        chunks.forEach(chunk -> chunk.getMetadata().put(SOURCE_KEY, source));
        vectorStore.accept(chunks);
        return new IngestionResponse(source, chunks.size(),"store into vector");
    }
}
