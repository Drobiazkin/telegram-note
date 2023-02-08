package com.example.telegramnote.domain.service.command;

import com.example.telegramnote.domain.dto.ResponseDto;
import com.example.telegramnote.domain.entity.DocumentEntity;
import com.example.telegramnote.domain.entity.DocumentEntityFactory;
import com.example.telegramnote.domain.dto.ResponseDtoCreatorService;
import com.example.telegramnote.domain.repository.DocumentEntityRepository;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public class CommandCreationDocument extends AbstractCommand implements CommandService {
    DocumentEntityFactory documentEntityFactory;
    ResponseDtoCreatorService responseDtoCreatorService;
    DocumentEntityRepository documentEntityRepository;

    public CommandCreationDocument(ResponseDtoCreatorService responseDtoCreatorService, DocumentEntityFactory documentEntityFactory, DocumentEntityRepository documentEntityRepository) {
        this.responseDtoCreatorService = responseDtoCreatorService;
        this.documentEntityFactory = documentEntityFactory;
        this.documentEntityRepository = documentEntityRepository;
    }

    @SneakyThrows
    @Override
    public ResponseDto<List<DocumentEntity>> replyToCommand(Message message) {
        var chatId = message.getChatId().toString();
        var messageText = message.getText().trim();
        if (message.getReplyToMessage() != null) {
            var documentEntity = documentEntityFactory.createDocument(messageText, chatId);
            documentEntityRepository.save(documentEntity, documentEntity.getDocumentId());
            return responseDtoCreatorService.createResponseDto(String.format(createdDocument, documentEntity.getDocumentId()));
        }
        return responseDtoCreatorService.createResponseDto(requestNotRecognized);
    }
}
