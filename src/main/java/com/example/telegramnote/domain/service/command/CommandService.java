package com.example.telegramnote.domain.service.command;

import com.example.telegramnote.domain.dto.ResponseDto;
import com.example.telegramnote.domain.entity.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;


public interface CommandService {
    ResponseDto<List<MessageEntity>> replyToCommand(Message message);
}
