package br.com.intelipost.web.client;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.intelipost.model.resource.UserResource;
import br.com.intelipost.web.interceptor.BasicAuthInterceptor;

@Component
public class UserClient {
	
	private static final Logger logger = LogManager.getLogger(UserClient.class.getName());
	@Autowired
	private Environment environment;
	private String URL;
	
	@PostConstruct
	private void loadURL() {
		URL = environment.getProperty("intelipost.web.api.host");
	}
	
	public UserResource login(final UserResource user) {
		
		try {
			logger.info("Iniciando a chamada na API de login.");
			final String api = URL + environment.getProperty("intelipost.web.api.login"); 
			final RestTemplate rt = new RestTemplate();
			final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
	        interceptors.add(new BasicAuthInterceptor(
	        		environment.getProperty("intelipost.web.basic.authentication.user"), 
	        		environment.getProperty("intelipost.web.basic.authentication.pass")));
	        rt.setInterceptors( interceptors );
	        
	        logger.info("Login efetuado com sucesso.");
			return rt.postForObject(api, user, UserResource.class);
		} catch(Exception e) {
			logger.info("Erro ao efetuar o login. " + e.getMessage());
		}
		return null;
	}

	public UserResource save(final UserResource user) {
		
		try {
			logger.info("Iniciando a chamada na API de cadastro.");
			final String api = URL + environment.getProperty("intelipost.web.api.user"); 
			final RestTemplate rt = new RestTemplate();
			final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
	        interceptors.add(new BasicAuthInterceptor(
	        		environment.getProperty("intelipost.web.basic.authentication.user"), 
	        		environment.getProperty("intelipost.web.basic.authentication.pass")));
	        rt.setInterceptors( interceptors );
	        logger.info("Cadastro efetuado com sucesso.");
			return rt.postForObject(api, user, UserResource.class);
		} catch(Exception e) {
			logger.info("Erro ao efetuar o cadastro. " + e.getMessage());
		}
		return null;
	}
}