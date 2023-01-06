package com.example.telegramnote.infra.openSearch;

import com.example.telegramnote.infra.openSearch.factory.OpenSearchHighLevelRestClientFactory;
import com.example.telegramnote.infra.openSearch.factory.OpenSearchLowLevelRestClientFactory;
import com.example.telegramnote.infra.openSearch.factory.OpenSearchRestClientFactory;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.opensearch.OpenSearchClient;

public class OpenSearchRestClientService {

    private static final String HIGH_LEVEL = "HIGH_LEVEL";
    private static final String LOW_LEVEL = "LOW_LEVEL";
    private static OpenSearchClient openSearchClient;
    private static RestHighLevelClient restHighLevelClient;

    public OpenSearchClient getOpenSearchClient() {
        return openSearchClient;
    }

    public RestHighLevelClient getRestHighLevelClient() {
        return restHighLevelClient;
    }

    public OpenSearchRestClientService(String restClientType) {
        createRestClient(restClientType);
    }

    public static void createRestClient(String restClientType) {
        switch (restClientType) {
            case LOW_LEVEL -> openSearchClient = getLowLevelClient();
            case HIGH_LEVEL -> restHighLevelClient = getHighLevelClient();
            default -> throw new RuntimeException();
        }
    }

    private static OpenSearchClient getLowLevelClient() {
        OpenSearchRestClientFactory<OpenSearchClient> openSearchRestClientFactory = new OpenSearchLowLevelRestClientFactory();
        return createClient(openSearchRestClientFactory).restClient();
    }

    private static RestHighLevelClient getHighLevelClient() {
        OpenSearchRestClientFactory<RestHighLevelClient> openSearchRestClientFactory = new OpenSearchHighLevelRestClientFactory();
        return createClient(openSearchRestClientFactory).restClient();
    }

    private static <T> OpenSearchRestClientAbstract<T> createClient(OpenSearchRestClientFactory<T> openSearchRestClientFactory) {
        return openSearchRestClientFactory.createRestClientFactory();
    }
}
