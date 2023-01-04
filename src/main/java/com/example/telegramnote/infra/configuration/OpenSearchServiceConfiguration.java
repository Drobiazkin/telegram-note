package com.example.telegramnote.infra.configuration;

import com.example.telegramnote.infra.adapter.openSearch.OpenSearchAdapter;
import com.example.telegramnote.infra.adapter.openSearch.OpenSearchAdapterImpl;
import com.example.telegramnote.infra.openSearchService.OpenSearchHighLevelRestClient;
import com.example.telegramnote.infra.openSearchService.OpenSearchLowLevelRestClient;
import com.example.telegramnote.infra.openSearchService.OpenSearchRestClientAbstract;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenSearchServiceConfiguration {

    @Bean
    public OpenSearchAdapter openSearchOperationService() {
        return new OpenSearchAdapterImpl();
    }

    @Bean
    public OpenSearchLowLevelRestClient openSearchLowLevelRestClient() {
        return new OpenSearchLowLevelRestClient();
    }
    @Bean
    public OpenSearchHighLevelRestClient openSearchHighLevelRestClient() {
        return new OpenSearchHighLevelRestClient();
    }
    @Bean
    public OpenSearchRestClientAbstract<RestHighLevelClient> restHighLevelClientOpenSearchRestClientAbstract() {
        return new OpenSearchHighLevelRestClient();
    }

    @Bean
    public OpenSearchRestClientAbstract<OpenSearchClient> openSearchClientOpenSearchRestClientAbstract() {
        return new OpenSearchLowLevelRestClient();
    }
}
