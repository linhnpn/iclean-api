package com.iclean.icleanapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class ICleanApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ICleanApiApplication.class, args);
	}

}
