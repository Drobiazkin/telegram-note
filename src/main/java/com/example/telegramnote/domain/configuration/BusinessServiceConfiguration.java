package com.example.telegramnote.domain.configuration;

import com.example.telegramnote.domain.dto.ResponseDtoCreatorService;
import com.example.telegramnote.domain.dto.ResponseDtoCreatorServiceImpl;
import com.example.telegramnote.domain.entity.DocumentEntityFactory;
import com.example.telegramnote.domain.entity.DocumentEntityFactoryImpl;
import com.example.telegramnote.domain.repository.DocumentEntityRepository;
import com.example.telegramnote.domain.repository.DocumentEntityRepositoryImpl;
import com.example.telegramnote.domain.service.command.*;
import com.example.telegramnote.domain.service.message.keyboard.KeyboardService;
import com.example.telegramnote.domain.service.message.MessageImpl;
import com.example.telegramnote.domain.service.message.MessageService;
import com.example.telegramnote.infra.adapter.openSearch.OpenSearchAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessServiceConfiguration {

    @Bean
    CommandHandlerService commonMessageService(ResponseDtoCreatorService responseDtoCreatorService, CommandService commandSearchDocument, CommandService commandCreationDocument, CommandService commandCreationIndex) {
        return new CommandHandlerImpl(responseDtoCreatorService, commandSearchDocument, commandCreationDocument, commandCreationIndex);
    }
    @Bean
    ResponseDtoCreatorService responseFactory() {
        return new ResponseDtoCreatorServiceImpl();
    }
    @Bean
    DocumentEntityFactory documentEntityFactory() {
        return new DocumentEntityFactoryImpl();
    }
    @Bean(name = "commandSearchDocument")
    CommandService commandSearchDocument(ResponseDtoCreatorService responseDtoCreatorService, DocumentEntityRepository documentEntityRepository) {
        return new CommandSearchDocument(responseDtoCreatorService, documentEntityRepository);
    }
    @Bean(name = "commandCreationDocument")
    CommandService commandCreationDocument(ResponseDtoCreatorService responseDtoCreatorService, DocumentEntityFactory documentEntityFactory, DocumentEntityRepository documentEntityRepository) {
        return new CommandCreationDocument(responseDtoCreatorService, documentEntityFactory, documentEntityRepository);
    }
    @Bean(name = "commandGetDocument")
    CommandService commandGetDocument(ResponseDtoCreatorService responseDtoCreatorService, DocumentEntityFactory documentEntityFactory, DocumentEntityRepository documentEntityRepository) {
        return new CommandGetDocument(responseDtoCreatorService, documentEntityFactory, documentEntityRepository);
    }
    @Bean(name = "commandCreationIndex")
    CommandService commandCreationIndex(ResponseDtoCreatorService responseDtoCreatorService, DocumentEntityFactory documentEntityFactory, DocumentEntityRepository documentEntityRepository) {
        return new CommandCreationIndex(responseDtoCreatorService, documentEntityFactory, documentEntityRepository);
    }
    @Bean
    DocumentEntityRepository messageEntityRepository(OpenSearchAdapter openSearchAdapter) {
        return new DocumentEntityRepositoryImpl(openSearchAdapter);
    }
    @Bean
    MessageService messageService(CommandHandlerService commandHandlerService, KeyboardService keyboardService) {
        return new MessageImpl(commandHandlerService, keyboardService);
    }
    @Bean
    KeyboardService keyboardService() {
        return new KeyboardService();
    }
}
