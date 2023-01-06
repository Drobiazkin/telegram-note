package com.example.telegramnote.domain.service.command;

import com.example.telegramnote.domain.dto.ResponseDto;
import com.example.telegramnote.domain.entity.DocumentEntity;
import com.example.telegramnote.domain.dto.ResponseDtoCreatorService;
import com.example.telegramnote.domain.repository.DocumentEntityRepository;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public class CommandSearchDocument extends AbstractCommand implements CommandService {

    ResponseDtoCreatorService responseDtoCreatorService;
    DocumentEntityRepository documentEntityRepository;

    public CommandSearchDocument(ResponseDtoCreatorService responseDtoCreatorService, DocumentEntityRepository documentEntityRepository) {
        this.responseDtoCreatorService = responseDtoCreatorService;
        this.documentEntityRepository = documentEntityRepository;
    }

    @SneakyThrows
    @Override
    public ResponseDto<List<DocumentEntity>> replyToCommand(Message message) {
        var messageText = message.getText().trim();
        if (message.getReplyToMessage() != null) {
            var documentEntities = documentEntityRepository.searchDocuments(messageText);
            if (documentEntities.isEmpty()) {
                return responseDtoCreatorService.createResponseDto("По вашему запросу ничего не найдено");
            } else {
                return responseDtoCreatorService.createResponseDto(documentEntities, "Запись найдена", DocumentEntity.class);
            }
        }
        return responseDtoCreatorService.createResponseDto(requestNotRecognized);
    }
}