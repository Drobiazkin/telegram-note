package com.example.telegramnote.controller.configuration;

import com.example.telegramnote.controller.TelegramController;
import com.example.telegramnote.domain.service.message.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {

    @Bean
    TelegramController telegramController(MessageService messageService) {
        return new TelegramController(messageService);
    }
}
