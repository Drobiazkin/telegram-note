package com.example.telegramnote.domain.service.command;

import com.example.telegramnote.domain.dto.ResponseDto;
import com.example.telegramnote.domain.entity.DocumentEntity;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;


public interface CommandService {
    ResponseDto<List<DocumentEntity>> replyToCommand(Message message);
}
