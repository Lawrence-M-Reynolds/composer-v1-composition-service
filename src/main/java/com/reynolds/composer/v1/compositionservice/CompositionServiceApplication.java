package com.reynolds.composer.v1.compositionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})
@ComponentScan("com.reynolds.composer.v1")
@EntityScan("com.reynolds.composer.v1")
public class CompositionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompositionServiceApplication.class, args);
    }

}
