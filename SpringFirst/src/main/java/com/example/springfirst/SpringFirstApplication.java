package com.example.springfirst;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringFirstApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringFirstApplication.class, args);

        System.out.println("Application Success!");

    }

}
