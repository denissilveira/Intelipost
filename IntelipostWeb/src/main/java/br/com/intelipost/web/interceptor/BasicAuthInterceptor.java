package br.com.intelipost.web.interceptor;

import java.io.IOException;  
import java.nio.charset.Charset;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.http.HttpRequest;  
import org.springframework.http.client.ClientHttpRequestExecution;  
import org.springframework.http.client.ClientHttpRequestInterceptor;  
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.crypto.codec.Base64;

public class BasicAuthInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger( BasicAuthInterceptor.class );

    private final String username;
    private final String password;

    public BasicAuthInterceptor( String username, String password ) {
        this.username = username;
        this.password = password;
    }

    @Override
    public ClientHttpResponse intercept( HttpRequest request, byte[] body, ClientHttpRequestExecution execution ) throws IOException {
        final String auth = username + ":" + password;
        final byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
        final String authHeader = "Basic " + new String( encodedAuth );
        request.getHeaders().add( "Authorization", authHeader );
        logger.info( "Added Basic Authentication Header: user -> {}, password -> {}", username, mask( password ) );
        return execution.execute( request, body );
    }

    private String mask( String toMask ) {
        return toMask.replaceAll( ".", "*" );
    }
}