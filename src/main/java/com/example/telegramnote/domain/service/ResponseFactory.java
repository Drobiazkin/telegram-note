package com.example.telegramnote.domain.service;

import com.example.telegramnote.domain.dto.ResponseDto;

import java.util.List;

public interface ResponseFactory {

    <T> ResponseDto<List<T>> createResponse(List<T> messages, String info, Class<T> entity);

    <T> ResponseDto<List<T>> createResponse(T messages, String info, Class<T> entity);

    <T> ResponseDto<T> createResponse(String info);

}
