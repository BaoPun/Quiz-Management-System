package com.project2.demo.entities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public Engine getEngine() {
		return new Engine();
	}
	
	public AppConfig() {
		super();
	}

}
