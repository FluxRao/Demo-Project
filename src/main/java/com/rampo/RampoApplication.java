package com.rampo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class RampoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RampoApplication.class, args);
	}

	@Bean

	public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption,
			@Value("${application-version}") String appVersion) {

		return new OpenAPI().info(new Info().title("fOffers").version(appVersion)
				.description(appDesciption).termsOfService("http://swagger.io/terms/")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}
