package com.example.telegramnote.domain.service;

import com.example.telegramnote.domain.dto.ResponseDto;
import com.example.telegramnote.domain.entity.MessageEntity;
import com.example.telegramnote.infra.openSearchService.OpenSearchOperationService;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.UUID;

public class CreateDocumentService extends AbstractMessage implements MessageService {
    OpenSearchOperationService openSearchOperationService;
    ResponseFactory responseFactory;

    public CreateDocumentService(OpenSearchOperationService openSearchOperationService, ResponseFactory responseFactory) {
        this.openSearchOperationService = openSearchOperationService;
        this.responseFactory = responseFactory;
    }

    @SneakyThrows
    @Override
    public ResponseDto<List<MessageEntity>> createResponseMessage(Message message) {
        var chatId = message.getChatId().toString();
        var messageText = message.getText().trim();
        if (message.getReplyToMessage() != null) {
            var messageEntity = new MessageEntity(UUID.randomUUID().toString(), messageText, chatId);
            openSearchOperationService.indexRequest(messageEntity);
            return responseFactory.createResponse(String.format(createdDocument,messageEntity.getMessageId().trim()));
        }
        return responseFactory.createResponse(requestNotRecognized);
    }
}
