package ru.innopolis.shop.services;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.innopolis.shop.dto.CustomerDto;
import ru.innopolis.shop.exceptions.CustomerNotFoundException;
import ru.innopolis.shop.models.Customer;
import ru.innopolis.shop.repositories.CustomersRepository;
import ru.innopolis.shop.services.CustomersService;
import ru.innopolis.shop.services.CustomersServiceImpl;

import static ru.innopolis.shop.dto.CustomerDto.from;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName(value = "Customer Service is working when")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
class CustomerServiceTest {
    @MockBean
    private CustomersRepository customerRepositoryMock; //Репозиторий + модель, класс 1

    @Autowired
    private CustomersService customerService = new CustomersServiceImpl(customerRepositoryMock); // класс 2

    private final List<Customer> customers = new ArrayList<>(); // класс 3
    private Customer customerFirst;
    private Customer customerSecond;

    @BeforeEach
    public void init() {
        customerFirst = new Customer();
        customerFirst.setId(56L);
        customerFirst.setFirstName("Иван");
        customerFirst.setLastName("Иванов");

        customerSecond = new Customer();
        customerSecond.setId(57L);
        customerSecond.setFirstName("Ирина");
        customerSecond.setLastName("Сидорова");
        customerRepositoryMock.save(customerFirst);
        customerRepositoryMock.save(customerSecond);

        customers.add(customerFirst);
        customers.add(customerSecond);
    }

    @Test
    public void handleFindById_ReturnsCustomerDTOWithFullFields() {
        Mockito.when(
                customerRepositoryMock.findById(56L)
        ).thenReturn(
                Optional.of(customerFirst)
        );
        CustomerDto found = customerService.getCustomerById(customerFirst.getId());

        assertNotNull(found);

        Assertions.assertEquals(customerFirst.getFirstName(), found.getFirstName());
        Assertions.assertEquals(customerFirst.getId(), found.getId());
    }

    @Test
    public void delete_customer_by_id() {
        customerService.deleteCustomer(customerFirst.getId());
        Mockito.verify(customerRepositoryMock, Mockito.times(1))
                .deleteById(customerFirst.getId());
    }
}