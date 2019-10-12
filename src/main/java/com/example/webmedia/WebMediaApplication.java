package com.example.webmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebMediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebMediaApplication.class, args);
    }

}
