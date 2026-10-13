package com.Ai_Rag_System.controller;

import com.Ai_Rag_System.dto.TextIngestionRequest;
import com.Ai_Rag_System.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class DocumentController {

    @Autowired
    DocumentService documentService;

    @PostMapping(value = "/addText")
    public void addText(@RequestBody TextIngestionRequest IngestionRequest) {

        documentService.ingestText(IngestionRequest.source() ,IngestionRequest.content());
    }

}
