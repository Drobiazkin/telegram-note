package com.example.telegramnote.infra.openSearchService;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractRestClient<T> {

    protected static final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    @Value("${elasticsearch.hostname}")
    protected static String elasticHosts;
    @Value("${elasticsearch.port}")
    protected static int port;
    @Value("${elasticsearch.scheme}")
    protected static String scheme;
    @Value("${elasticsearch.username}")
    private static String username;
    @Value("${elasticsearch.password}")
    private static String password;

    public abstract T restClient();

    private static void createCredentials() {
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));
    }
}
