package com.challenge.customer.root;

import com.challenge.customer.entity.Customer;
import com.challenge.customer.entity.dto.CustomerDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.challenge.customer.service.KpiCustomerService;
import com.challenge.customer.usecase.CustomerStatistics;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerRoot {

    private final KpiCustomerService kpiCustomerService;
    private final CustomerStatistics customerStatistics;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(kpiCustomerService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Customer> getById(
            @PathVariable String id) {
        Optional<Customer> customer = kpiCustomerService.getById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Void> customerSave(
            @RequestBody CustomerDTO customer) {
        kpiCustomerService.save(customer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(
            @PathVariable String id) {
        kpiCustomerService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statistics/avg")
    @ResponseBody
    public ResponseEntity<Double> getAverageAge() {
        return ResponseEntity.ok(customerStatistics.getAverageCustomersAge());
    }
    @GetMapping("/statistics/variance")
    @ResponseBody
    public ResponseEntity<Double> getVarianceAge() {
        return ResponseEntity.ok(customerStatistics.getVarianceOfCustomersAge());
    }

}
