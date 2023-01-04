package com.example.telegramnote.domain.service.command;

import com.example.telegramnote.domain.dto.ResponseDto;
import com.example.telegramnote.domain.entity.MessageEntity;
import com.example.telegramnote.domain.service.ResponseDtoCreatorService;
import com.example.telegramnote.infra.adapter.openSearch.OpenSearchAdapter;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.UUID;

public class DocumentCreation extends AbstractCommand implements CommandService {
    OpenSearchAdapter openSearchAdapter;
    ResponseDtoCreatorService responseDtoCreatorService;

    public DocumentCreation(OpenSearchAdapter openSearchAdapter, ResponseDtoCreatorService responseDtoCreatorService) {
        this.openSearchAdapter = openSearchAdapter;
        this.responseDtoCreatorService = responseDtoCreatorService;
    }

    @SneakyThrows
    @Override
    public ResponseDto<List<MessageEntity>> replyToCommand(Message message) {
        var chatId = message.getChatId().toString();
        var messageText = message.getText().trim();
        if (message.getReplyToMessage() != null) {
            var messageEntity = new MessageEntity(UUID.randomUUID().toString(), messageText, chatId);
            openSearchAdapter.indexRequest(messageEntity);
            return responseDtoCreatorService.createResponseDto(String.format(createdDocument,messageEntity.getMessageId().trim()));
        }
        return responseDtoCreatorService.createResponseDto(requestNotRecognized);
    }
}
