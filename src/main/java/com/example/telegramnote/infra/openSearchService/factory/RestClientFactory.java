package com.example.telegramnote.infra.openSearchService.factory;

import com.example.telegramnote.infra.openSearchService.AbstractRestClient;

public interface RestClientFactory<T> {

    AbstractRestClient<T> createRestClientFactory();
}
