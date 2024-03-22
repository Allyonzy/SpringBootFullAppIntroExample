package ru.innopolis.shop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class ApplicationTests {

    PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:14-alpine"));
    }

    public static void main(String[] args) {
        SpringApplication
                .from(Application::main)
                .with(ApplicationTests.class)
                .run(args);
    }

}
