package com.example.telegramnote.infra.openSearch.factory;

import com.example.telegramnote.infra.openSearch.OpenSearchRestClientAbstract;
import com.example.telegramnote.infra.openSearch.OpenSearchHighLevelRestClient;
import org.opensearch.client.RestHighLevelClient;

public class OpenSearchHighLevelRestClientFactory implements OpenSearchRestClientFactory<RestHighLevelClient> {
    @Override
    public OpenSearchRestClientAbstract<RestHighLevelClient> createRestClientFactory() {
        return new OpenSearchHighLevelRestClient();
    }
}
