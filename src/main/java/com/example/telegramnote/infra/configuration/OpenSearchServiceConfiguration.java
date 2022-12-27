package com.example.telegramnote.infra.configuration;

import com.example.telegramnote.infra.openSearchService.OpenSearchOperationService;
import com.example.telegramnote.infra.openSearchService.OpenSearchOperationServiceImpl;
import com.example.telegramnote.infra.openSearchService.OpenSearchRestClientServiceImpl;
import com.example.telegramnote.infra.openSearchService.OpenSearchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenSearchServiceConfiguration {

    @Bean
    public OpenSearchService openSearchService() {
        return new OpenSearchRestClientServiceImpl();
    }

    @Bean
    public OpenSearchOperationService openSearchOperationService(
            OpenSearchService openSearchService) {
        return new OpenSearchOperationServiceImpl(openSearchService);
    }
}
