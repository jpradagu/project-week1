package com.nttdata.bootcamp.productdomain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class  WebClientConfig {

	@Value("${gateway.uri}")
	private String urlGateway;
	
	@Bean
	public WebClient createWebClient() {
		return WebClient.create(urlGateway);
	}
}
