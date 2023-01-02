package com.example.telegramnote.domain.service.command;

import com.example.telegramnote.domain.dto.ResponseDto;
import com.example.telegramnote.domain.entity.MessageEntity;
import com.example.telegramnote.domain.service.ResponseDtoCreatorService;
import com.example.telegramnote.infra.openSearchService.OpenSearchOperationService;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public class DocumentSearch extends AbstractCommand implements CommandService {

    OpenSearchOperationService openSearchOperationService;
    ResponseDtoCreatorService responseDtoCreatorService;

    public DocumentSearch(OpenSearchOperationService openSearchOperationService, ResponseDtoCreatorService responseDtoCreatorService) {
        this.openSearchOperationService = openSearchOperationService;
        this.responseDtoCreatorService = responseDtoCreatorService;
    }

    @SneakyThrows
    @Override
    public ResponseDto<List<MessageEntity>> replyToCommand(Message message) {
        var messageText = message.getText().trim();
        if (message.getReplyToMessage() != null) {
            var messages = openSearchOperationService.getDocs(messageText, MessageEntity.class);
            if (messages.isEmpty()) {
                return responseDtoCreatorService.createResponseDto("По вашему запросу ничего не найдено");
            } else {
                return responseDtoCreatorService.createResponseDto(messages, "Запись найдена", MessageEntity.class);
            }
        }
        return responseDtoCreatorService.createResponseDto(requestNotRecognized);
    }
}