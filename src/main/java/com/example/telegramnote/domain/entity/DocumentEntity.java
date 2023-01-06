package com.example.telegramnote.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import nonapi.io.github.classgraph.json.Id;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@FieldNameConstants
public class DocumentEntity implements Serializable {

    protected DocumentEntity(String documentId, String message, String chatId) {
        this.documentId = documentId;
        this.message = message;
        this.chatId = chatId;
    }

    @Id
    private String documentId;

    @JsonProperty("message")
    private String message;

    @JsonProperty("chatId")
    private String chatId;

    @Override
    public String toString() {
        return String.format("IndexData{message Id = '%s', message='%s', chat Id='%s'}", documentId, message, chatId);
    }
}
