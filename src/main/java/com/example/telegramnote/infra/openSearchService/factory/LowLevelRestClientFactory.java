package com.example.telegramnote.infra.openSearchService.factory;

import com.example.telegramnote.infra.openSearchService.AbstractRestClient;
import com.example.telegramnote.infra.openSearchService.LowLevelRestClient;
import org.opensearch.client.opensearch.OpenSearchClient;

public class LowLevelRestClientFactory implements RestClientFactory<OpenSearchClient> {
    @Override
    public AbstractRestClient<OpenSearchClient> createRestClientFactory() {
        return new LowLevelRestClient();
    }
}
