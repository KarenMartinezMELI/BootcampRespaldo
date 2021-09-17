package com.ej.calories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class CaloriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaloriesApplication.class, args);
	}

}
