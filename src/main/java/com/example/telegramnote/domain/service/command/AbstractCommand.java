package com.example.telegramnote.domain.service.command;

public abstract class AbstractCommand {
    protected static final String createdDocument = "Запись создана %s";
    protected static final String createDataRequest = "Отправьте данные которые необходимо создать";
    protected static final String searchDataRequest = "Отправьте текст который необходимо найти";
    protected static final String createIndexRequest = "Вы уверены что хотите создать индекс?";
    protected static final String requestNotRecognized = "Ваш запрос не распознан";
}
