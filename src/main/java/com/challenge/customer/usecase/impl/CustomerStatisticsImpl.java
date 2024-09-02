package com.challenge.customer.usecase.impl;

import com.challenge.customer.entity.Customer;
import com.challenge.customer.exception.ExceptionType;
import com.challenge.customer.exception.UseCaseException;
import com.challenge.customer.repository.CustomerRepository;
import com.challenge.customer.usecase.CustomerStatistics;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerStatisticsImpl implements CustomerStatistics {

    private final CustomerRepository customerRepository;

    public Double getAverageCustomersAge(){
        List<Customer> customers = customerRepository.findAll().stream()
                .filter(customer -> Customer.Status.ACTIVE.equals(customer.getStatus()))
                .toList();
        List<Integer> ages = customers.stream()
                .map(this::calculateAge)
                .toList();
        double avg = ages.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow(() -> new UseCaseException("No customers found to calculate the average age", ExceptionType.INTERNAL_SERVER_ERROR));

        return BigDecimal.valueOf(avg)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
    }

    @Override
    public Double getVarianceOfCustomersAge() {
        List<Customer> customers = customerRepository.findAll().stream()
                .filter(customer -> Customer.Status.ACTIVE.equals(customer.getStatus()))
                .toList();
        List<Integer> ages = customers.stream()
                .map(this::calculateAge)
                .toList();

        double avg = getAverageCustomersAge();

        double variance = ages.stream()
                .mapToDouble(age -> Math.pow(age - avg, 2))
                .average()
                .orElseThrow(() -> new UseCaseException("No customers found to calculate the variance of age", ExceptionType.INTERNAL_SERVER_ERROR));

        return BigDecimal.valueOf(variance)
                .setScale(5, RoundingMode.HALF_UP)
                .doubleValue();
    }

    @Override
    public Integer calculateAge(Customer customer) {
        LocalDate birthDate = customer.getBirth();
        if (birthDate != null) {
            int age = LocalDate.now().getYear() - birthDate.getYear();
            if (LocalDate.now().isBefore(birthDate.plusYears(age))) {
                age--;
            }
            return age;
        }
        throw new UseCaseException(String.format("null birthday cannot be processed"), ExceptionType.INTERNAL_SERVER_ERROR);
    }
}
