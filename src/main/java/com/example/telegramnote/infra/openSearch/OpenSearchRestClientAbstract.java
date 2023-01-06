package com.example.telegramnote.infra.openSearch;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;

public abstract class OpenSearchRestClientAbstract<T> {

    protected static final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

    protected static final String elasticHosts = "localhost";

    protected static final int port = 9200;

    protected static final String scheme = "http";

    public abstract T restClient();

    public OpenSearchRestClientAbstract() {
        createCredentials();
    }

    protected final void createCredentials() {
        String username = "admin";
        String password = "admin";
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));
    }
}
