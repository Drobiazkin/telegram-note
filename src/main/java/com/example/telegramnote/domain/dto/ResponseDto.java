package com.example.telegramnote.domain.dto;

import lombok.*;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@FieldNameConstants
public class ResponseDto<T> {

    private T payload;
    private String info;

    protected ResponseDto(T payload, String info) {
        this.payload = payload;
        this.info = info;
    }
}
