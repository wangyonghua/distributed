package com.study.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = { "com.study" })
public class DistributedMsgcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedMsgcenterApplication.class, args);
	}
}
