package ru.innopolis.shop;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.innopolis.shop.repositories.CustomersRepository;

@Slf4j
@SpringBootApplication
public class Application {


    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Bean
    public CommandLineRunner commandLineRunner(CustomersRepository customersRepository) {
        return args -> log.info(customersRepository.findAll().toString());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
