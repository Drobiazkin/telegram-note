package com.example.telegramnote.infra.OpenSearchService;

import java.io.IOException;
import java.util.List;

public interface OpenSearchOperationService {

    void createIndex() throws IOException;

    <T> void indexRequest(T indexData) throws IOException;

    String indexGetRequest(String id) throws IOException;

    <T> List<T> getDocs(String tags, Class<T> entity) throws IOException;
}
