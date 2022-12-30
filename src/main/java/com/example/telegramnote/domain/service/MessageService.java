package com.example.telegramnote.domain.service;

import com.example.telegramnote.domain.dto.ResponseDto;
import com.example.telegramnote.domain.entity.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;


public interface MessageService {
    ResponseDto<List<MessageEntity>> createResponseMessage(Message message);
}
