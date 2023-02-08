package com.example.telegramnote.infra.openSearch;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.RestHighLevelClient;

public class OpenSearchHighLevelRestClient extends OpenSearchRestClientAbstract<RestHighLevelClient> {
    private static volatile RestHighLevelClient restHighLevelClient;

    private RestHighLevelClient createRestHighLevelClient() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(elasticHosts, port, scheme))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        return new RestHighLevelClient(builder);
    }

    @Override
    public RestHighLevelClient getRestClient() {
        if (restHighLevelClient == null) {
            synchronized (RestHighLevelClient.class) {
                if (restHighLevelClient == null) {
                    restHighLevelClient = createRestHighLevelClient();
                }
            }
        }
        return restHighLevelClient;
    }
}