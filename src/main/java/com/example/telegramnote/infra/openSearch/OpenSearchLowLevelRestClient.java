package com.example.telegramnote.infra.openSearch;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.rest_client.RestClientTransport;

public class OpenSearchLowLevelRestClient extends OpenSearchRestClientAbstract<OpenSearchClient> {
    private static OpenSearchClient openSearchClient;

    public OpenSearchLowLevelRestClient() {
        createRestLowLevelClient();
    }

    private void createRestLowLevelClient() {
        var builder = RestClient.builder(new HttpHost(elasticHosts, port, scheme))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        OpenSearchTransport transport = new RestClientTransport(builder.build(), new JacksonJsonpMapper());
        openSearchClient = new OpenSearchClient(transport);
    }

    @Override
    public OpenSearchClient getRestClient() {
        return openSearchClient;
    }
}
