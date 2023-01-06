package com.example.telegramnote.domain.service.command;

import com.example.telegramnote.domain.dto.ResponseDto;
import com.example.telegramnote.domain.entity.DocumentEntity;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public interface CommandHandlerService {

    ResponseDto<List<DocumentEntity>> handleEvent(Message message);
}
