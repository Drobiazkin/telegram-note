package com.example.telegramnote.infra.openSearch.factory;

import com.example.telegramnote.infra.openSearch.OpenSearchRestClientAbstract;

public interface OpenSearchRestClientFactory<T> {

    OpenSearchRestClientAbstract<T> createRestClientFactory();
}
