package com.challenge.backend.runthebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RunTheBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunTheBankApplication.class, args);
	}

}
