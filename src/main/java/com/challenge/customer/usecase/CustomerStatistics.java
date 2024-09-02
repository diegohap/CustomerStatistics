package com.challenge.customer.usecase;

import com.challenge.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public interface CustomerStatistics {

    Double getAverageCustomersAge();

    Double getVarianceOfCustomersAge();

    Integer calculateAge(Customer customer);
}
