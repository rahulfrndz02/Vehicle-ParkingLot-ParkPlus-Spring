package com.driver;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@OpenAPIDefinition
public class ParkPlus {

	public static void main(String[] args) {
		SpringApplication.run(ParkPlus.class, args);
	}

}
