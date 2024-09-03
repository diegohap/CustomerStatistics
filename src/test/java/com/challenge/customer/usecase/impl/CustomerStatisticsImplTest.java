package com.challenge.customer.usecase.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.challenge.customer.entity.Customer;
import com.challenge.customer.repository.CustomerRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.challenge.customer.service.KpiCustomerService;
import com.challenge.customer.service.impl.KpiCustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


class CustomerStatisticsImplTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private KpiCustomerService kpiCustomerService;

    @InjectMocks private CustomerStatisticsImpl instance;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        kpiCustomerService = new KpiCustomerServiceImpl(customerRepository);
        instance = new CustomerStatisticsImpl(kpiCustomerService);
        when(customerRepository.findAll()).thenReturn(buildCustomerList());
    }

    @Test
    void getAverageCustomersAge() {
        Double avg = instance.getAverageCustomersAge();
        assertThat(avg).isEqualTo(20.333);
    }

    @Test
    void getVarianceOfCustomersAge() {
        Double avg = instance.getVarianceOfCustomersAge();
        assertThat(avg).isEqualTo(2.22222);
    }

    @Test
    void calculateAge() {
        Integer age = instance.calculateAge(buildCustomer());
        assertThat(age).isEqualTo(23);
    }

    private Customer buildCustomer() {
        return Customer.builder()
                .birth(LocalDate.parse("2000-10-10", DateTimeFormatter.ISO_LOCAL_DATE))
                .build();
    }

    private List<Customer> buildCustomerList() {
        return List.of(
                Customer.builder()
                        .status(Customer.Status.DELETED)
                        .birth(LocalDate.parse("2003-10-18", DateTimeFormatter.ISO_LOCAL_DATE))
                        .build(),
                Customer.builder()
                        .status(Customer.Status.ACTIVE)
                        .birth(LocalDate.parse("2003-10-18", DateTimeFormatter.ISO_LOCAL_DATE))
                        .build(),
                Customer.builder()
                        .status(Customer.Status.ACTIVE)
                        .birth(LocalDate.parse("2003-10-18", DateTimeFormatter.ISO_LOCAL_DATE))
                        .build(),
                Customer.builder()
                        .status(Customer.Status.ACTIVE)
                        .birth(LocalDate.parse("2003-10-18", DateTimeFormatter.ISO_LOCAL_DATE))
                        .build(),
                Customer.builder()
                        .status(Customer.Status.ACTIVE)
                        .birth(LocalDate.parse("2002-10-18", DateTimeFormatter.ISO_LOCAL_DATE))
                        .build(),
                Customer.builder()
                        .status(Customer.Status.ACTIVE)
                        .birth(LocalDate.parse("2005-10-18", DateTimeFormatter.ISO_LOCAL_DATE))
                        .build(),
                Customer.builder()
                        .status(Customer.Status.ACTIVE)
                        .birth(LocalDate.parse("2000-10-18", DateTimeFormatter.ISO_LOCAL_DATE))
                        .build()
        );
    }
}