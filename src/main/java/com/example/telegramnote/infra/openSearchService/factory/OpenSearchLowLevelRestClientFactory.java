package com.example.telegramnote.infra.openSearchService.factory;

import com.example.telegramnote.infra.openSearchService.OpenSearchRestClientAbstract;
import com.example.telegramnote.infra.openSearchService.OpenSearchLowLevelRestClient;
import org.opensearch.client.opensearch.OpenSearchClient;

public class OpenSearchLowLevelRestClientFactory implements OpenSearchRestClientFactory<OpenSearchClient> {
    @Override
    public OpenSearchRestClientAbstract<OpenSearchClient> createRestClientFactory() {
        return new OpenSearchLowLevelRestClient();
    }
}
