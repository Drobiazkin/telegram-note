package com.example.telegramnote.domain.service;

import com.example.telegramnote.domain.dto.ResponseDto;
import com.example.telegramnote.domain.entity.MessageEntity;
import com.example.telegramnote.infra.openSearchService.OpenSearchOperationService;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessageService {

    private static final String createdDocument = "Запись создана %s";

    OpenSearchOperationService openSearchOperationService;
    ResponseDtoFactory responseDtoFactory;

    public MessageService(OpenSearchOperationService openSearchOperationService, ResponseDtoFactory responseDtoFactory) {
        this.openSearchOperationService = openSearchOperationService;
        this.responseDtoFactory = responseDtoFactory;
    }

    @SneakyThrows
    public ResponseDto<List<String>> createResponseMessage(Message message) {
        var chatId = message.getChatId().toString();
        var messageText = message.getText().trim();
        if (messageText.equals("Создать запись в БД")) {
            return responseDtoFactory.createResponseDto("Отправьте данные которые необходимо создать");
        } else if (messageText.equals("Получить запись из БД")) {
            return responseDtoFactory.createResponseDto("Отправьте текст который необходимо найти");
        } else {
            if (message.getReplyToMessage() != null) {
                var messageContext = message.getReplyToMessage().getText();
                if ("Отправьте данные которые необходимо создать".equals(messageContext)) {
                    var messageEntity = new MessageEntity(UUID.randomUUID().toString(), messageText, chatId);
                    openSearchOperationService.indexRequest(messageEntity);
                    return responseDtoFactory.createResponseDto(String.format(createdDocument, messageEntity.getMessageId()));
                } else if ("Отправьте текст который необходимо найти".equals(messageContext)) {
                    var messages = new ArrayList<>(openSearchOperationService.getDocs(messageText, MessageEntity.class).stream().map(MessageEntity::getMessage).toList());
                    if (messages.isEmpty()) {
                        messages.add("Ничего не найдено");
                    }
                    return responseDtoFactory.createResponseDto(messages);
                }
            }
        }
        return null;
    }
}
