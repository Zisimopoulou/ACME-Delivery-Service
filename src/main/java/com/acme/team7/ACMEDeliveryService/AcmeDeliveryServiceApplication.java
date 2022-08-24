package com.acme.team7.ACMEDeliveryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AcmeDeliveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcmeDeliveryServiceApplication.class, args);
	}

}
