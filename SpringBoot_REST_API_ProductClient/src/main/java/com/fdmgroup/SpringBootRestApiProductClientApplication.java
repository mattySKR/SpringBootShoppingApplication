package com.fdmgroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


@SpringBootApplication
public class SpringBootRestApiProductClientApplication {
	
	private final String PRODUCT_REST_BASE_URL = "http://localhost:8080/api/v1/products";

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiProductClientApplication.class, args);
	}
	
	/**
	 * Obtains a WebClient builder
	 * 
	 * @return WebClient builder
	 */
	@Bean
	public WebClient.Builder webClientBuilder(){
		return WebClient.builder();
	}
	
	/**
	 * Creates a WebClient instance
	 * 
	 * @param builder, which is the above WebClient builder
	 * getting injected here
	 * @return WebClient (base) instance
	 */
	@Bean
	public WebClient productRestWebClient(WebClient.Builder builder) {
		return builder.baseUrl(PRODUCT_REST_BASE_URL)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}
}
