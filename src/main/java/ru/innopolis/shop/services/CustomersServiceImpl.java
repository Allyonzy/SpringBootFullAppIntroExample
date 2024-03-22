package ru.innopolis.shop.services;

import ru.innopolis.shop.exceptions.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.shop.dto.CustomerDto;
import ru.innopolis.shop.models.Customer;
import ru.innopolis.shop.repositories.CustomersRepository;

import java.util.List;

import static ru.innopolis.shop.dto.CustomerDto.from;

@RequiredArgsConstructor
@Service
public class CustomersServiceImpl implements CustomersService {

    private final CustomersRepository customersRepository;

    @Override
    public List<CustomerDto> getAll() {
        return from(customersRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        customersRepository.deleteById(id);
    }

    @Override
    public CustomerDto getById(Long id) {
        return from(customersRepository.findById(id).orElseThrow(CustomerNotFoundException::new));
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customer) {
        return from(customersRepository.save(
                Customer.builder()
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .build()));
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto newData, boolean changeOnlyName) {
        Customer customer = customersRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);

        if(!changeOnlyName) {
            customer.setLastName(newData.getLastName());
        }
        customer.setFirstName(newData.getFirstName());


        return from(customersRepository.save(customer));
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return from(customersRepository.findById(id).orElseThrow(CustomerNotFoundException::new));
    }

    @Override
    public void deleteCustomer(Long id) {
        customersRepository.deleteById(id);
    }
}
