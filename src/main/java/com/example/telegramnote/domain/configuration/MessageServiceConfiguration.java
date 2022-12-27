package com.example.telegramnote.domain.configuration;

import com.example.telegramnote.domain.service.MessageService;
import com.example.telegramnote.domain.service.ResponseDtoFactory;
import com.example.telegramnote.infra.openSearchService.OpenSearchOperationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageServiceConfiguration {

    @Bean
    MessageService messageService(OpenSearchOperationService openSearchOperationService, ResponseDtoFactory responseDtoFactory) {
        return new MessageService(openSearchOperationService, responseDtoFactory);
    }

    @Bean
    ResponseDtoFactory responseDtoFactory() {
        return new ResponseDtoFactory();
    }
}
