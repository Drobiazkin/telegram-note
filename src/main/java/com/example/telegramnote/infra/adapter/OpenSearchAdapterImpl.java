package com.example.telegramnote.infra.adapter;

import com.example.telegramnote.infra.openSearch.OpenSearchRestClientAbstract;
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
    OpenSearchRestClientAbstract<RestHighLevelClient> restHighLevelClientOpenSearchRestClientAbstract;
    OpenSearchRestClientAbstract<OpenSearchClient> openSearchClientOpenSearchRestClientAbstract;

    public OpenSearchAdapterImpl(OpenSearchRestClientAbstract<RestHighLevelClient> restHighLevelClientOpenSearchRestClientAbstract, OpenSearchRestClientAbstract<OpenSearchClient> openSearchClientOpenSearchRestClientAbstract) {
        this.restHighLevelClientOpenSearchRestClientAbstract = restHighLevelClientOpenSearchRestClientAbstract;
        this.openSearchClientOpenSearchRestClientAbstract = openSearchClientOpenSearchRestClientAbstract;
    }

    private HashMap<String, Object> objectToJson(Object data) {
        JSONObject dataAsJson = new JSONObject(data);
        return new HashMap<>(dataAsJson.toMap());
    }

    private OpenSearchClient lowRestClient() {
        return openSearchClientOpenSearchRestClientAbstract.getRestClient();
    }

    private RestHighLevelClient highRestClient() {
        return restHighLevelClientOpenSearchRestClientAbstract.getRestClient();
    }

    @SneakyThrows
    @Override
    public void createIndex() {
        var createIndexRequest = new CreateIndexRequest(index);
        highRestClient().indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public <T> void indexRequest(T indexData, String id) {
        IndexRequest request = new IndexRequest(index).id("");
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
    public <T> T getDocId(String id, Class<T> entity) {
        SearchRequest searchRequest = new SearchRequest
                .Builder()
                .query(q -> q.match(m -> m.field("documentId")
                        .query(FieldValue.of(id)))).index(index)
                .build();
        var searchResponse = lowRestClient().search(searchRequest, entity);
        return searchResponse.hits().hits().stream().map(Hit::source).filter(Objects::nonNull).findAny().orElse(null);
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
