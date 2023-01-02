package com.example.telegramnote.infra.openSearchService;

import com.example.telegramnote.infra.mapper.EnumMapper;
import com.example.telegramnote.infra.openSearchService.factory.HighLevelRestClientFactory;
import com.example.telegramnote.infra.openSearchService.factory.LowLevelRestClientFactory;
import com.example.telegramnote.infra.openSearchService.factory.RestClientFactory;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.opensearch.OpenSearchClient;

public class OpenSearchRestClient {
    static EnumMapper enumMapper;
    public static <T> AbstractRestClient<T> createRestClient(String restClientType) {
        var restClientEnum = enumMapper.toRestClientEnum(restClientType);
        var restClientFactory = restClientEnum.getRestClientFactory();
        var r = restClientFactory.createRestClientFactory();
        return restClientFactory.createRestClientFactory();
    }

    public void est() {
        RestClientFactory<OpenSearchClient> restClientFactory = new LowLevelRestClientFactory();
        var client = restClientFactory.createRestClientFactory();
        var restClient = client.restClient();
    }
}
