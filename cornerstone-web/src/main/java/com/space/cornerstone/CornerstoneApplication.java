package com.space.cornerstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.space.cornerstone")
public class CornerstoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(CornerstoneApplication.class, args);
    }
}