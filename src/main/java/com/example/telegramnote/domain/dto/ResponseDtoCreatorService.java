package com.example.telegramnote.domain.dto;

import java.util.List;

public interface ResponseDtoCreatorService {

    <T> ResponseDto<List<T>> createResponseDto(List<T> messages, String info, Class<T> entity);

    <T> ResponseDto<List<T>> createResponseDto(T messages, String info, Class<T> entity);

    <T> ResponseDto<T> createResponseDto(String info);

}
