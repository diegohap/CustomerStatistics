package com.challenge.customer.service.impl;

import com.challenge.customer.entity.Customer;
import com.challenge.customer.entity.dto.CustomerDTO;
import com.challenge.customer.exception.ExceptionType;
import com.challenge.customer.exception.UseCaseException;
import com.challenge.customer.repository.CustomerRepository;
import com.challenge.customer.service.KpiCustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class KpiCustomerServiceImpl implements KpiCustomerService {

    private final CustomerRepository customerRepository;

    public Optional<Customer> getById(String id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public void save(CustomerDTO customer) {
        customerRepository.save(Customer.builder()
                .id(UUID.randomUUID().toString())
                .status(Customer.Status.ACTIVE)
                .name(customer.getName())
                .birth(LocalDate.parse(customer.getBirthday(), DateTimeFormatter.ISO_LOCAL_DATE))
                .build());
    }

    @Override
    public void deleteById(String id) {
        customerRepository.findById(id)
                .ifPresentOrElse(
                        customer -> customerRepository.save(customer.toBuilder()
                                .status(Customer.Status.DELETED)
                                .build()),
                        () -> { throw new UseCaseException(String.format("Customer %s not found", id), ExceptionType.NOT_FOUND); }
                );
    }
}
