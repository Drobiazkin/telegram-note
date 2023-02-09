package com.example.telegramnote.infra.openSearch;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.RestHighLevelClient;

public class OpenSearchHighLevelRestClient extends OpenSearchRestClientAbstract<RestHighLevelClient> {
    private RestHighLevelClient restHighLevelClient;

    public OpenSearchHighLevelRestClient() {
        createRestHighLevelClient();
    }

    private void createRestHighLevelClient() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(elasticHosts, port, scheme))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        restHighLevelClient = new RestHighLevelClient(builder);
    }

    @Override
    public RestHighLevelClient getRestClient() {
        return restHighLevelClient;
    }
}