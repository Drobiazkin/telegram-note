package com.example.telegramnote.domain.entity;

public interface DocumentEntityFactory {
    DocumentEntity createDocument(String message, String chatId);
}
