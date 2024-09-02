package com.challenge.customer.service;

import com.challenge.customer.entity.Customer;
import com.challenge.customer.entity.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface KpiCustomerService {

    Optional<Customer> getById(String id);

    List<Customer> getAll();

    void save(CustomerDTO customer);

    void deleteById(String id);
}
