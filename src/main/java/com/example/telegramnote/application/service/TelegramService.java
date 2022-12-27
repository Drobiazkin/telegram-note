package com.example.telegramnote.application.service;

import com.example.telegramnote.domain.service.MessageService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Slf4j
public class TelegramService extends TelegramLongPollingBot {
    KeyboardService keyboardService;
    MessageService messageService;
    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    protected TelegramService(KeyboardService keyboardService, MessageService messageService) {
        this.keyboardService = keyboardService;
        this.messageService = messageService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        new Thread(() -> {
            if (update.hasMessage()) {
                sendMsg(update);
            } else if (update.hasCallbackQuery()) {
                answerCallbackQuery(update);
            }
        }).start();
    }

    @SneakyThrows
    protected synchronized void answerCallbackQuery(Update update) {
        var callbackId = update.getCallbackQuery().getId();
        String message = null;
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(callbackId);
        answer.setText(message);
        answer.setShowAlert(true);
        execute(answer);
    }

    @SneakyThrows(TelegramApiException.class)
    protected synchronized void sendMsg(Update update) {
        var chatId = update.getMessage().getChatId();
        var response = messageService.createResponseMessage(update.getMessage());
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
//        if (message.equals("") || message.equals("")) {
//            sendMessage.setReplyMarkup(keyboardService.setInline());
//            sendMessage.setText("");
//        } else {
        if (response != null && response.getPayload() != null) {
            for (var text : response.getPayload()) {
                sendMessage.setText(text);
                execute(sendMessage);
            }
        } else {
            sendMessage.setText("Воспользуйтесь командами на клавиатуре");
            keyboardService.setButtons(sendMessage);
            execute(sendMessage);
        }
    }
}
