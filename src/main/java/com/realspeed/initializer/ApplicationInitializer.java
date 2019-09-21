package com.realspeed.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages={"com.realspeed.*"})
public class ApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationInitializer.class, args);
	}
}