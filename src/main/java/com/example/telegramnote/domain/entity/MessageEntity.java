package com.example.telegramnote.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import nonapi.io.github.classgraph.json.Id;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class MessageEntity implements Serializable {

    @Id
    private String messageId;

    @JsonProperty("message")
    private String message;

    @JsonProperty("chatId")
    private String chatId;

    @Override
    public String toString() {
        return String.format("IndexData{message Id = '%s', message='%s', chat Id='%s'}", messageId, message, chatId);
    }
}
