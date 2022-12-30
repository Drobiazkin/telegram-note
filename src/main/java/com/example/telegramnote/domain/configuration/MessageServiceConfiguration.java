package com.example.telegramnote.domain.configuration;

import com.example.telegramnote.domain.service.*;
import com.example.telegramnote.infra.openSearchService.OpenSearchOperationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageServiceConfiguration {

    @Bean
    CommonMessageService commonMessageService(OpenSearchOperationService openSearchOperationService, ResponseFactory responseFactory, SearchService searchService, CreateDocumentService createDocumentService) {
        return new CommonMessageService(openSearchOperationService, responseFactory, searchService, createDocumentService);
    }

    @Bean
    ResponseFactory responseFactory() {
        return new ResponseFactoryImpl();
    }

    @Bean
    SearchService messageService(OpenSearchOperationService openSearchOperationService, ResponseFactory responseFactory) {
        return new SearchService(openSearchOperationService, responseFactory);
    }

    @Bean
    CreateDocumentService createDocumentService(OpenSearchOperationService openSearchOperationService, ResponseFactory responseFactory) {
        return new CreateDocumentService(openSearchOperationService, responseFactory);
    }
}
