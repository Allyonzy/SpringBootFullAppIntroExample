package ru.innopolis.shop.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.innopolis.shop.models.Customer;
import ru.innopolis.shop.repositories.CustomersRepository;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomersControllerRESTAPITest {

    @Autowired
    private CustomersRepository customersRepository; //Репозиторий + модель

    @LocalServerPort
    private int port;

    private Customer customerFirst;

    @BeforeEach
    public void init() {
        customerFirst = new Customer();
        customerFirst.setId(2L);
        customerFirst.setFirstName("Иван");
        customerFirst.setLastName("Иванов");
        customersRepository.save(customerFirst);
    }

    @Test
    public void should_return_title() throws Exception {
        when()
                .get(String.format("http://localhost:%s/customers/getPageName", port))
                .then()
                .statusCode(is(200))
                .body(containsString("Страница покупателей"));
    }

    @Test
    public void should_return_new_name() throws Exception {

        when()
                .put(String.format("http://localhost:%s/customers/test/2/Terry", port))
                .then()
                .statusCode(is(202))
                .body(containsString("Terry"));
    }

    @AfterEach
    public void tearDown() throws Exception {
        customersRepository.deleteAll();
    }
}
