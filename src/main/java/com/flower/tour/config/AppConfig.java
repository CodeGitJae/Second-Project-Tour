package com.flower.tour.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Value("${apikey.openweather}")
    private String weatherApikey;
	
	@Bean
	String weatherApikey() {
		return weatherApikey;
	}
}
