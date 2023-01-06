package com.example.telegramnote.domain.service.message;

import com.example.telegramnote.domain.service.command.CommandHandlerService;
import com.example.telegramnote.domain.service.message.keyboard.KeyboardService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageImpl implements MessageService {

    KeyboardService keyboardService;
    CommandHandlerService commandHandlerService;

    public MessageImpl(CommandHandlerService commandHandlerService, KeyboardService keyboardService) {
        this.commandHandlerService = commandHandlerService;
        this.keyboardService = keyboardService;
    }

    public List<SendMessage> createResponseMessage(Message message) {
        var chatId = message.getChatId();
        var sendMessages = new ArrayList<SendMessage>();
        var responseBody = commandHandlerService.handleEvent(message);
//        if (response != null && response.getPayload() != null && response.getPayload().get(0).equals("Вы уверены что хотите создать индекс?")) {
//            sendMessage.setReplyMarkup(keyboardService.setInline());
//            sendMessage.setText("Вы уверены что хотите создать индекс?");
//            execute(sendMessage);
//        }
        if (responseBody.getInfo() != null) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.enableMarkdown(true);
            sendMessage.setChatId(chatId);
            sendMessage.setText(responseBody.getInfo());
            keyboardService.setButtons(sendMessage);
            sendMessages.add(sendMessage);
        }
        if (responseBody.getPayload() != null) {
            for (var entity : responseBody.getPayload()) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.enableMarkdown(true);
                sendMessage.setChatId(chatId);
                sendMessage.setText(entity.getMessage());
                sendMessages.add(sendMessage);
            }
        }
        return sendMessages;
    }
}
