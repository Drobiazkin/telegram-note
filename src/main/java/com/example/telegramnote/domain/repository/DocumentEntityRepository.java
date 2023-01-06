package com.example.telegramnote.domain.repository;

import com.example.telegramnote.domain.entity.DocumentEntity;

import java.util.List;

public interface DocumentEntityRepository {

    void save(DocumentEntity documentEntity, String id);

    List<DocumentEntity> searchDocuments(String textContainsDocument);

    DocumentEntity getDocument(String documentId);

}
