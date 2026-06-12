package com.sitaf.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sitaf"})
@EntityScan(basePackages = {"com.sitaf"})
public class SitafBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SitafBackendApplication.class, args);
	}

}
