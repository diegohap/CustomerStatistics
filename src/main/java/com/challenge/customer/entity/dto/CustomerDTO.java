package com.challenge.customer.entity.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
public class CustomerDTO {
    private String name;
    private String birthday;
}
