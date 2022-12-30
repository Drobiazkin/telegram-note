package com.example.telegramnote.application.configuration;

import com.example.telegramnote.domain.service.KeyboardService;
import com.example.telegramnote.application.service.TelegramService;
import com.example.telegramnote.domain.service.CommonMessageService;
import com.example.telegramnote.infra.openSearchService.OpenSearchOperationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramServiceConfiguration {

    @Bean
    TelegramService telegramService(KeyboardService keyboardService,
                                    CommonMessageService commonMessageService,
                                    OpenSearchOperationService openSearchOperationService) {
        return new TelegramService(keyboardService,
                commonMessageService,
                openSearchOperationService);
    }

    @Bean
    KeyboardService buttonsService() {
        return new KeyboardService();
    }
}
