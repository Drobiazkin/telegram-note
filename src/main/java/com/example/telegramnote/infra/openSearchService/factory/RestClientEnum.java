package com.example.telegramnote.infra.openSearchService.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RestClientEnum {

    HIGH_LEVEL(new OpenSearchHighLevelRestClientFactory()),
    LOW_LEVEL(new OpenSearchLowLevelRestClientFactory());

}
