package com.space.cornerstone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.space.cornerstone.**.mapper")
@SpringBootApplication
public class CornerstoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(CornerstoneApplication.class, args);
    }
}
