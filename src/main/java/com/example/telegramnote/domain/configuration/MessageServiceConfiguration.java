package com.example.telegramnote.domain.configuration;

import com.example.telegramnote.domain.service.*;
import com.example.telegramnote.domain.service.command.CommandHandler;
import com.example.telegramnote.domain.service.command.DocumentCreation;
import com.example.telegramnote.domain.service.command.DocumentSearch;
import com.example.telegramnote.infra.openSearchService.OpenSearchOperationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageServiceConfiguration {

    @Bean
    CommandHandler commonMessageService(OpenSearchOperationService openSearchOperationService, ResponseDtoCreatorService responseDtoCreatorService, DocumentSearch documentSearch, DocumentCreation documentCreation) {
        return new CommandHandler(openSearchOperationService, responseDtoCreatorService, documentSearch, documentCreation);
    }

    @Bean
    ResponseDtoCreatorService responseFactory() {
        return new ResponseDtoCreatorServiceImpl();
    }

    @Bean
    DocumentSearch messageService(OpenSearchOperationService openSearchOperationService, ResponseDtoCreatorService responseDtoCreatorService) {
        return new DocumentSearch(openSearchOperationService, responseDtoCreatorService);
    }

    @Bean
    DocumentCreation createDocumentService(OpenSearchOperationService openSearchOperationService, ResponseDtoCreatorService responseDtoCreatorService) {
        return new DocumentCreation(openSearchOperationService, responseDtoCreatorService);
    }
}
