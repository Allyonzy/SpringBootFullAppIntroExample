package ru.innopolis.shop.services;

import ru.innopolis.shop.dto.CustomerDto;
import ru.innopolis.shop.models.Customer;

import java.util.List;

public interface CustomersService {
    List<CustomerDto> getAll();
    void deleteById(Long id);
    CustomerDto getById(Long id);

    CustomerDto addCustomer(CustomerDto customer);

    CustomerDto updateCustomer(Long customerId, CustomerDto newData, boolean changeOnlyName);

    CustomerDto getCustomerById(Long id);

    void deleteCustomer(Long id);
}
