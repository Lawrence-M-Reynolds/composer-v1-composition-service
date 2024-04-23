package com.reynolds.compositionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.reynolds")
@EntityScan("com.reynolds")
public class CompositionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompositionServiceApplication.class, args);
    }

}
