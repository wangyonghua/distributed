package com.study.distributed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.study")
public class DistributedWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedWebApplication.class, args);
    }
}
