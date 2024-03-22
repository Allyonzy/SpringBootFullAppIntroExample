package ru.innopolis.shop.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.utility.DockerImageName;
import ru.innopolis.shop.models.Customer;
import ru.innopolis.shop.repositories.CustomersRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/test-customers-data.sql"})
public class CustomerRepositoryTest {
    @Autowired
    private CustomersRepository customersRepository;

    @Container
    @ServiceConnection
    public static PostgreSQLContainer postgreSQLContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

    @Test
    void findByName_ReturnsTheStudent() {

        Customer customer = customersRepository.findById(4L).get();
        assertThat(customer).isNotNull();
        assertThat(customer.getFirstName()).isEqualTo("Customer 4");
    }
}



