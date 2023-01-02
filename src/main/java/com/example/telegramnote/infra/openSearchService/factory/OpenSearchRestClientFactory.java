package com.example.telegramnote.infra.openSearchService.factory;

import com.example.telegramnote.infra.openSearchService.OpenSearchRestClientAbstract;

public interface OpenSearchRestClientFactory<T> {

    OpenSearchRestClientAbstract<T> createRestClientFactory();
}
