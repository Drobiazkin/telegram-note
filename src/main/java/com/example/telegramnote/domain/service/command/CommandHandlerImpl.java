package com.example.telegramnote.domain.service.command;

import com.example.telegramnote.domain.dto.ResponseDto;
import com.example.telegramnote.domain.entity.DocumentEntity;
import com.example.telegramnote.domain.dto.ResponseDtoCreatorService;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public class CommandHandlerImpl extends AbstractCommand implements CommandHandlerService {

    ResponseDtoCreatorService responseDtoCreatorService;
    CommandService commandSearchDocument;
    CommandService commandCreationDocument;


    public CommandHandlerImpl(ResponseDtoCreatorService responseDtoCreatorService, CommandService commandSearchDocument, CommandService commandCreationDocument) {
        this.responseDtoCreatorService = responseDtoCreatorService;
        this.commandSearchDocument = commandSearchDocument;
        this.commandCreationDocument = commandCreationDocument;
    }

    private CommandService defineCommandByMessage(String text) {
        if (searchDataRequest.equals(text)) {
            return commandSearchDocument;
        } else if (createDataRequest.equals(text)) {
            return commandCreationDocument;
        }
        return null;
    }

    @Override
    public ResponseDto<List<DocumentEntity>> handleEvent(Message message) {
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
                case "/start" -> responseDtoCreatorService.createResponseDto(requestBotStart);
                default -> responseDtoCreatorService.createResponseDto(requestNotRecognized);
            };
        }
    }
}
