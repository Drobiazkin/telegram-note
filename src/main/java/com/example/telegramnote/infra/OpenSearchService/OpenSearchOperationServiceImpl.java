package com.example.telegramnote.infra.OpenSearchService;

import lombok.SneakyThrows;
import org.json.JSONObject;
import org.opensearch.action.admin.indices.delete.DeleteIndexRequest;
import org.opensearch.action.delete.DeleteRequest;
import org.opensearch.action.delete.DeleteResponse;
import org.opensearch.action.get.GetRequest;
import org.opensearch.action.get.GetResponse;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.support.master.AcknowledgedResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.opensearch.core.search.Hit;
import org.opensearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class OpenSearchOperationServiceImpl implements OpenSearchOperationService {

    OpenSearchService openSearchService;

    @Value("${elasticsearch.index}")
    String index;

    public OpenSearchOperationServiceImpl(OpenSearchService openSearchService) {
        this.openSearchService = openSearchService;
    }

    @SneakyThrows
    @Override
    public void createIndex() {
        var createIndexRequest = new CreateIndexRequest(index);
        openSearchService.createRestHighLevelClient().indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public <T> void indexRequest(T indexData) {
        IndexRequest request = new IndexRequest(index);
        JSONObject dataAsJson = new JSONObject(indexData);
        HashMap<String, Object> dataAsMap = new HashMap<>(dataAsJson.toMap());
        request.source(dataAsMap, XContentType.JSON);
        openSearchService.createRestHighLevelClient().index(request, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public String indexGetRequest(String id) {
        GetRequest getRequest = new GetRequest(index, id);
        GetResponse response = openSearchService.createRestHighLevelClient().get(getRequest, RequestOptions.DEFAULT);
        openSearchService.createRestHighLevelClient().close();
        return response.getSourceAsString();
    }


    @SneakyThrows
    @Override
    public <T> List<T> getDocs(String tags, Class<T> entity) {
        SearchRequest searchRequest = new SearchRequest
                .Builder()
                .query(q -> q.match(m -> m.field("message")
                        .query(FieldValue.of(tags)))).index(index)
                .build();
        var searchResponse = openSearchService.createRestClient().search(searchRequest, entity);
        var response = searchResponse.hits().hits().stream().map(Hit::source).filter(Objects::nonNull).toList();
        return new ArrayList<>(response);
    }

    @SneakyThrows
    public void deleteRequest(String id) {
        DeleteRequest deleteDocumentRequest = new DeleteRequest(index, id);
        openSearchService.createRestHighLevelClient().delete(deleteDocumentRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    public void deleteIndexRequest() {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
        openSearchService.createRestHighLevelClient().indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
    }
}
