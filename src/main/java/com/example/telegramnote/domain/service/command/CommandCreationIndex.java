package com.example.telegramnote.domain.service.command;

import com.example.telegramnote.domain.dto.ResponseDto;
import com.example.telegramnote.domain.dto.ResponseDtoCreatorService;
import com.example.telegramnote.domain.entity.DocumentEntity;
import com.example.telegramnote.domain.entity.DocumentEntityFactory;
import com.example.telegramnote.domain.repository.DocumentEntityRepository;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public class CommandCreationIndex extends AbstractCommand implements CommandService {

    DocumentEntityFactory documentEntityFactory;
    ResponseDtoCreatorService responseDtoCreatorService;
    DocumentEntityRepository documentEntityRepository;

    public CommandCreationIndex(ResponseDtoCreatorService responseDtoCreatorService, DocumentEntityFactory documentEntityFactory, DocumentEntityRepository documentEntityRepository) {
        this.responseDtoCreatorService = responseDtoCreatorService;
        this.documentEntityFactory = documentEntityFactory;
        this.documentEntityRepository = documentEntityRepository;
    }

    @Override
    public ResponseDto<List<DocumentEntity>> replyToCommand(Message message) {
        if (message.getReplyToMessage() != null) {
            documentEntityRepository.createIndex();
            return responseDtoCreatorService.createResponseDto(createdIndex);
        }
        return responseDtoCreatorService.createResponseDto(requestNotRecognized);
    }
}
