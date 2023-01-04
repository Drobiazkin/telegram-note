package com.example.telegramnote.infra.adapter.openSearch;

import com.example.telegramnote.infra.openSearchService.OpenSearchRestClientService;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.opensearch.action.admin.indices.delete.DeleteIndexRequest;
import org.opensearch.action.delete.DeleteRequest;
import org.opensearch.action.get.GetRequest;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.opensearch.core.search.Hit;
import org.opensearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class OpenSearchAdapterImpl implements OpenSearchAdapter {

    @Value("${elasticsearch.index}")
    String index;

    private static final String REST_CLIENT_HIGH_LEVEL = "HIGH_LEVEL";
    private static final String REST_CLIENT_LOW_LEVEL = "LOW_LEVEL";

    private HashMap<String, Object> objectToJson(Object data) {
        JSONObject dataAsJson = new JSONObject(data);
        return new HashMap<>(dataAsJson.toMap());
    }

    private OpenSearchClient lowRestClient() {
        return new OpenSearchRestClientService(REST_CLIENT_LOW_LEVEL).getOpenSearchClient();
    }

    private RestHighLevelClient highRestClient() {
        return new OpenSearchRestClientService(REST_CLIENT_HIGH_LEVEL).getRestHighLevelClient();
    }

    @SneakyThrows
    @Override
    public void createIndex() {
        var createIndexRequest = new CreateIndexRequest(index);
        highRestClient().indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public <T> void indexRequest(T indexData) {
        IndexRequest request = new IndexRequest(index);
        request.source(objectToJson(indexData), XContentType.JSON);
        highRestClient().index(request, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public String indexGetRequest(String id) {
        GetRequest getRequest = new GetRequest(index, id);
        return highRestClient().get(getRequest, RequestOptions.DEFAULT).getSourceAsString();
    }


    @SneakyThrows
    @Override
    public <T> List<T> getDocs(String tags, Class<T> entity) {
        SearchRequest searchRequest = new SearchRequest
                .Builder()
                .query(q -> q.match(m -> m.field("message")
                        .query(FieldValue.of(tags)))).index(index)
                .build();
        var searchResponse = lowRestClient().search(searchRequest, entity);
        var response = searchResponse.hits().hits().stream().map(Hit::source).filter(Objects::nonNull).toList();
        return new ArrayList<>(response);
    }

    @SneakyThrows
    @Override
    public void deleteRequest(String id) {
        DeleteRequest deleteDocumentRequest = new DeleteRequest(index, id);
        highRestClient().delete(deleteDocumentRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public void deleteIndexRequest() {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
        highRestClient().indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
    }
}
