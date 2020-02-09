package com.sid.demo.flagservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.sid"})
public class FlagPickerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlagPickerServiceApplication.class, args);
	}

}
