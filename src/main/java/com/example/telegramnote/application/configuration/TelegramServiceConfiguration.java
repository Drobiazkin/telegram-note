package com.example.telegramnote.application.configuration;

import com.example.telegramnote.application.service.KeyboardService;
import com.example.telegramnote.application.service.TelegramService;
import com.example.telegramnote.domain.service.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramServiceConfiguration {

    @Bean
    TelegramService telegramService(KeyboardService keyboardService, MessageService messageService) {
        return new TelegramService(keyboardService, messageService);
    }

    @Bean
    KeyboardService buttonsService() {
        return new KeyboardService();
    }
}
