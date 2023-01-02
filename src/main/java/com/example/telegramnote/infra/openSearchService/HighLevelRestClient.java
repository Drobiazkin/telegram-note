package com.example.telegramnote.infra.openSearchService;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.RestHighLevelClient;

public class HighLevelRestClient extends AbstractRestClient<RestHighLevelClient> {

    private RestHighLevelClient createRestHighLevelClient() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(elasticHosts, port, scheme))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        return new RestHighLevelClient(builder);
    }

    @Override
    public RestHighLevelClient restClient() {
        return createRestHighLevelClient();
    }
}