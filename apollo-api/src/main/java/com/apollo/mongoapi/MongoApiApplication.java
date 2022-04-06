package com.apollo.mongoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication//(exclude = {MongoAutoConfiguration.class})
@EnableReactiveMongoRepositories
public class MongoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoApiApplication.class, args);
	}

}
