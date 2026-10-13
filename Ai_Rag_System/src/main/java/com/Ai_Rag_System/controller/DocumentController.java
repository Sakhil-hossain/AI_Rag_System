package com.Ai_Rag_System.controller;

import com.Ai_Rag_System.dto.IngestionResponse;
import com.Ai_Rag_System.dto.TextIngestionRequest;
import com.Ai_Rag_System.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @PostMapping(value = "/addText")
    public void addText(@RequestBody TextIngestionRequest IngestionRequest) {

        documentService.ingestText(IngestionRequest.source() ,IngestionRequest.content());
    }

    @PostMapping(value = "/addDocument", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public IngestionResponse addDocument(@RequestParam("file") MultipartFile file) {
        return documentService.ingestDocument(file);
    }

}
