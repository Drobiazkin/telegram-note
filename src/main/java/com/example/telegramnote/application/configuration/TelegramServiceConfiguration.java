package com.example.telegramnote.application.configuration;

import com.example.telegramnote.domain.service.keyboard.KeyboardService;
import com.example.telegramnote.application.service.TelegramService;
import com.example.telegramnote.domain.service.command.CommandHandler;
import com.example.telegramnote.infra.adapter.openSearch.OpenSearchAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramServiceConfiguration {

    @Bean
    TelegramService telegramService(KeyboardService keyboardService,
                                    CommandHandler commonMessageService,
                                    OpenSearchAdapter openSearchAdapter) {
        return new TelegramService(keyboardService,
                commonMessageService,
                openSearchAdapter);
    }

    @Bean
    KeyboardService buttonsService() {
        return new KeyboardService();
    }
}
