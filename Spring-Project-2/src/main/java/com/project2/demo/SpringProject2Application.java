package com.project2.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project2.demo")
public class SpringProject2Application {


	public static void main(String[] args) {
		SpringApplication.run(SpringProject2Application.class, args);
	}

}
