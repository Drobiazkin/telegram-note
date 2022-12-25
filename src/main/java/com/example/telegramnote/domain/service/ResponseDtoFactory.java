package com.example.telegramnote.domain.service;

import com.example.telegramnote.domain.dto.ResponseDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResponseDtoFactory {

    public ResponseDto<List<String>> createResponseDto(String message) {
        return ResponseDto.<List<String>>builder().payload(Collections.singletonList(message)).build();
    }

    public ResponseDto<List<String>> createResponseDto(List<String> messages) {
        return ResponseDto.<List<String>>builder().payload(messages).build();
    }
}
