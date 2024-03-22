package ru.innopolis.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.shop.models.Customer;

public interface CustomersRepository extends JpaRepository<Customer, Long> {

}
