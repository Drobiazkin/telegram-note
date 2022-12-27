package com.example.telegramnote.infra.openSearchService;

import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.opensearch.OpenSearchClient;

public interface OpenSearchService {

    RestHighLevelClient createRestHighLevelClient();

    OpenSearchClient createRestClient();

}
