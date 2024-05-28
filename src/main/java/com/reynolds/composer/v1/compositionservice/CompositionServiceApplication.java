package com.reynolds.composer.v1.compositionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})
@ComponentScan("com.reynolds.composer.v1")
@EntityScan("com.reynolds.composer.v1")
public class CompositionServiceApplication {

    private final Integer threadPoolSize;
    private final Integer taskQueueSize;

    @Autowired
    public CompositionServiceApplication(@Value("${app.threadPoolSize:10}") Integer threadPoolSize,
                                         @Value("${app.taskQueueSize:10}") Integer taskQueueSize) {
        this.threadPoolSize = threadPoolSize;
        this.taskQueueSize = taskQueueSize;
    }

    @Bean
    public Scheduler jdbcScheduler() {
        return Schedulers.newBoundedElastic(threadPoolSize,
                taskQueueSize, "jdbc-pool");
    }

    public static void main(String[] args) {
        SpringApplication.run(CompositionServiceApplication.class, args);
    }

}
