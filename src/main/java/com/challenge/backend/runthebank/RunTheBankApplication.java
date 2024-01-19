package com.challenge.backend.runthebank;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info=@Info(title="Desafio: Run The Bank!"))
public class RunTheBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunTheBankApplication.class, args);
	}

}
