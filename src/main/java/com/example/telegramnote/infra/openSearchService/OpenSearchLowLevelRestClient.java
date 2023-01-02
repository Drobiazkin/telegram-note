package com.example.telegramnote.infra.openSearchService;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.rest_client.RestClientTransport;

public class OpenSearchLowLevelRestClient extends OpenSearchRestClientAbstract<OpenSearchClient> {
    public OpenSearchClient createRestLowLevelClient() {
        var builder = RestClient.builder(new HttpHost(elasticHosts, port, scheme))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        OpenSearchTransport transport = new RestClientTransport(builder.build(), new JacksonJsonpMapper());
        return new OpenSearchClient(transport);
    }

    @Override
    public OpenSearchClient restClient() {
        return createRestLowLevelClient();
    }
}
