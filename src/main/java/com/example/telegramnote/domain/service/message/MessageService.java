package com.example.telegramnote.domain.service.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public interface MessageService {

    List<SendMessage> createResponseMessage(Message message);
}
