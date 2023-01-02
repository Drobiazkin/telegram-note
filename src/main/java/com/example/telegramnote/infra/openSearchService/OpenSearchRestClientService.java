package com.example.telegramnote.infra.openSearchService;

import com.example.telegramnote.infra.openSearchService.factory.OpenSearchHighLevelRestClientFactory;
import com.example.telegramnote.infra.openSearchService.factory.OpenSearchLowLevelRestClientFactory;
import com.example.telegramnote.infra.openSearchService.factory.OpenSearchRestClientFactory;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.opensearch.OpenSearchClient;

public class OpenSearchRestClientService {

    private static final String HIGH_LEVEL = "HIGH_LEVEL";
    private static final String LOW_LEVEL = "LOW_LEVEL";

    public static <T> OpenSearchRestClientAbstract<T> createRestClient(String restClientType) {
        switch (restClientType) {
            case HIGH_LEVEL -> {
                return getHighLevelClient();
            }
            case LOW_LEVEL ->
            {
                return getLowLevelClient();
            }
            default -> {
                return null;
            }
        }
    }

    private static OpenSearchRestClientAbstract<OpenSearchClient> getLowLevelClient() {
        OpenSearchRestClientFactory<OpenSearchClient> openSearchRestClientFactory = new OpenSearchLowLevelRestClientFactory();
        return createClient(openSearchRestClientFactory);
    }

    private static OpenSearchRestClientAbstract<RestHighLevelClient> getHighLevelClient() {
        OpenSearchRestClientFactory<RestHighLevelClient> openSearchRestClientFactory = new OpenSearchHighLevelRestClientFactory();
        return createClient(openSearchRestClientFactory);
    }

    private static <T> OpenSearchRestClientAbstract<T> createClient(OpenSearchRestClientFactory<T> openSearchRestClientFactory) {
        return openSearchRestClientFactory.createRestClientFactory();
    }
}
