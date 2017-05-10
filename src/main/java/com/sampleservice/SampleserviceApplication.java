package com.sampleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SampleserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleserviceApplication.class, args);
	}
}
