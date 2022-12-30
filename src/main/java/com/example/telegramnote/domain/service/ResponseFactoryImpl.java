package com.example.telegramnote.domain.service;

import com.example.telegramnote.domain.dto.ResponseDto;

import java.util.Collections;
import java.util.List;

public class ResponseFactoryImpl implements ResponseFactory {

    @Override
    public <T> ResponseDto<List<T>> createResponse(List<T> messages, String info, Class<T> entity) {
        return changeType(messages, info, entity);
    }

    public <T> ResponseDto<List<T>> createResponse(T message, String info, Class<T> entity) {
        var listMessage = Collections.singletonList(message);
        return changeType(listMessage, info, entity);
    }

    @Override
    public <T> ResponseDto<T> createResponse(String messages) {
        return new ResponseDto<>(null, messages);
    }

    private <TEntity, T> ResponseDto<List<TEntity>> changeType(List<T> messages, String info, Class<TEntity> entity) {
        return new ResponseDto<>( messages.stream().map(entity::cast).toList(), info);
    }
}