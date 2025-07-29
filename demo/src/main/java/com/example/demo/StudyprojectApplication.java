package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class StudyprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyprojectApplication.class, args);
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));
    }

}
