package com.example.telegramnote.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class ResponseDto<T> {

    private T payload;
    private String info;

}
