package com.example.telegramnote.domain.repository;

import com.example.telegramnote.domain.entity.DocumentEntity;
import com.example.telegramnote.infra.adapter.OpenSearchAdapter;

import java.util.List;

public class DocumentEntityRepositoryImpl implements DocumentEntityRepository {
    OpenSearchAdapter openSearchAdapter;

    public DocumentEntityRepositoryImpl(OpenSearchAdapter openSearchAdapter) {
        this.openSearchAdapter = openSearchAdapter;
    }

    @Override
    public void save(DocumentEntity documentEntity, String id) {
        openSearchAdapter.indexRequest(documentEntity, id);
    }

    @Override
    public List<DocumentEntity> searchDocuments(String textContainsDocument) {
        return openSearchAdapter.getDocs(textContainsDocument, DocumentEntity.class);
    }

    @Override
    public DocumentEntity getDocument(String documentId) {
        return openSearchAdapter.getDocId(documentId, DocumentEntity.class);
    }

    @Override
    public void createIndex() {
        openSearchAdapter.createIndex();
    }


}
