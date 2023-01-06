package com.example.telegramnote.domain.entity;

import java.util.UUID;

public class DocumentEntityFactoryImpl implements DocumentEntityFactory {

    @Override
    public DocumentEntity createDocument(String message, String chatId) {
        return new DocumentEntity(UUID.randomUUID().toString(), message, chatId);
    }
}
