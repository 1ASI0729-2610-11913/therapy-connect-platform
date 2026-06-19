package com.open.therapyconnect.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TherapyConnectPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(TherapyConnectPlatformApplication.class, args);
	}

}
