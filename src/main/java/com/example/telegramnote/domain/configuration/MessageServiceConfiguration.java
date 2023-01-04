package com.example.telegramnote.domain.configuration;

import com.example.telegramnote.domain.service.*;
import com.example.telegramnote.domain.service.command.CommandHandler;
import com.example.telegramnote.domain.service.command.DocumentCreation;
import com.example.telegramnote.domain.service.command.DocumentSearch;
import com.example.telegramnote.infra.adapter.openSearch.OpenSearchAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageServiceConfiguration {

    @Bean
    CommandHandler commonMessageService(OpenSearchAdapter openSearchAdapter, ResponseDtoCreatorService responseDtoCreatorService, DocumentSearch documentSearch, DocumentCreation documentCreation) {
        return new CommandHandler(openSearchAdapter, responseDtoCreatorService, documentSearch, documentCreation);
    }

    @Bean
    ResponseDtoCreatorService responseFactory() {
        return new ResponseDtoCreatorServiceImpl();
    }

    @Bean
    DocumentSearch messageService(OpenSearchAdapter openSearchAdapter, ResponseDtoCreatorService responseDtoCreatorService) {
        return new DocumentSearch(openSearchAdapter, responseDtoCreatorService);
    }

    @Bean
    DocumentCreation createDocumentService(OpenSearchAdapter openSearchAdapter, ResponseDtoCreatorService responseDtoCreatorService) {
        return new DocumentCreation(openSearchAdapter, responseDtoCreatorService);
    }
}
