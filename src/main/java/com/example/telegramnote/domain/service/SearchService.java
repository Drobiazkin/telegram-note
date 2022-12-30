package com.example.telegramnote.domain.service;

import com.example.telegramnote.domain.dto.ResponseDto;
import com.example.telegramnote.domain.entity.MessageEntity;
import com.example.telegramnote.infra.openSearchService.OpenSearchOperationService;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public class SearchService extends AbstractMessage implements MessageService {

    OpenSearchOperationService openSearchOperationService;
    ResponseFactory responseFactory;

    public SearchService(OpenSearchOperationService openSearchOperationService, ResponseFactory responseFactory) {
        this.openSearchOperationService = openSearchOperationService;
        this.responseFactory = responseFactory;
    }

    @SneakyThrows
    @Override
    public ResponseDto<List<MessageEntity>> createResponseMessage(Message message) {
        var messageText = message.getText().trim();
        if (message.getReplyToMessage() != null) {
            var messages = openSearchOperationService.getDocs(messageText, MessageEntity.class);
            if (messages.isEmpty()) {
                return responseFactory.createResponse("По вашему запросу ничего не найдено");
            } else {
                return responseFactory.createResponse(messages, "Запись найдена", MessageEntity.class);
            }
        }
        return responseFactory.createResponse(requestNotRecognized);
    }
}