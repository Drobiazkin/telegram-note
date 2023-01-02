package com.example.telegramnote.infra.openSearchService.factory;

import com.example.telegramnote.infra.openSearchService.OpenSearchRestClientAbstract;
import com.example.telegramnote.infra.openSearchService.OpenSearchHighLevelRestClient;
import org.opensearch.client.RestHighLevelClient;

public class OpenSearchHighLevelRestClientFactory implements OpenSearchRestClientFactory<RestHighLevelClient> {
    @Override
    public OpenSearchRestClientAbstract<RestHighLevelClient> createRestClientFactory() {
        return new OpenSearchHighLevelRestClient();
    }
}
