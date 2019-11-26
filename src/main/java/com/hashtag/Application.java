package com.hashtag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@RestController
@SpringBootApplication
public class Application {

	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2);
	}
	
	@GetMapping("/home")
	public String homePage() {
		return "HashTag Movie DB";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
