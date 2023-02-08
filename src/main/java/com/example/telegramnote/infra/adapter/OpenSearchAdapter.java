package com.example.telegramnote.infra.adapter;

import java.util.List;

public interface OpenSearchAdapter {

    void createIndex();

    <T> void indexRequest(T indexData, String id);

    String indexGetRequest(String id);

    <T> T getDocId(String id, Class<T> entity);

    <T> List<T> getDocs(String tags, Class<T> entity);

    void deleteRequest(String id);

    void deleteIndexRequest();
}
