package com.example.telegramnote.domain.service;

import com.example.telegramnote.domain.dto.ResponseDto;
import com.example.telegramnote.domain.entity.MessageEntity;
import com.example.telegramnote.infra.openSearchService.OpenSearchOperationService;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public class CommonMessageService extends AbstractMessage {

    OpenSearchOperationService openSearchOperationService;
    ResponseFactory responseFactory;
    SearchService searchService;
    CreateDocumentService createDocumentService;


    public CommonMessageService(OpenSearchOperationService openSearchOperationService, ResponseFactory responseFactory, SearchService searchService, CreateDocumentService createDocumentService) {
        this.openSearchOperationService = openSearchOperationService;
        this.responseFactory = responseFactory;
        this.searchService = searchService;
        this.createDocumentService = createDocumentService;
    }

    private MessageService createMessageByType(String text) {
        if (searchDataRequest.equals(text)) {
            return searchService;
        } else if (createDataRequest.equals(text)) {
            return createDocumentService;
        }
        return null;
    }

    public ResponseDto<List<MessageEntity>> createResponseMessage(Message message) {
        if (message.getReplyToMessage() != null) {
            var messageService = createMessageByType(message.getReplyToMessage().getText());
            assert messageService != null;
            return messageService.createResponseMessage(message);
        } else {
            var messageText = message.getText().trim();
            return switch (messageText) {
                case "Создать запись в БД" -> responseFactory.createResponse(createDataRequest);
                case "Получить запись из БД" -> responseFactory.createResponse(searchDataRequest);
                case "Создать индекс" -> responseFactory.createResponse(createIndexRequest);
                default -> responseFactory.createResponse(requestNotRecognized);
            };
        }
    }
}
