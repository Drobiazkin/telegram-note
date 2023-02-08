package com.example.telegramnote.infra.configuration;

import com.example.telegramnote.infra.adapter.OpenSearchAdapter;
import com.example.telegramnote.infra.adapter.OpenSearchAdapterImpl;
import com.example.telegramnote.infra.openSearch.OpenSearchHighLevelRestClient;
import com.example.telegramnote.infra.openSearch.OpenSearchLowLevelRestClient;
import com.example.telegramnote.infra.openSearch.OpenSearchRestClientAbstract;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfrastructureConfiguration {

    @Bean
    public OpenSearchAdapter openSearchOperationService(OpenSearchRestClientAbstract<RestHighLevelClient> restHighLevelClientOpenSearchRestClientAbstract,
                                                        OpenSearchRestClientAbstract<OpenSearchClient> openSearchClientOpenSearchRestClientAbstract) {
        return new OpenSearchAdapterImpl(restHighLevelClientOpenSearchRestClientAbstract, openSearchClientOpenSearchRestClientAbstract);
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
