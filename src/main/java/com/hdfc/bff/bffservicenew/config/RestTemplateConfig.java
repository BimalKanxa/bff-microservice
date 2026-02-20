package com.hdfc.bff.bffservicenew.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory =
                new SimpleClientHttpRequestFactory();

        // 2 seconds connection timeout
        factory.setConnectTimeout(2000);

        // 2 seconds read timeout
        factory.setReadTimeout(2000);

        return new RestTemplate(factory);
    }
}
