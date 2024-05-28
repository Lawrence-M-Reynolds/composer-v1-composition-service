package com.reynolds.composer.v1.compositionservice;

import com.reynolds.composer.v1.AbstractComposerLocalDevTestcontainersConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class CompositionServiceLocalRunner {

    public static void main(String[] args) {
        SpringApplication.from(CompositionServiceApplication::main)
                .with(LocalDevTestcontainersConfig.class)
                .run(args);
    }

    @TestConfiguration(proxyBeanMethods = false)
    static private class LocalDevTestcontainersConfig extends AbstractComposerLocalDevTestcontainersConfig {
        @Bean
        @ServiceConnection
        public MySQLContainer<?> mySQLContainer() {
            var container = new MySQLContainer<>("mysql:8.0.37");
            this.configureContainer(container);
            return container;
        }
    }
}

