package com.example.telegramnote.infra.openSearchService;

import java.util.List;

public interface OpenSearchOperationService {

    void createIndex();

    <T> void indexRequest(T indexData);

    String indexGetRequest(String id);

    <T> List<T> getDocs(String tags, Class<T> entity);

    void deleteRequest(String id);

    void deleteIndexRequest();
}
