package com.example.telegramnote.domain.service.command;

import com.example.telegramnote.domain.dto.ResponseDto;
import com.example.telegramnote.domain.entity.MessageEntity;
import com.example.telegramnote.domain.service.ResponseDtoCreatorService;
import com.example.telegramnote.infra.openSearchService.OpenSearchOperationService;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public class CommandHandler extends AbstractCommand {

    OpenSearchOperationService openSearchOperationService;
    ResponseDtoCreatorService responseDtoCreatorService;
    DocumentSearch documentSearch;
    DocumentCreation documentCreation;


    public CommandHandler(OpenSearchOperationService openSearchOperationService, ResponseDtoCreatorService responseDtoCreatorService, DocumentSearch documentSearch, DocumentCreation documentCreation) {
        this.openSearchOperationService = openSearchOperationService;
        this.responseDtoCreatorService = responseDtoCreatorService;
        this.documentSearch = documentSearch;
        this.documentCreation = documentCreation;
    }

    private CommandService defineCommandByMessage(String text) {
        if (searchDataRequest.equals(text)) {
            return documentSearch;
        } else if (createDataRequest.equals(text)) {
            return documentCreation;
        }
        return null;
    }

    public ResponseDto<List<MessageEntity>> handleEvent(Message message) {
        if (message.getReplyToMessage() != null) {
            var commandService = defineCommandByMessage(message.getReplyToMessage().getText());
            assert commandService != null;
            return commandService.replyToCommand(message);
        } else {
            var messageText = message.getText().trim();
            return switch (messageText) {
                case "Создать запись в БД" -> responseDtoCreatorService.createResponseDto(createDataRequest);
                case "Получить запись из БД" -> responseDtoCreatorService.createResponseDto(searchDataRequest);
                case "Создать индекс" -> responseDtoCreatorService.createResponseDto(createIndexRequest);
                default -> responseDtoCreatorService.createResponseDto(requestNotRecognized);
            };
        }
    }
}
