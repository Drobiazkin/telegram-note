package com.example.telegramnote.application.service;

import com.example.telegramnote.domain.service.keyboard.KeyboardService;
import com.example.telegramnote.domain.service.command.CommandHandler;
import com.example.telegramnote.infra.adapter.openSearch.OpenSearchAdapter;
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
    CommandHandler commonMessageService;
    OpenSearchAdapter openSearchAdapter;
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

    public TelegramService(KeyboardService keyboardService, CommandHandler commonMessageService, OpenSearchAdapter openSearchAdapter) {
        this.keyboardService = keyboardService;
        this.commonMessageService = commonMessageService;
        this.openSearchAdapter = openSearchAdapter;
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

    @SneakyThrows
    protected synchronized void answerCallbackQuery(Update update) {
        if (update.getCallbackQuery().getData().equals("1")) {
            openSearchAdapter.createIndex();
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
    protected synchronized void sendMsg(Update update) {
        var chatId = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        if ("/start".equals(update.getMessage().getText())) {
            sendMessage.setText("Воспользуйтесь командами на клавиатуре");
            keyboardService.setButtons(sendMessage);
            execute(sendMessage);
        } else {
            var response = commonMessageService.handleEvent(update.getMessage());
//        if (response != null && response.getPayload() != null && response.getPayload().get(0).equals("Вы уверены что хотите создать индекс?")) {
//            sendMessage.setReplyMarkup(keyboardService.setInline());
//            sendMessage.setText("Вы уверены что хотите создать индекс?");
//            execute(sendMessage);
//        }
            if (response.getInfo() != null) {
                sendMessage.setText(response.getInfo());
                execute(sendMessage);
            }
            if (response.getPayload() != null) {
                for (var entity : response.getPayload()) {
                    sendMessage.setText(entity.getMessage());
                    execute(sendMessage);
                }
            }
        }
    }
}
