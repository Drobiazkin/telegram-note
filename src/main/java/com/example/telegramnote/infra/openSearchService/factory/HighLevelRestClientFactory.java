package com.example.telegramnote.infra.openSearchService.factory;

import com.example.telegramnote.infra.openSearchService.AbstractRestClient;
import com.example.telegramnote.infra.openSearchService.HighLevelRestClient;
import org.opensearch.client.RestHighLevelClient;

public class HighLevelRestClientFactory implements RestClientFactory<RestHighLevelClient> {
    @Override
    public AbstractRestClient<RestHighLevelClient> createRestClientFactory() {
        return new HighLevelRestClient();
    }
}
