package com.challenge.customer.repository;

import com.challenge.customer.entity.Customer;
import java.time.LocalDate;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{

    @Query("SELECT c.birth FROM Customer c WHERE c.id = :id")
    LocalDate findAgeById(@Param("id") String id);
}
