package com.example.telegramnote.controller;

import com.example.telegramnote.domain.service.message.MessageService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Slf4j
public class TelegramController extends TelegramLongPollingBot {

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

    public TelegramController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Runnable threadUpdate = () -> {
            if (update.hasMessage()) {
                sendMsg(update);
            } else if (update.hasCallbackQuery()) {
                answerCallbackQuery(update);
            }
        };
        new Thread(threadUpdate).start();
    }

    //TODO Implement in the future
    @SneakyThrows
    protected void answerCallbackQuery(Update update) {
        if (update.getCallbackQuery().getData().equals("1")) {
            var callbackId = update.getCallbackQuery().getId();
            String message = "Индекс создан";
            AnswerCallbackQuery answer = new AnswerCallbackQuery();
            answer.setCallbackQueryId(callbackId);
            answer.setText(message);
            answer.setShowAlert(true);
            execute(answer);
        }
    }

    @SneakyThrows(TelegramApiException.class)
    protected void sendMsg(Update update) {
        var responseBody = messageService.createResponseMessage(update.getMessage());
        for(var body : responseBody) {
            executeAsync(body);
        }
    }
}
