package com.example.telegramnote.infra.openSearch.factory;

import com.example.telegramnote.infra.openSearch.OpenSearchRestClientAbstract;
import com.example.telegramnote.infra.openSearch.OpenSearchLowLevelRestClient;
import org.opensearch.client.opensearch.OpenSearchClient;

public class OpenSearchLowLevelRestClientFactory implements OpenSearchRestClientFactory<OpenSearchClient> {
    @Override
    public OpenSearchRestClientAbstract<OpenSearchClient> createRestClientFactory() {
        return new OpenSearchLowLevelRestClient();
    }
}
