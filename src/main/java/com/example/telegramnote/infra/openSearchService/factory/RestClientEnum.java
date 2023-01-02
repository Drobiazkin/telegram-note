package com.example.telegramnote.infra.openSearchService.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.opensearch.client.RestHighLevelClient;

@AllArgsConstructor
@Getter
public enum RestClientEnum {

    HIGH_LEVEL(new HighLevelRestClientFactory()),
    LOW_LEVEL(new LowLevelRestClientFactory());
    private final RestClientFactory restClientFactory;

}
