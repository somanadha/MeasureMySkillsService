package com.bst.mms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MeasureMySkillsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeasureMySkillsServiceApplication.class, args);
		System.out.println("Tomcat Running on port 8080 for Measure My Skills Service...");
	}
}
