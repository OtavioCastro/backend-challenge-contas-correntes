package com.challange.backend.runthebank.domain.dtos;

public record CostumerUpdateDTO(
        String name,
        String address,
        String password
) {
}
